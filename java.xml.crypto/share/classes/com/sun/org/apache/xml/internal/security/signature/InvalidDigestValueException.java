package com.sun.org.apache.xml.internal.security.signature;

/**
 * Raised when the computed hash value doesn't match the given <i>DigestValue</i>.
 * Additional human readable info is passed to the constructor -- this being the benefit
 * of raising an exception or returning a value.
 *
 */
public class InvalidDigestValueException extends XMLSignatureException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor InvalidDigestValueException
     *
     */
    public InvalidDigestValueException() {
        super();
    }

    /**
     * Constructor InvalidDigestValueException
     *
     * @param msgID
     */
    public InvalidDigestValueException(String msgID) {
        super(msgID);
    }

    /**
     * Constructor InvalidDigestValueException
     *
     * @param msgID
     * @param exArgs
     */
    public InvalidDigestValueException(String msgID, Object[] exArgs) {
        super(msgID, exArgs);
    }

    /**
     * Constructor InvalidDigestValueException
     *
     * @param originalException
     * @param msgID
     */
    public InvalidDigestValueException(Exception originalException, String msgID) {
        super(originalException, msgID);
    }

    @Deprecated
    public InvalidDigestValueException(String msgID, Exception originalException) {
        this(originalException, msgID);
    }

    /**
     * Constructor InvalidDigestValueException
     *
     * @param originalException
     * @param msgID
     * @param exArgs
     */
    public InvalidDigestValueException(Exception originalException, String msgID, Object[] exArgs) {
        super(originalException, msgID, exArgs);
    }

    @Deprecated
    public InvalidDigestValueException(String msgID, Object[] exArgs, Exception originalException) {
        this(originalException, msgID, exArgs);
    }
}
