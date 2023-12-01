package com.sun.org.apache.bcel.internal.generic;

/**
 * ALOAD - Load reference from local variable
 *
 * <PRE>
 * Stack: ... -&gt; ..., objectref
 * </PRE>
 * @LastModified: Jan 2020
 */
public class ALOAD extends LoadInstruction {

    /**
     * Empty constructor needed for Instruction.readInstruction. Not to be used otherwise.
     */
    ALOAD() {
        super(com.sun.org.apache.bcel.internal.Const.ALOAD, com.sun.org.apache.bcel.internal.Const.ALOAD_0);
    }

    /**
     * Load reference from local variable
     *
     * @param n index of local variable
     */
    public ALOAD(final int n) {
        super(com.sun.org.apache.bcel.internal.Const.ALOAD, com.sun.org.apache.bcel.internal.Const.ALOAD_0, n);
    }

    /**
     * Call corresponding visitor method(s). The order is: Call visitor methods of implemented interfaces first, then call
     * methods according to the class hierarchy in descending order, i.e., the most specific visitXXX() call comes last.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitALOAD(this);
    }
}
