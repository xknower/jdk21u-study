package com.sun.org.apache.bcel.internal.generic;

/**
 * IMPDEP2 - Implementation dependent
 */
public class IMPDEP2 extends Instruction {

    public IMPDEP2() {
        super(com.sun.org.apache.bcel.internal.Const.IMPDEP2, (short) 1);
    }

    /**
     * Call corresponding visitor method(s). The order is: Call visitor methods of implemented interfaces first, then call
     * methods according to the class hierarchy in descending order, i.e., the most specific visitXXX() call comes last.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitIMPDEP2(this);
    }
}
