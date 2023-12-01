package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import java.util.Stack;

/**
 * @author Jacek Ambroziak
 * @author Santiago Pericas-Geertsen
 * @LastModified: Oct 2017
 */
public final class StringStack extends Stack<String> {
    static final long serialVersionUID = -1506910875640317898L;
    public String peekString() {
        return super.peek();
    }

    public String popString() {
        return super.pop();
    }

    public String pushString(String val) {
        return super.push(val);
    }
}
