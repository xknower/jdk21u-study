package com.sun.org.apache.bcel.internal.generic;

/**
 * IASTORE - Store into int array
 *
 * <PRE>
 * Stack: ..., arrayref, index, value -&gt; ...
 * </PRE>
 */
public class IASTORE extends ArrayInstruction implements StackConsumer {

    /**
     * Store into int array
     */
    public IASTORE() {
        super(com.sun.org.apache.bcel.internal.Const.IASTORE);
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
        v.visitIASTORE(this);
    }
}
