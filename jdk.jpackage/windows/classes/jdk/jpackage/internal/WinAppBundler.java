package jdk.jpackage.internal;

public class WinAppBundler extends AppImageBundler {
    public WinAppBundler() {
        setAppImageSupplier(WindowsAppImageBuilder::new);
    }
}
