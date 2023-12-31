package com.sun.org.apache.bcel.internal.generic;

/**
 * I2B - Convert int to byte
 *
 * <PRE>
 * Stack: ..., value -&gt; ..., result
 * </PRE>
 */
public class I2B extends ConversionInstruction {

    /**
     * Convert int to byte
     */
    public I2B() {
        super(com.sun.org.apache.bcel.internal.Const.I2B);
    }

    /**
     * Call corresponding visitor method(s). The order is: Call visitor methods of implemented interfaces first, then call
     * methods according to the class hierarchy in descending order, i.e., the most specific visitXXX() call comes last.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitConversionInstruction(this);
        v.visitI2B(this);
    }
}
