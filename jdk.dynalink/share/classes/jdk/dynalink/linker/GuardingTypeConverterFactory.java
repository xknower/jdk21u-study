package jdk.dynalink.linker;

import java.lang.invoke.MethodHandles;
import java.util.function.Supplier;
import jdk.dynalink.SecureLookupSupplier;
import jdk.dynalink.beans.BeansLinker;
import jdk.dynalink.linker.support.TypeUtilities;

/**
 * Optional interface that can be implemented by {@link GuardingDynamicLinker}
 * implementations to provide language-specific type conversion capabilities.
 * Note that if you implement this interface, you will very likely want to
 * implement {@link ConversionComparator} interface too, as your additional
 * language-specific conversions, in absence of a strategy for prioritizing
 * these conversions, will cause more ambiguity for {@link BeansLinker} in
 * selecting the correct overload when trying to link to an overloaded Java
 * method.
 */
public interface GuardingTypeConverterFactory {
    /**
     * Returns a guarded type conversion that receives a value of the specified
     * source type and returns a value converted to the specified target type.
     * Value types can be either primitives or reference types, including
     * interfaces, so you can even provide converters for converting your
     * language's objects to Java interfaces and classes by generating adapters
     * for them.
     * <p>
     * The type of the invocation is <code>(sourceType)&rarr;targetType</code>, while the
     * type of the guard is <code>(sourceType)&rarr;boolean</code>. You are allowed to
     * return unconditional invocations (with no guard) if the source type is
     * specific to your runtime and your runtime only.
     * <p>Note that this method will never be invoked for
     * {@link TypeUtilities#isMethodInvocationConvertible(Class, Class) method
     * invocation conversions} as those can be automatically applied by
     * {@link java.lang.invoke.MethodHandle#asType(MethodType)}.
     * An implementation can assume it is never requested to produce a
     * converter for those conversions. If a language runtime needs to customize
     * method invocation conversions, it should
     * {@link jdk.dynalink.DynamicLinkerFactory#setAutoConversionStrategy(MethodTypeConversionStrategy)
     * set an autoconversion strategy in the dynamic linker factory} instead.
     * <p>Dynalink is at liberty to either cache some of the returned converters
     * or to repeatedly request the converter factory to create the same
     * conversion.
     *
     * @param sourceType source type
     * @param targetType the target type.
     * @param lookupSupplier a supplier for retrieving the lookup of the class
     * on whose behalf a type converter is requested. When a converter is
     * requested as part of linking an {@code invokedynamic} instruction the
     * supplier will return the lookup passed to the bootstrap method, otherwise
     * if the method is invoked from within a
     * {@link LinkerServices#getWithLookup(Supplier, jdk.dynalink.SecureLookupSupplier)}
     * it will delegate to the secure lookup supplier. In any other case,
     * it will return the public lookup. A typical case where the lookup might
     * be needed is when the converter creates a Java adapter class on the fly
     * (e.g. to convert some object from the dynamic language into a Java
     * interface for interoperability). Invoking the {@link Supplier#get()}
     * method on the passed supplier will be subject to the same security checks
     * as {@link SecureLookupSupplier#getLookup()}. An implementation should avoid
     * retrieving the lookup if it is not needed so as to avoid the expense of
     * {@code AccessController.doPrivileged} call.
     * @return a guarded invocation that can take an object (if it passes guard)
     * and return another object that is its representation coerced into the
     * target type. In case the factory is certain it is unable to handle a
     * conversion, it can return null. In case the factory is certain that it
     * can always handle the conversion, it can return an unconditional
     * invocation (one whose guard is null).
     * @throws Exception if there was an error during creation of the converter
     * @see LinkerServices#getWithLookup(Supplier, SecureLookupSupplier)
     */
    public GuardedInvocation convertToType(Class<?> sourceType, Class<?> targetType, Supplier<MethodHandles.Lookup> lookupSupplier) throws Exception;
}
