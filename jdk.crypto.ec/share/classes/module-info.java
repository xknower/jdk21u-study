/**
 * Provides the implementation of the SunEC security provider.
 *
 * @provides java.security.Provider
 *
 * @moduleGraph
 * @since 9
 */
module jdk.crypto.ec {
    provides java.security.Provider with sun.security.ec.SunEC;
}
