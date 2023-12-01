/**
 * Defines the API for XML cryptography.
 *
 * @moduleGraph
 * @since 9
 */
module java.xml.crypto {
    requires java.logging;

    requires transitive java.xml;

    exports javax.xml.crypto;
    exports javax.xml.crypto.dom;
    exports javax.xml.crypto.dsig;
    exports javax.xml.crypto.dsig.dom;
    exports javax.xml.crypto.dsig.keyinfo;
    exports javax.xml.crypto.dsig.spec;

    provides java.security.Provider with
        org.jcp.xml.dsig.internal.dom.XMLDSigRI;
}

