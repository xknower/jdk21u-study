package jdk.internal.joptsimple;

/**
 * Thrown by {@link ValueConverter}s when problems occur in converting string values to other Java types.
 *
 * @author <a href="mailto:pholser@alumni.rice.edu">Paul Holser</a>
 */
public class ValueConversionException extends RuntimeException {
    private static final long serialVersionUID = -1L;

    /**
     * Creates a new exception with the specified detail message.
     *
     * @param message the detail message
     */
    public ValueConversionException( String message ) {
        this( message, null );
    }

    /**
     * Creates a new exception with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the original exception
     */
    public ValueConversionException( String message, Throwable cause ) {
        super( message, cause );
    }
}
