package com.sun.org.apache.bcel.internal.generic;

/**
 * LALOAD - Load long from array
 *
 * <PRE>
 * Stack: ..., arrayref, index -&gt; ..., value1, value2
 * </PRE>
 */
public class LALOAD extends ArrayInstruction implements StackProducer {

    /**
     * Load long from array
     */
    public LALOAD() {
        super(com.sun.org.apache.bcel.internal.Const.LALOAD);
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
        v.visitLALOAD(this);
    }
}
