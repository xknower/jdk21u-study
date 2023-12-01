package jdk.internal.vm;

/**
 * Defines a static method to test if the VM has support for the foreign java.lang.foreign.Linker.
 */
public final class ForeignLinkerSupport {
    private static final boolean SUPPORTED = isSupported0();

    private ForeignLinkerSupport() {
    }

    /**
     * Return true if the VM has support for the foreign Linker.
     */
    public static boolean isSupported() {
        return SUPPORTED;
    }

    private static native boolean isSupported0();
}
