/**
 * Provides the implementation of the SunMSCAPI security provider.
 *
 * @provides java.security.Provider
 * @moduleGraph
 * @since 9
 */
module jdk.crypto.mscapi {
    provides java.security.Provider with sun.security.mscapi.SunMSCAPI;
}

