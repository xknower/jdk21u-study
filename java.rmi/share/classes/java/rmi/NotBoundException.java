package java.rmi;

/**
 * A <code>NotBoundException</code> is thrown if an attempt
 * is made to lookup or unbind in the registry a name that has
 * no associated binding.
 *
 * @since   1.1
 * @author  Ann Wollrath
 * @author  Roger Riggs
 * @see     java.rmi.Naming#lookup(String)
 * @see     java.rmi.Naming#unbind(String)
 * @see     java.rmi.registry.Registry#lookup(String)
 * @see     java.rmi.registry.Registry#unbind(String)
 */
public class NotBoundException extends java.lang.Exception {

    /* indicate compatibility with JDK 1.1.x version of class */
    private static final long serialVersionUID = -1857741824849069317L;

    /**
     * Constructs a <code>NotBoundException</code> with no
     * specified detail message.
     * @since 1.1
     */
    public NotBoundException() {
        super();
    }

    /**
     * Constructs a <code>NotBoundException</code> with the specified
     * detail message.
     *
     * @param s the detail message
     * @since 1.1
     */
    public NotBoundException(String s) {
        super(s);
    }
}
