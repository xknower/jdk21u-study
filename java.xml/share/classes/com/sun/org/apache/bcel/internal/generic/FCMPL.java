package com.sun.org.apache.bcel.internal.generic;

/**
 * FCMPL - Compare floats: value1 &lt; value2
 *
 * <PRE>
 * Stack: ..., value1, value2 -&gt; ..., result
 * </PRE>
 */
public class FCMPL extends Instruction implements TypedInstruction, StackProducer, StackConsumer {

    public FCMPL() {
        super(com.sun.org.apache.bcel.internal.Const.FCMPL, (short) 1);
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
        v.visitFCMPL(this);
    }

    /**
     * @return Type.FLOAT
     */
    @Override
    public Type getType(final ConstantPoolGen cp) {
        return Type.FLOAT;
    }
}
