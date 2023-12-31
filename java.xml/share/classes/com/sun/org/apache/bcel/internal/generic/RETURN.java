package com.sun.org.apache.bcel.internal.generic;

/**
 * RETURN - Return from void method
 *
 * <PRE>
 * Stack: ... -&gt; &lt;empty&gt;
 * </PRE>
 */
public class RETURN extends ReturnInstruction {

    public RETURN() {
        super(com.sun.org.apache.bcel.internal.Const.RETURN);
    }

    /**
     * Call corresponding visitor method(s). The order is: Call visitor methods of implemented interfaces first, then call
     * methods according to the class hierarchy in descending order, i.e., the most specific visitXXX() call comes last.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitStackConsumer(this);
        v.visitReturnInstruction(this);
        v.visitRETURN(this);
    }
}
