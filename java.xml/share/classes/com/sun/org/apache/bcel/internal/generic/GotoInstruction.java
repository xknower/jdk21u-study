package com.sun.org.apache.bcel.internal.generic;

/**
 * Super class for GOTO
 */
public abstract class GotoInstruction extends BranchInstruction implements UnconditionalBranch {

    /**
     * Empty constructor needed for Instruction.readInstruction. Not to be used otherwise.
     */
    GotoInstruction() {
    }

    GotoInstruction(final short opcode, final InstructionHandle target) {
        super(opcode, target);
    }
}
