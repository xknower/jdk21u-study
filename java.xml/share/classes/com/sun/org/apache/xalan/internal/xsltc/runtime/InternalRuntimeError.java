package com.sun.org.apache.xalan.internal.xsltc.runtime;

/**
 * Class to express failed assertions and similar for the xsltc runtime.
 * As java.lang.AssertionError was introduced in JDK 1.4 we can't use that yet.
 *
 * @LastModified: Oct 2017
 */
public class InternalRuntimeError extends Error {
    private static final long serialVersionUID = 2802784919179095307L;

    public InternalRuntimeError(String message) {
        super(message);
    }

}
