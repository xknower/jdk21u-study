package jdk.internal.misc;

public class OSEnvironment {

    /*
     * Initialize any miscellaneous operating system settings that need to be set
     * for the class libraries.
     */
    public static void initialize() {
        // no-op on Solaris and Linux
    }

}
