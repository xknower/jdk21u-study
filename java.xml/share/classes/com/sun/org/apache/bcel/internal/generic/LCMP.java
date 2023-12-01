package com.sun.org.apache.bcel.internal.generic;

/**
 * LCMP - Compare longs:
 *
 * <PRE>
 * Stack: ..., value1.word1, value1.word2, value2.word1, value2.word2 -&gt; ..., result &lt;= -1, 0, 1&gt;
 * </PRE>
 *
 */
public class LCMP extends Instruction implements TypedInstruction, StackProducer, StackConsumer {

    public LCMP() {
        super(com.sun.org.apache.bcel.internal.Const.LCMP, (short) 1);
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
        v.visitLCMP(this);
    }

    /**
     * @return Type.LONG
     */
    @Override
    public Type getType(final ConstantPoolGen cp) {
        return Type.LONG;
    }
}
