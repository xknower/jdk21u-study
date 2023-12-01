package com.sun.org.apache.bcel.internal.generic;

/**
 * IFLT - Branch if int comparison with zero succeeds
 *
 * <PRE>
 * Stack: ..., value -&gt; ...
 * </PRE>
 */
public class IFLT extends IfInstruction {

    /**
     * Empty constructor needed for Instruction.readInstruction. Not to be used otherwise.
     */
    IFLT() {
    }

    public IFLT(final InstructionHandle target) {
        super(com.sun.org.apache.bcel.internal.Const.IFLT, target);
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
        v.visitIFLT(this);
    }

    /**
     * @return negation of instruction
     */
    @Override
    public IfInstruction negate() {
        return new IFGE(super.getTarget());
    }
}
