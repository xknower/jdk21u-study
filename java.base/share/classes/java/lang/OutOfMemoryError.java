package java.lang;

/**
 * Thrown when the Java Virtual Machine cannot allocate an object
 * because it is out of memory, and no more memory could be made
 * available by the garbage collector.
 *
 * {@code OutOfMemoryError} objects may be constructed by the virtual
 * machine as if {@linkplain Throwable#Throwable(String, Throwable,
 * boolean, boolean) suppression were disabled and/or the stack trace was not
 * writable}.
 *
 * @since   1.0
 */
public class OutOfMemoryError extends VirtualMachineError {
    @java.io.Serial
    private static final long serialVersionUID = 8228564086184010517L;

    /**
     * Constructs an {@code OutOfMemoryError} with no detail message.
     */
    public OutOfMemoryError() {
        super();
    }

    /**
     * Constructs an {@code OutOfMemoryError} with the specified
     * detail message.
     *
     * @param   s   the detail message.
     */
    public OutOfMemoryError(String s) {
        super(s);
    }
}
