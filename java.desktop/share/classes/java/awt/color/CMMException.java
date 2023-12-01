package java.awt.color;

import java.io.Serial;

/**
 * This exception is thrown if the native CMM returns an error.
 */
public class CMMException extends RuntimeException {

    /**
     * Use serialVersionUID from JDK 1.2 for interoperability.
     */
    @Serial
    private static final long serialVersionUID = 5775558044142994965L;

    /**
     * Constructs a {@code CMMException} with the specified detail message.
     *
     * @param  s the specified detail message
     */
    public CMMException(String s) {
        super(s);
    }
}
