package com.sun.org.apache.bcel.internal.generic;

/**
 * FADD - Add floats
 *
 * <PRE>
 * Stack: ..., value1, value2 -&gt; result
 * </PRE>
 */
public class FADD extends ArithmeticInstruction {

    /**
     * Add floats
     */
    public FADD() {
        super(com.sun.org.apache.bcel.internal.Const.FADD);
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
        v.visitFADD(this);
    }
}
