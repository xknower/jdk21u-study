package jdk.jpackage.internal;

public class LinuxAppBundler extends AppImageBundler {
    public LinuxAppBundler() {
        setAppImageSupplier(LinuxAppImageBuilder::new);
    }
}
