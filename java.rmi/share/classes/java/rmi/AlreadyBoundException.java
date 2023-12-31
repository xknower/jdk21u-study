package java.rmi;

/**
 * An <code>AlreadyBoundException</code> is thrown if an attempt
 * is made to bind an object to a name that already
 * has an associated binding in the registry.
 *
 * @since   1.1
 * @author  Ann Wollrath
 * @author  Roger Riggs
 * @see     java.rmi.Naming#bind(String, java.rmi.Remote)
 * @see     java.rmi.registry.Registry#bind(String, java.rmi.Remote)
 */
public class AlreadyBoundException extends java.lang.Exception {

    /* indicate compatibility with JDK 1.1.x version of class */
    private static final long serialVersionUID = 9218657361741657110L;

    /**
     * Constructs an <code>AlreadyBoundException</code> with no
     * specified detail message.
     * @since 1.1
     */
    public AlreadyBoundException() {
        super();
    }

    /**
     * Constructs an <code>AlreadyBoundException</code> with the specified
     * detail message.
     *
     * @param s the detail message
     * @since 1.1
     */
    public AlreadyBoundException(String s) {
        super(s);
    }
}
