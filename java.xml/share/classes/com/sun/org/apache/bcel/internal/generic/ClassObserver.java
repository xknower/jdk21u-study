package com.sun.org.apache.bcel.internal.generic;

/**
 * Implement this interface if you're interested in changes to a ClassGen object and register yourself with
 * addObserver().
 */
public interface ClassObserver {

    void notify(ClassGen clazz);
}
