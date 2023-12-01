package com.sun.org.apache.bcel.internal.util;

import java.util.Stack;

import com.sun.org.apache.bcel.internal.classfile.JavaClass;

/**
 * Utility class implementing a (typesafe) stack of JavaClass objects.
 *
 * @see Stack
 */
public class ClassStack {

    private final Stack<JavaClass> stack = new Stack<>();

    public boolean empty() {
        return stack.empty();
    }

    public JavaClass pop() {
        return stack.pop();
    }

    public void push(final JavaClass clazz) {
        stack.push(clazz);
    }

    public JavaClass top() {
        return stack.peek();
    }
}
