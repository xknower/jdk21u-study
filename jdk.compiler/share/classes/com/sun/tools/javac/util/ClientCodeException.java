package com.sun.tools.javac.util;

/**
 * An exception used for propagating exceptions found in client code
 * invoked from javac.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public class ClientCodeException extends RuntimeException {

    static final long serialVersionUID = -5674494409392415163L;

    public ClientCodeException(Throwable cause) {
        super(cause);
    }
}
