package java.io;

/**
 *
 * @since 1.8
 */
final class DefaultFileSystem {

    private DefaultFileSystem() {}

    /**
     * Return the FileSystem object for Unix-based platform.
     */
    public static FileSystem getFileSystem() {
        return new UnixFileSystem();
    }
}
