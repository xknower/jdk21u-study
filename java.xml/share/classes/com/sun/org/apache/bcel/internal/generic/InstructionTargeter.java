package com.sun.org.apache.bcel.internal.generic;

/**
 * Denote that a class targets InstructionHandles within an InstructionList. Namely the following implementers:
 *
 * @see BranchHandle
 * @see LocalVariableGen
 * @see CodeExceptionGen
 */
public interface InstructionTargeter {

    // static final InstructionTargeter[] EMPTY_ARRAY = new InstructionTargeter[0];

    /**
     * Checks whether this targeter targets the specified instruction handle.
     */
    boolean containsTarget(InstructionHandle ih);

    /**
     * Replaces the target of this targeter from this old handle to the new handle.
     *
     * @param oldIh the old handle
     * @param newIh the new handle
     * @throws ClassGenException if oldIh is not targeted by this object
     */
    void updateTarget(InstructionHandle oldIh, InstructionHandle newIh) throws ClassGenException;
}
