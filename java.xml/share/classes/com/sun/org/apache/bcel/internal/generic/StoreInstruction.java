package com.sun.org.apache.bcel.internal.generic;

/**
 * Denotes an unparameterized instruction to store a value into a local variable, e.g. ISTORE.
 */
public abstract class StoreInstruction extends LocalVariableInstruction implements PopInstruction {

    /**
     * Empty constructor needed for Instruction.readInstruction. Not to be used otherwise. tag and length are defined in
     * readInstruction and initFromFile, respectively.
     */
    StoreInstruction(final short canonTag, final short cTag) {
        super(canonTag, cTag);
    }

    /**
     * @param opcode Instruction opcode
     * @param cTag Instruction number for compact version, ASTORE_0, e.g.
     * @param n local variable index (unsigned short)
     */
    protected StoreInstruction(final short opcode, final short cTag, final int n) {
        super(opcode, cTag, n);
    }

    /**
     * Call corresponding visitor method(s). The order is: Call visitor methods of implemented interfaces first, then call
     * methods according to the class hierarchy in descending order, i.e., the most specific visitXXX() call comes last.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitPopInstruction(this);
        v.visitTypedInstruction(this);
        v.visitLocalVariableInstruction(this);
        v.visitStoreInstruction(this);
    }
}
