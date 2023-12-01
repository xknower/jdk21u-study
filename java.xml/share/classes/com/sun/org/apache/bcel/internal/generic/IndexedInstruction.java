package com.sun.org.apache.bcel.internal.generic;

/**
 * Denote entity that refers to an index, e.g. local variable instructions, RET, CPInstruction, etc.
 */
public interface IndexedInstruction {

    int getIndex();

    void setIndex(int index);
}
