package com.sun.org.apache.bcel.internal.generic;

/**
 * DADD - Add doubles
 *
 * <PRE>
 * Stack: ..., value1.word1, value1.word2, value2.word1, value2.word2 -&gt;
 * </PRE>
 *
 * ..., result.word1, result1.word2
 */
public class DADD extends ArithmeticInstruction {

    /**
     * Add doubles
     */
    public DADD() {
        super(com.sun.org.apache.bcel.internal.Const.DADD);
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
        v.visitDADD(this);
    }
}
