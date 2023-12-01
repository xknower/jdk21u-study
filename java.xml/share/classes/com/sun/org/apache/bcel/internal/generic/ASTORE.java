package com.sun.org.apache.bcel.internal.generic;

/**
 * ASTORE - Store reference into local variable
 *
 * <PRE>
 * Stack ..., objectref -&gt; ...
 * </PRE>
 * @LastModified: Jan 2020
 */
public class ASTORE extends StoreInstruction {

    /**
     * Empty constructor needed for Instruction.readInstruction. Not to be used otherwise.
     */
    ASTORE() {
        super(com.sun.org.apache.bcel.internal.Const.ASTORE, com.sun.org.apache.bcel.internal.Const.ASTORE_0);
    }

    /**
     * Store reference into local variable
     *
     * @param n index of local variable
     */
    public ASTORE(final int n) {
        super(com.sun.org.apache.bcel.internal.Const.ASTORE, com.sun.org.apache.bcel.internal.Const.ASTORE_0, n);
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
        v.visitASTORE(this);
    }
}
