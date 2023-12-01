package jdk.tools.jlink.internal.plugins;

/**
 * Plugin to set the vendor bug URL, by redefining the static field
 * java.lang.VersionProps.VENDOR_URL_BUG
 */
public final class VendorBugURLPlugin extends VersionPropsPlugin {

    public VendorBugURLPlugin() {
        super("VENDOR_URL_BUG", "vendor-bug-url");
    }

}
