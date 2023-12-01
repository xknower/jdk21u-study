package com.sun.org.apache.xml.internal.security.parser;

import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;

public class XMLParserException extends XMLSecurityException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor XMLParserException
     *
     */
    public XMLParserException() {
        super();
    }

    /**
     * Constructor XMLParserException
     *
     * @param msgID
     */
    public XMLParserException(String msgID) {
        super(msgID);
    }

    /**
     * Constructor XMLParserException
     *
     * @param msgID
     * @param exArgs
     */
    public XMLParserException(String msgID, Object[] exArgs) {
        super(msgID, exArgs);
    }

    /**
     * Constructor XMLParserException
     *
     * @param originalException
     * @param msgID
     */
    public XMLParserException(Exception originalException, String msgID) {
        super(originalException, msgID);
    }

    /**
     * Constructor XMLParserException
     *
     * @param originalException
     * @param msgID
     * @param exArgs
     */
    public XMLParserException(
        Exception originalException, String msgID, Object[] exArgs
    ) {
        super(originalException, msgID, exArgs);
    }

}
