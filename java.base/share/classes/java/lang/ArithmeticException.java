package java.lang;

/**
 * Thrown when an exceptional arithmetic condition has occurred. For
 * example, an integer "divide by zero" throws an
 * instance of this class.
 *
 * {@code ArithmeticException} objects may be constructed by the
 * virtual machine as if {@linkplain Throwable#Throwable(String,
 * Throwable, boolean, boolean) suppression were disabled and/or the
 * stack trace was not writable}.
 *
 * @since   1.0
 */
public class ArithmeticException extends RuntimeException {
    @java.io.Serial
    private static final long serialVersionUID = 2256477558314496007L;

    /**
     * Constructs an {@code ArithmeticException} with no detail
     * message.
     */
    public ArithmeticException() {
        super();
    }

    /**
     * Constructs an {@code ArithmeticException} with the specified
     * detail message.
     *
     * @param   s   the detail message.
     */
    public ArithmeticException(String s) {
        super(s);
    }
}
