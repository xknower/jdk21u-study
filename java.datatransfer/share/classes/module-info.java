/**
 * Defines the API for transferring data between and within applications.
 *
 * @moduleGraph
 * @since 9
 */
module java.datatransfer {
    exports java.awt.datatransfer;

    exports sun.datatransfer to java.desktop;

    uses sun.datatransfer.DesktopDatatransferService;
}
