package com.sun.org.apache.xalan.internal.xsltc.dom;

/**
 * @author Jacek Ambroziak
 * @author Santiago Pericas-Geertsen
 */
public interface Filter {
    public boolean test(int node);
}
