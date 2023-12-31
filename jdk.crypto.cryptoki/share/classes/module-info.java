/**
 * Provides the implementation of the SunPKCS11 security provider.
 *
 * @provides java.security.Provider
 *
 * @moduleGraph
 * @since 9
 */
module jdk.crypto.cryptoki {
    // Depends on SunEC provider for EC related functionality
    requires jdk.crypto.ec;

    provides java.security.Provider with sun.security.pkcs11.SunPKCS11;
}
