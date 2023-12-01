package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.ExceptionConst;

/**
 * LDIV - Divide longs
 *
 * <PRE>
 * Stack: ..., value1.word1, value1.word2, value2.word1, value2.word2 -&gt;
 * </PRE>
 *
 * ..., result.word1, result.word2
 * @LastModified: Jan 2020
 */
public class LDIV extends ArithmeticInstruction implements ExceptionThrower {

    public LDIV() {
        super(com.sun.org.apache.bcel.internal.Const.LDIV);
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
        v.visitLDIV(this);
    }

    @Override
    public Class<?>[] getExceptions() {
        return new Class<?>[] {ExceptionConst.ARITHMETIC_EXCEPTION};
    }
}
