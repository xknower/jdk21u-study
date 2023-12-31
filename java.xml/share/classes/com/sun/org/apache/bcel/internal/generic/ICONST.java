package com.sun.org.apache.bcel.internal.generic;

/**
 * ICONST - Push value between -1, ..., 5, other values cause an exception
 *
 * <PRE>
 * Stack: ... -&gt; ...,
 * </PRE>
 *
 */
public class ICONST extends Instruction implements ConstantPushInstruction {

    private final int value;

    /**
     * Empty constructor needed for Instruction.readInstruction. Not to be used otherwise.
     */
    ICONST() {
        this(0);
    }

    public ICONST(final int i) {
        super(com.sun.org.apache.bcel.internal.Const.ICONST_0, (short) 1);
        if (i < -1 || i > 5) {
            throw new ClassGenException("ICONST can be used only for value between -1 and 5: " + i);
        }
        super.setOpcode((short) (com.sun.org.apache.bcel.internal.Const.ICONST_0 + i)); // Even works for i == -1
        value = i;
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
        v.visitICONST(this);
    }

    /**
     * @return Type.INT
     */
    @Override
    public Type getType(final ConstantPoolGen cp) {
        return Type.INT;
    }

    @Override
    public Number getValue() {
        return Integer.valueOf(value);
    }
}
