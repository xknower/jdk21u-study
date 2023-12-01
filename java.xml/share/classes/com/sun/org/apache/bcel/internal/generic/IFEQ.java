package com.sun.org.apache.bcel.internal.generic;

/**
 * IFEQ - Branch if int comparison with zero succeeds
 *
 * <PRE>
 * Stack: ..., value -&gt; ...
 * </PRE>
 */
public class IFEQ extends IfInstruction {

    /**
     * Empty constructor needed for Instruction.readInstruction. Not to be used otherwise.
     */
    IFEQ() {
    }

    public IFEQ(final InstructionHandle target) {
        super(com.sun.org.apache.bcel.internal.Const.IFEQ, target);
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
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIFEQ(this);
    }

    /**
     * @return negation of instruction, e.g. IFEQ.negate() == IFNE
     */
    @Override
    public IfInstruction negate() {
        return new IFNE(super.getTarget());
    }
}
