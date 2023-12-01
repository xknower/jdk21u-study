package com.sun.org.apache.bcel.internal.generic;

/**
 * FLOAD - Load float from local variable
 *
 * <PRE>
 * Stack ... -&gt; ..., result
 * </PRE>
 */
public class FLOAD extends LoadInstruction {

    /**
     * Empty constructor needed for Instruction.readInstruction. Not to be used otherwise.
     */
    FLOAD() {
        super(com.sun.org.apache.bcel.internal.Const.FLOAD, com.sun.org.apache.bcel.internal.Const.FLOAD_0);
    }

    /**
     * Load float from local variable
     *
     * @param n index of local variable
     */
    public FLOAD(final int n) {
        super(com.sun.org.apache.bcel.internal.Const.FLOAD, com.sun.org.apache.bcel.internal.Const.FLOAD_0, n);
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
        v.visitFLOAD(this);
    }
}
