package com.sun.org.apache.bcel.internal.generic;

/**
 * DSTORE - Store double into local variable
 *
 * <pre>
 * Stack: ..., value.word1, value.word2 -&gt; ...
 * </PRE>
 */
public class DSTORE extends StoreInstruction {

    /**
     * Empty constructor needed for Instruction.readInstruction. Not to be used otherwise.
     */
    DSTORE() {
        super(com.sun.org.apache.bcel.internal.Const.DSTORE, com.sun.org.apache.bcel.internal.Const.DSTORE_0);
    }

    /**
     * Store double into local variable
     *
     * @param n index of local variable
     */
    public DSTORE(final int n) {
        super(com.sun.org.apache.bcel.internal.Const.DSTORE, com.sun.org.apache.bcel.internal.Const.DSTORE_0, n);
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
        v.visitDSTORE(this);
    }
}
