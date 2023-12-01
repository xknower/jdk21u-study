package jdk.internal.agent;

import java.io.File;
import java.io.IOException;

/*
 * Linux implementation of jdk.internal.agent.FileSystem
 */
@SuppressWarnings("removal")
public class FileSystemImpl extends FileSystem {

    public boolean supportsFileSecurity(File f) throws IOException {
        return true;
    }

    public boolean isAccessUserOnly(File f) throws IOException {
        String path = f.getPath();
        if (path.indexOf(0) >= 0) {
            throw new IOException("illegal filename");
        }
        return isAccessUserOnly0(f.getPath());
    }

    // Native methods

    static native boolean isAccessUserOnly0(String path) throws IOException;

    // Initialization

    static {
        java.security.AccessController.doPrivileged(
            new java.security.PrivilegedAction<Void>() {
                public Void run() {
                    System.loadLibrary("management_agent");
                    return null;
                }
            });
    }
}
