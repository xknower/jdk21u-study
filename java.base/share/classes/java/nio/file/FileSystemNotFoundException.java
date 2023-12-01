package java.nio.file;

/**
 * Runtime exception thrown when a file system cannot be found.
 *
 * @since 1.7
 */

public class FileSystemNotFoundException
    extends RuntimeException
{
    @java.io.Serial
    static final long serialVersionUID = 7999581764446402397L;

    /**
     * Constructs an instance of this class.
     */
    public FileSystemNotFoundException() {
    }

    /**
     * Constructs an instance of this class.
     *
     * @param   msg
     *          the detail message
     */
    public FileSystemNotFoundException(String msg) {
        super(msg);
    }
}
