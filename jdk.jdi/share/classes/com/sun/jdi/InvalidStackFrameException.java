package com.sun.jdi;

/**
 * Thrown to indicate that the requested operation cannot be
 * completed because the specified stack frame is no longer valid.
 *
 * @author Gordon Hirsch
 * @since  1.3
 */
public class InvalidStackFrameException extends RuntimeException {

    private static final long serialVersionUID = -1919378296505827922L;

    public InvalidStackFrameException() {
        super();
    }

    public InvalidStackFrameException(String s) {
        super(s);
    }
}
