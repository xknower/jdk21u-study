package com.sun.org.apache.xalan.internal.xsltc.dom;

/**
 * @author Jacek Ambroziak
 * @author Santiago Pericas-Geertsen
 */
public final class EmptyFilter implements Filter {
    public boolean test(int node) {
        return true;
    }
}
