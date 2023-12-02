package jdk.vm.ci.code;

/**
 * A CPU feature identified by a name.
 */
public interface CPUFeatureName {
    /**
     * Gets the name of this feature.
     */
    String name();

    /**
     * Determines if {@code other} equals {@link #name()}.
     */
    default boolean nameEquals(String other) {
        return name().equals(other);
    }
}
