/**
 * This module is intended for use only by the javafx.swing module
 * for the purpose of FX/Swing interop
 *
 * @since 11
 */
module jdk.unsupported.desktop {

    requires transitive java.desktop;

    exports jdk.swing.interop;

    provides sun.swing.InteropProvider
        with jdk.swing.interop.internal.InteropProviderImpl;

}

