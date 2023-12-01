package jdk.dynalink.linker;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import jdk.dynalink.DynamicLinkerFactory;

/**
 * Interface for objects representing a strategy for converting a method handle
 * to a new type. Typical usage is for customizing a language runtime's handling
 * of
 * {@link DynamicLinkerFactory#setAutoConversionStrategy(MethodTypeConversionStrategy)
 * method invocation conversions}.
 */
@FunctionalInterface
public interface MethodTypeConversionStrategy {
    /**
     * Converts a method handle to a new type.
     * @param target target method handle
     * @param newType new type
     * @return target converted to the new type.
     */
    public MethodHandle asType(final MethodHandle target, final MethodType newType);
}
