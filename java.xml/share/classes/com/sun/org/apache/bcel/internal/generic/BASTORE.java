package com.sun.org.apache.bcel.internal.generic;

/**
 * BASTORE - Store into byte or boolean array
 *
 * <PRE>
 * Stack: ..., arrayref, index, value -&gt; ...
 * </PRE>
 */
public class BASTORE extends ArrayInstruction implements StackConsumer {

    /**
     * Store byte or boolean into array
     */
    public BASTORE() {
        super(com.sun.org.apache.bcel.internal.Const.BASTORE);
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
        v.visitBASTORE(this);
    }
}
