package jdk.dynalink;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.util.Objects;
import java.util.function.Supplier;
import jdk.dynalink.linker.ConversionComparator.Comparison;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.GuardingDynamicLinker;
import jdk.dynalink.linker.LinkRequest;
import jdk.dynalink.linker.LinkerServices;
import jdk.dynalink.linker.MethodHandleTransformer;

/**
 * Default implementation of the {@link LinkerServices} interface.
 */
final class LinkerServicesImpl implements LinkerServices {
    private static final ThreadLocal<SecureLookupSupplier> threadLookupSupplier = new ThreadLocal<>();

    private final TypeConverterFactory typeConverterFactory;
    private final GuardingDynamicLinker topLevelLinker;
    private final MethodHandleTransformer internalObjectsFilter;

    /**
     * Creates a new linker services object.
     *
     * @param typeConverterFactory the type converter factory exposed by the services.
     * @param topLevelLinker the top level linker used by the services.
     * @param internalObjectsFilter a method handle transformer that is supposed to act as the implementation of this
     * services' {@link #filterInternalObjects(java.lang.invoke.MethodHandle)} method.
     */
    LinkerServicesImpl(final TypeConverterFactory typeConverterFactory,
            final GuardingDynamicLinker topLevelLinker, final MethodHandleTransformer internalObjectsFilter) {
        this.typeConverterFactory = typeConverterFactory;
        this.topLevelLinker = topLevelLinker;
        this.internalObjectsFilter = internalObjectsFilter;
    }

    @Override
    public boolean canConvert(final Class<?> from, final Class<?> to) {
        return typeConverterFactory.canConvert(from, to);
    }

    @Override
    public MethodHandle asType(final MethodHandle handle, final MethodType fromType) {
        return typeConverterFactory.asType(handle, fromType);
    }

    @Override
    public MethodHandle getTypeConverter(final Class<?> sourceType, final Class<?> targetType) {
        return typeConverterFactory.getTypeConverter(sourceType, targetType);
    }

    @Override
    public Comparison compareConversion(final Class<?> sourceType, final Class<?> targetType1, final Class<?> targetType2) {
        return typeConverterFactory.compareConversion(sourceType, targetType1, targetType2);
    }

    /**
     * Used to marshal a checked exception out of Supplier.get() in getGuardedInvocation.
     */
    private static class LinkerException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public LinkerException(final Exception cause) {
            super(null, cause, true, false);
        }
    }

    @Override
    public GuardedInvocation getGuardedInvocation(final LinkRequest linkRequest) throws Exception {
        try {
            return getWithLookupInternal(() -> {
                try {
                    return topLevelLinker.getGuardedInvocation(linkRequest, this);
                } catch (final RuntimeException e) {
                    throw e;
                } catch (final Exception e) {
                    throw new LinkerException(e);
                }
            }, linkRequest.getCallSiteDescriptor());
        } catch (final LinkerException e) {
            throw (Exception)e.getCause();
        }
    }

    @Override
    public MethodHandle filterInternalObjects(final MethodHandle target) {
        return internalObjectsFilter != null ? internalObjectsFilter.transform(target) : target;
    }

    @Override
    public <T> T getWithLookup(final Supplier<T> operation, final SecureLookupSupplier lookupSupplier) {
        return getWithLookupInternal(
                Objects.requireNonNull(operation, "action"),
                Objects.requireNonNull(lookupSupplier, "lookupSupplier"));
    }

    private static <T> T getWithLookupInternal(final Supplier<T> operation, final SecureLookupSupplier lookupSupplier) {
        final SecureLookupSupplier prevLookupSupplier = threadLookupSupplier.get();
        final boolean differ = prevLookupSupplier != lookupSupplier;
        if (differ) {
            threadLookupSupplier.set(lookupSupplier);
        }
        try {
            return operation.get();
        } finally {
            if (differ) {
                threadLookupSupplier.set(prevLookupSupplier);
            }
        }
    }

    static Lookup getCurrentLookup() {
        final SecureLookupSupplier lookupSupplier = threadLookupSupplier.get();
        if (lookupSupplier != null) {
            return lookupSupplier.getLookup();
        }
        return MethodHandles.publicLookup();
    }
}
