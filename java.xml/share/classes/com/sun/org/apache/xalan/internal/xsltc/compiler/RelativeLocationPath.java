package com.sun.org.apache.xalan.internal.xsltc.compiler;

/**
 * @author Jacek Ambroziak
 * @author Santiago Pericas-Geertsen
 */
abstract class RelativeLocationPath extends Expression {
    public abstract int getAxis();
    public abstract void setAxis(int axis);
}
