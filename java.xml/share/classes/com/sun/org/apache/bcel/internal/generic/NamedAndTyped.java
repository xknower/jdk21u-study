package com.sun.org.apache.bcel.internal.generic;

/**
 * Denote entity that has both name and type. This is true for local variables, methods and fields.
 */
public interface NamedAndTyped {

    String getName();

    Type getType();

    void setName(String name);

    void setType(Type type);
}
