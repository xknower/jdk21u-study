package jdk.dynalink.linker;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.function.Supplier;
import jdk.dynalink.DynamicLinker;
import jdk.dynalink.DynamicLinkerFactory;
import jdk.dynalink.SecureLookupSupplier;
import jdk.dynalink.linker.ConversionComparator.Comparison;
import jdk.dynalink.linker.support.TypeUtilities;

/**
 * Interface for services provided to {@link GuardingDynamicLinker} instances by
 * the {@link DynamicLinker} that owns them.
 */
public interface LinkerServices {
    /**
     * Similar to {@link MethodHandle#asType(MethodType)} except it also hooks
     * in method handles produced by all available
     * {@link GuardingTypeConverterFactory} implementations, providing for
     * language-specific type coercing of parameters. It will apply
     * {@link MethodHandle#asType(MethodType)} for all primitive-to-primitive,
     * wrapper-to-primitive, primitive-to-wrapper conversions as well as for all
     * upcasts. For all other conversions, it'll insert
     * {@link MethodHandles#filterArguments(MethodHandle, int, MethodHandle...)}
     * with composite filters provided by {@link GuardingTypeConverterFactory}
     * implementations.
     *
     * @param handle target method handle
     * @param fromType the types of source arguments
     * @return a method handle that is a suitable combination of
     * {@link MethodHandle#asType(MethodType)},
     * {@link MethodHandles#filterArguments(MethodHandle, int, MethodHandle...)},
     * and {@link MethodHandles#filterReturnValue(MethodHandle, MethodHandle)}
     * with {@link GuardingTypeConverterFactory}-produced type converters as
     * filters.
     */
    public MethodHandle asType(MethodHandle handle, MethodType fromType);

    /**
     * Similar to {@link #asType(MethodHandle, MethodType)} except it treats
     * return value type conversion specially. It only converts the return type
     * of the method handle when it can be done using a conversion that loses
     * neither precision nor magnitude, otherwise it leaves it unchanged. These
     * are the only return value conversions that should be performed by
     * individual language-specific linkers, and
     * {@link DynamicLinkerFactory#setPrelinkTransformer(GuardedInvocationTransformer)
     * pre-link transformer of the dynamic linker} should implement the strategy
     * for dealing with potentially lossy return type conversions in a manner
     * specific to the language runtime where the call site is located.
     *
     * @param handle target method handle
     * @param fromType the types of source arguments
     * @return a method handle that is a suitable combination of
     * {@link MethodHandle#asType(MethodType)}, and
     * {@link MethodHandles#filterArguments(MethodHandle, int, MethodHandle...)}
     * with {@link GuardingTypeConverterFactory}-produced type converters as filters.
     */
    public default MethodHandle asTypeLosslessReturn(final MethodHandle handle, final MethodType fromType) {
        final Class<?> handleReturnType = handle.type().returnType();
        return asType(handle, TypeUtilities.isConvertibleWithoutLoss(handleReturnType, fromType.returnType()) ?
                fromType : fromType.changeReturnType(handleReturnType));
    }

    /**
     * Given a source and target type, returns a method handle that converts
     * between them. Never returns null; in worst case it will return an
     * identity conversion (that might fail for some values at runtime). You
     * rarely need to use this method directly and should mostly rely on
     * {@link #asType(MethodHandle, MethodType)} instead. This method is needed
     * when you need to reuse existing type conversion machinery outside the
     * context of processing a link request.
     * @param sourceType the type to convert from
     * @param targetType the type to convert to
     * @return a method handle performing the conversion.
     */
    public MethodHandle getTypeConverter(Class<?> sourceType, Class<?> targetType);

    /**
     * Returns true if there might exist a conversion between the requested
     * types (either an automatic JVM conversion, or one provided by any
     * available {@link GuardingTypeConverterFactory}), or false if there
     * definitely does not exist a conversion between the requested types. Note
     * that returning true does not guarantee that the conversion will succeed
     * at runtime for all values (especially if the "from" or "to" types are
     * sufficiently generic), but returning false guarantees that it would fail.
     *
     * @param from the source type for the conversion
     * @param to the target type for the conversion
     * @return true if there can be a conversion, false if there can not.
     */
    public boolean canConvert(Class<?> from, Class<?> to);

    /**
     * Creates a guarded invocation delegating back to the {@link DynamicLinker}
     * that exposes this linker services object. The dynamic linker will then
     * itself delegate the linking to all of its managed
     * {@link GuardingDynamicLinker}s including potentially this one if no
     * linker responds earlier, so beware of infinite recursion. You'll
     * typically craft the link request so that it will be different than the
     * one you are currently trying to link.
     *
     * @param linkRequest a request for linking the invocation
     * @return a guarded invocation linked by some of the guarding dynamic
     * linkers managed by the top-level dynamic linker. Can be null if no
     * available linker is able to link the invocation. You will typically use
     * the elements of the returned invocation to compose your own invocation.
     * @throws Exception in case the top-level linker throws an exception
     */
    public GuardedInvocation getGuardedInvocation(LinkRequest linkRequest) throws Exception;

    /**
     * Determines which of the two type conversions from a source type to the
     * two target types is preferred. This is used for dynamic overloaded method
     * resolution. If the source type is convertible to exactly one target type
     * with a method invocation conversion, it is chosen, otherwise available
     * {@link ConversionComparator}s are consulted.
     * @param sourceType the source type.
     * @param targetType1 one potential target type
     * @param targetType2 another potential target type.
     * @return one of Comparison constants that establish which &ndash; if any
     * &ndash; of the target types is preferable for the conversion.
     */
    public Comparison compareConversion(Class<?> sourceType, Class<?> targetType1, Class<?> targetType2);

    /**
     * Modifies the method handle so that any parameters that can receive
     * potentially internal language runtime objects will have a filter added on
     * them to prevent them from escaping, potentially by wrapping them. It can
     * also potentially add an unwrapping filter to the return value. Basically
     * transforms the method handle using the transformer configured by
     * {@link DynamicLinkerFactory#setInternalObjectsFilter(MethodHandleTransformer)}.
     * @param target the target method handle
     * @return a method handle with parameters and/or return type potentially
     * filtered for wrapping and unwrapping.
     */
    public MethodHandle filterInternalObjects(final MethodHandle target);

    /**
     * Executes an operation within the context of a particular
     * {@code MethodHandles.Lookup} lookup object. Normally, methods on
     * {@code LinkerServices} are invoked as part of the linking mechanism in
     * which case Dynalink internally maintains a per-thread current lookup
     * (the one belonging to the descriptor of the call site being linked). This
     * lookup can be retrieved by any {@link GuardingTypeConverterFactory}
     * involved in linking if it needs to generate lookup-sensitive converters.
     * However, linker services' methods can be invoked outside the linking
     * process too when implementing invocation-time dispatch schemes, invoking
     * conversions at runtime, etc. If it becomes necessary to use any type
     * converter in this situation, and it needs a lookup, it will normally only
     * get {@link MethodHandles#publicLookup()} as the thread is not engaged in
     * a linking operation. If there is a way to meaningfully associate the
     * operation to the context of some caller class, consider performing it
     * within an invocation of this method and passing a full-strength lookup
     * for that class, as it will associate that lookup with the current thread
     * for the duration of the operation. Note that since you are passing a
     * {@link SecureLookupSupplier}, any invoked type converter factories will
     * still need to hold the necessary runtime permission to be able to get the
     * lookup should they need it.
     * @param <T> the type of the return value provided by the passed-in supplier.
     * @param operation the operation to execute in context of the specified lookup.
     * @param lookupSupplier secure supplier of the lookup
     * @return the return value of the action
     * @throws NullPointerException if either action or lookupSupplier are null.
     * @see GuardingTypeConverterFactory#convertToType(Class, Class, Supplier)
     */
    public <T> T getWithLookup(final Supplier<T> operation, final SecureLookupSupplier lookupSupplier);
}
