package java.beans;

import java.io.Serial;

/**
 * A PropertyVetoException is thrown when a proposed change to a
 * property represents an unacceptable value.
 * @since 1.1
 */

public
class PropertyVetoException extends Exception {

    /**
     * Use serialVersionUID from JDK 1.7 for interoperability.
     */
    @Serial
    private static final long serialVersionUID = 129596057694162164L;

    /**
     * Constructs a {@code PropertyVetoException} with a
     * detailed message.
     *
     * @param mess Descriptive message
     * @param evt A PropertyChangeEvent describing the vetoed change.
     */
    public PropertyVetoException(String mess, PropertyChangeEvent evt) {
        super(mess);
        this.evt = evt;
    }

     /**
     * Gets the vetoed {@code PropertyChangeEvent}.
     *
     * @return A PropertyChangeEvent describing the vetoed change.
     */
    public PropertyChangeEvent getPropertyChangeEvent() {
        return evt;
    }

    /**
     * A PropertyChangeEvent describing the vetoed change.
     * @serial
     */
    private PropertyChangeEvent evt;
}
