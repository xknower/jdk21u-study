package com.sun.org.apache.bcel.internal.generic;

/**
 * ISHL - Arithmetic shift left int
 *
 * <PRE>
 * Stack: ..., value1, value2 -&gt; ..., result
 * </PRE>
 */
public class ISHL extends ArithmeticInstruction {

    public ISHL() {
        super(com.sun.org.apache.bcel.internal.Const.ISHL);
    }

    /**
     * Call corresponding visitor method(s). The order is: Call visitor methods of implemented interfaces first, then call
     * methods according to the class hierarchy in descending order, i.e., the most specific visitXXX() call comes last.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitISHL(this);
    }
}
