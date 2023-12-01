package com.sun.org.apache.bcel.internal.generic;

/**
 * BALOAD - Load byte or boolean from array
 *
 * <PRE>
 * Stack: ..., arrayref, index -&gt; ..., value
 * </PRE>
 */
public class BALOAD extends ArrayInstruction implements StackProducer {

    /**
     * Load byte or boolean from array
     */
    public BALOAD() {
        super(com.sun.org.apache.bcel.internal.Const.BALOAD);
    }

    /**
     * Call corresponding visitor method(s). The order is: Call visitor methods of implemented interfaces first, then call
     * methods according to the class hierarchy in descending order, i.e., the most specific visitXXX() call comes last.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitBALOAD(this);
    }
}
