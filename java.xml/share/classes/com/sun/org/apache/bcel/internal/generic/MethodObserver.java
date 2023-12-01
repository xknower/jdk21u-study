package com.sun.org.apache.bcel.internal.generic;

/**
 * Implement this interface if you're interested in changes to a MethodGen object and register yourself with
 * addObserver().
 */
public interface MethodObserver {

    void notify(MethodGen method);
}
