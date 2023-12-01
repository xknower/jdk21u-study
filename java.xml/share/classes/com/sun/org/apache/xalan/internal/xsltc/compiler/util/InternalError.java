package com.sun.org.apache.xalan.internal.xsltc.compiler.util;
/**
 * Marks a class of errors in which XSLTC has reached some incorrect internal
 * state from which it cannot recover.
 *
 * @LastModified: Oct 2017
 */
public class InternalError extends Error {
    private static final long serialVersionUID = -6690855975016554786L;

    /**
     * Construct an <code>InternalError</code> with the specified error message.
     * @param msg the error message
     */
    public InternalError(String msg) {
        super(msg);
    }
}
