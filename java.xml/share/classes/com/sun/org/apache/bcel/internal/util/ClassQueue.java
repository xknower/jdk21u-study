package com.sun.org.apache.bcel.internal.util;

import java.util.LinkedList;

import com.sun.org.apache.bcel.internal.classfile.JavaClass;

/**
 * Utility class implementing a (typesafe) queue of JavaClass objects.
 * @LastModified: Jan 2020
 */
public class ClassQueue {

    /**
     * @deprecated (since 6.0) will be made private; do not access
     */
    @Deprecated
    protected LinkedList<JavaClass> vec = new LinkedList<>(); // TODO not used externally

    public JavaClass dequeue() {
        return vec.removeFirst();
    }

    public boolean empty() {
        return vec.isEmpty();
    }

    public void enqueue(final JavaClass clazz) {
        vec.addLast(clazz);
    }

    @Override
    public String toString() {
        return vec.toString();
    }
}
