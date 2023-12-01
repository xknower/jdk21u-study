package jdk.dynalink.linker;

import java.lang.invoke.MethodHandle;
import jdk.dynalink.DynamicLinkerFactory;

/**
 * A generic interface describing operations that transform method handles.
 * Typical usage is for implementing
 * {@link DynamicLinkerFactory#setInternalObjectsFilter(MethodHandleTransformer)
 * internal objects filters}.
 */
@FunctionalInterface
public interface MethodHandleTransformer {
    /**
     * Transforms a method handle.
     * @param target the method handle being transformed.
     * @return transformed method handle.
     */
    public MethodHandle transform(final MethodHandle target);
}
