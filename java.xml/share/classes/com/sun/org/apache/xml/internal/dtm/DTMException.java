package com.sun.org.apache.xml.internal.dtm;

/**
 * This class specifies an exceptional condition that occurred
 * in the DTM module.
 */
public class DTMException extends RuntimeException {
    static final long serialVersionUID = -775576419181334734L;

    /**
     * Create a new DTMException.
     *
     * @param message The error or warning message.
     */
    public DTMException(String message) {
        super(message);
    }

    /**
     * Create a new DTMException wrapping an existing exception.
     *
     * @param e The exception to be wrapped.
     */
    public DTMException(Throwable e) {
        super(e);
    }

    /**
     * Wrap an existing exception in a DTMException.
     *
     * <p>This is used for throwing processor exceptions before
     * the processing has started.</p>
     *
     * @param message The error or warning message, or null to
     *                use the message from the embedded exception.
     * @param e Any exception
     */
    public DTMException(String message, Throwable e) {
        super(message, e);
    }
    }
