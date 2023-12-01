package com.sun.org.apache.bcel.internal.generic;

/**
 * DASTORE - Store into double array
 *
 * <PRE>
 * Stack: ..., arrayref, index, value.word1, value.word2 -&gt; ...
 * </PRE>
 */
public class DASTORE extends ArrayInstruction implements StackConsumer {

    /**
     * Store double into array
     */
    public DASTORE() {
        super(com.sun.org.apache.bcel.internal.Const.DASTORE);
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
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitDASTORE(this);
    }
}
