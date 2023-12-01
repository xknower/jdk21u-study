package com.sun.org.apache.bcel.internal.generic;

/**
 * FCONST - Push 0.0, 1.0 or 2.0, other values cause an exception
 *
 * <PRE>
 * Stack: ... -&gt; ...,
 * </PRE>
 * @LastModified: Jan 2020
 */
public class FCONST extends Instruction implements ConstantPushInstruction {

    private final float value;

    /**
     * Empty constructor needed for Instruction.readInstruction. Not to be used otherwise.
     */
    FCONST() {
        this(0);
    }

    public FCONST(final float f) {
        super(com.sun.org.apache.bcel.internal.Const.FCONST_0, (short) 1);
        if (f == 0.0) {
            super.setOpcode(com.sun.org.apache.bcel.internal.Const.FCONST_0);
        } else if (f == 1.0) {
            super.setOpcode(com.sun.org.apache.bcel.internal.Const.FCONST_1);
        } else if (f == 2.0) {
            super.setOpcode(com.sun.org.apache.bcel.internal.Const.FCONST_2);
        } else {
            throw new ClassGenException("FCONST can be used only for 0.0, 1.0 and 2.0: " + f);
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
        v.visitFCONST(this);
    }

    /**
     * @return Type.FLOAT
     */
    @Override
    public Type getType(final ConstantPoolGen cp) {
        return Type.FLOAT;
    }

    @Override
    public Number getValue() {
        return Float.valueOf(value);
    }
}
