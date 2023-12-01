package com.sun.org.apache.bcel.internal.generic;

/**
 * Thrown on internal exceptions.
 */
public class ClassGenException extends RuntimeException {

    private static final long serialVersionUID = 7247369755051242791L;

    public ClassGenException() {
    }

    public ClassGenException(final String s) {
        super(s);
    }

    public ClassGenException(final String s, final Throwable initCause) {
        super(s, initCause);
    }
}
