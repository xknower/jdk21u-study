package sun.net;

/**
 * Determines the ephemeral port range in use on this system.
 * On Windows we always use the default range.
 */
public final class PortConfig {

    private static final int defaultLower = 49152;
    private static final int defaultUpper = 65535;

    public static int getLower() {
        return defaultLower;
    }

    public static int getUpper() {
        return defaultUpper;
    }
}
