package com.sun.org.apache.bcel.internal.generic;

/**
 * IF_ICMPGT - Branch if int comparison succeeds
 *
 * <PRE>
 * Stack: ..., value1, value2 -&gt; ...
 * </PRE>
 */
public class IF_ICMPGT extends IfInstruction {

    /**
     * Empty constructor needed for Instruction.readInstruction. Not to be used otherwise.
     */
    IF_ICMPGT() {
    }

    public IF_ICMPGT(final InstructionHandle target) {
        super(com.sun.org.apache.bcel.internal.Const.IF_ICMPGT, target);
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
        v.visitIF_ICMPGT(this);
    }

    /**
     * @return negation of instruction
     */
    @Override
    public IfInstruction negate() {
        return new IF_ICMPLE(super.getTarget());
    }
}
