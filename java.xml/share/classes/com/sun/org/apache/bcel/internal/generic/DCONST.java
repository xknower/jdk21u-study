package com.sun.org.apache.bcel.internal.generic;

/**
 * DCONST - Push 0.0 or 1.0, other values cause an exception
 *
 * <PRE>
 * Stack: ... -&gt; ...,
 * </PRE>
 * @LastModified: Jan 2020
 */
public class DCONST extends Instruction implements ConstantPushInstruction {

    private final double value;

    /**
     * Empty constructor needed for Instruction.readInstruction. Not to be used otherwise.
     */
    DCONST() {
        this(0);
    }

    public DCONST(final double f) {
        super(com.sun.org.apache.bcel.internal.Const.DCONST_0, (short) 1);
        if (f == 0.0) {
            super.setOpcode(com.sun.org.apache.bcel.internal.Const.DCONST_0);
        } else if (f == 1.0) {
            super.setOpcode(com.sun.org.apache.bcel.internal.Const.DCONST_1);
        } else {
            throw new ClassGenException("DCONST can be used only for 0.0 and 1.0: " + f);
        }
        value = f;
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
        v.visitDCONST(this);
    }

    /**
     * @return Type.DOUBLE
     */
    @Override
    public Type getType(final ConstantPoolGen cp) {
        return Type.DOUBLE;
    }

    @Override
    public Number getValue() {
        return Double.valueOf(value);
    }
}
