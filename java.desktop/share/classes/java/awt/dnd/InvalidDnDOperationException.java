package java.awt.dnd;

import java.io.Serial;

/**
 * This exception is thrown by various methods in the java.awt.dnd package.
 * It is usually thrown to indicate that the target in question is unable
 * to undertake the requested operation that the present time, since the
 * underlying DnD system is not in the appropriate state.
 *
 * @since 1.2
 */

public class InvalidDnDOperationException extends IllegalStateException {

    /**
     * Use serialVersionUID from JDK 1.8 for interoperability.
     */
    @Serial
    private static final long serialVersionUID = -6062568741193956678L;

    private static String dft_msg = "The operation requested cannot be performed by the DnD system since it is not in the appropriate state";

    /**
     * Create a default Exception
     */

    public InvalidDnDOperationException() { super(dft_msg); }

    /**
     * Create an Exception with its own descriptive message
     *
     * @param msg the detail message
     */

    public InvalidDnDOperationException(String msg) { super(msg); }

}
