package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.ExceptionConst;

/**
 * IREM - Remainder of int
 *
 * <PRE>
 * Stack: ..., value1, value2 -&gt; result
 * </PRE>
 * @LastModified: Jan 2020
 */
public class IREM extends ArithmeticInstruction implements ExceptionThrower {

    /**
     * Remainder of ints
     */
    public IREM() {
        super(com.sun.org.apache.bcel.internal.Const.IREM);
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
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitIREM(this);
    }

    /**
     * @return exceptions this instruction may cause
     */
    @Override
    public Class<?>[] getExceptions() {
        return new Class<?>[] {ExceptionConst.ARITHMETIC_EXCEPTION};
    }
}
