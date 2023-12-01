package com.sun.org.apache.bcel.internal.generic;

/**
 * LCONST - Push 0 or 1, other values cause an exception
 *
 * <PRE>
 * Stack: ... -&gt; ...,
 * </PRE>
 */
public class LCONST extends Instruction implements ConstantPushInstruction {

    private final long value;

    /**
     * Empty constructor needed for Instruction.readInstruction. Not to be used otherwise.
     */
    LCONST() {
        this(0);
    }

    public LCONST(final long l) {
        super(com.sun.org.apache.bcel.internal.Const.LCONST_0, (short) 1);
        if (l == 0) {
            super.setOpcode(com.sun.org.apache.bcel.internal.Const.LCONST_0);
        } else if (l == 1) {
            super.setOpcode(com.sun.org.apache.bcel.internal.Const.LCONST_1);
        } else {
            throw new ClassGenException("LCONST can be used only for 0 and 1: " + l);
        }
        value = l;
    }

    /**
     * Call corresponding visitor method(s). The order is: Call visitor methods of implemented interfaces first, then call
     * methods according to the class hierarchy in descending order, i.e., the most specific visitXXX() call comes last.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitPushInstruction(this);
        v.visitStackProducer(this);
        v.visitTypedInstruction(this);
        v.visitConstantPushInstruction(this);
        v.visitLCONST(this);
    }

    /**
     * @return Type.LONG
     */
    @Override
    public Type getType(final ConstantPoolGen cp) {
        return Type.LONG;
    }

    @Override
    public Number getValue() {
        return Long.valueOf(value);
    }
}
