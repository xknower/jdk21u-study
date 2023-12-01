package java.io;

/**
 *
 * @since 1.8
 */
final class DefaultFileSystem {

    private DefaultFileSystem() {}

    /**
     * Return the FileSystem object for Windows platform.
     */
    public static FileSystem getFileSystem() {
        return new WinNTFileSystem();
    }
}
