package com.sun.org.apache.bcel.internal.generic;

/**
 * Implement this interface if you're interested in changes to an InstructionList object and register yourself with
 * addObserver().
 */
public interface InstructionListObserver {

    void notify(InstructionList list);
}
