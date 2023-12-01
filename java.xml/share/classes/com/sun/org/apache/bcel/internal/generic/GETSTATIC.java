package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.ExceptionConst;

/**
 * GETSTATIC - Fetch static field from class
 *
 * <PRE>
 * Stack: ..., -&gt; ..., value
 * </PRE>
 *
 * OR
 *
 * <PRE>
 * Stack: ..., -&gt; ..., value.word1, value.word2
 * </PRE>
 */
public class GETSTATIC extends FieldInstruction implements PushInstruction, ExceptionThrower {

    /**
     * Empty constructor needed for Instruction.readInstruction. Not to be used otherwise.
     */
    GETSTATIC() {
    }

    public GETSTATIC(final int index) {
        super(Const.GETSTATIC, index);
    }

    /**
     * Call corresponding visitor method(s). The order is: Call visitor methods of implemented interfaces first, then call
     * methods according to the class hierarchy in descending order, i.e., the most specific visitXXX() call comes last.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitPushInstruction(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitLoadClass(this);
        v.visitCPInstruction(this);
        v.visitFieldOrMethod(this);
        v.visitFieldInstruction(this);
        v.visitGETSTATIC(this);
    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_FIELD_AND_METHOD_RESOLUTION, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
    }

    @Override
    public int produceStack(final ConstantPoolGen cpg) {
        return getFieldSize(cpg);
    }
}
