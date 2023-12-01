/**
 * Defines the Java Smart Card I/O API.
 *
 * @moduleGraph
 * @since 9
 */
module java.smartcardio {
    exports javax.smartcardio;

    provides java.security.Provider with
        sun.security.smartcardio.SunPCSC;
}

