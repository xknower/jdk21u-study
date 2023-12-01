package jdk.tools.jlink.internal.plugins;

/**
 * Plugin to set the vendor VM bug URL, by redefining the static field
 * java.lang.VersionProps.VENDOR_URL_VM_BUG
 */
public final class VendorVMBugURLPlugin extends VersionPropsPlugin {

    public VendorVMBugURLPlugin() {
        super("VENDOR_URL_VM_BUG", "vendor-vm-bug-url");
    }

}
