package com.sun.org.apache.bcel.internal.generic;

/**
 * DLOAD - Load double from local variable
 *
 * <PRE>
 * Stack ... -&gt; ..., result.word1, result.word2
 * </PRE>
 */
public class DLOAD extends LoadInstruction {

    /**
     * Empty constructor needed for Instruction.readInstruction. Not to be used otherwise.
     */
    DLOAD() {
        super(com.sun.org.apache.bcel.internal.Const.DLOAD, com.sun.org.apache.bcel.internal.Const.DLOAD_0);
    }

    /**
     * Load double from local variable
     *
     * @param n index of local variable
     */
    public DLOAD(final int n) {
        super(com.sun.org.apache.bcel.internal.Const.DLOAD, com.sun.org.apache.bcel.internal.Const.DLOAD_0, n);
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
        v.visitDLOAD(this);
    }
}
