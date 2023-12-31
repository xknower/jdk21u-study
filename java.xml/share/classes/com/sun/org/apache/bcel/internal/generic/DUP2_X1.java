package com.sun.org.apache.bcel.internal.generic;

/**
 * DUP2_X1 - Duplicate two top operand stack words and put three down
 *
 * <PRE>
 * Stack: ..., word3, word2, word1 -&gt; ..., word2, word1, word3, word2, word1
 * </PRE>
 */
public class DUP2_X1 extends StackInstruction {

    public DUP2_X1() {
        super(com.sun.org.apache.bcel.internal.Const.DUP2_X1);
    }

    /**
     * Call corresponding visitor method(s). The order is: Call visitor methods of implemented interfaces first, then call
     * methods according to the class hierarchy in descending order, i.e., the most specific visitXXX() call comes last.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitStackInstruction(this);
        v.visitDUP2_X1(this);
    }
}
