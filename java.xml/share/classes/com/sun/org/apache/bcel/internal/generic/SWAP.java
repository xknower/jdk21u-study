package com.sun.org.apache.bcel.internal.generic;

/**
 * SWAP - Swa top operand stack word
 *
 * <PRE>
 * Stack: ..., word2, word1 -&gt; ..., word1, word2
 * </PRE>
 */
public class SWAP extends StackInstruction implements StackConsumer, StackProducer {

    public SWAP() {
        super(com.sun.org.apache.bcel.internal.Const.SWAP);
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
        v.visitStackProducer(this);
        v.visitStackInstruction(this);
        v.visitSWAP(this);
    }
}
