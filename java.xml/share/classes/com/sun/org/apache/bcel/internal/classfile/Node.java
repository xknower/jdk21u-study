package com.sun.org.apache.bcel.internal.classfile;

/**
 * Denote class to have an accept method();
 */
public interface Node {

    void accept(Visitor obj);
}
