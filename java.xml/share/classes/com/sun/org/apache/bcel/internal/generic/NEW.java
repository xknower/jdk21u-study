package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.ExceptionConst;

/**
 * NEW - Create new object
 *
 * <PRE>
 * Stack: ... -&gt; ..., objectref
 * </PRE>
 */
public class NEW extends CPInstruction implements LoadClass, AllocationInstruction, ExceptionThrower, StackProducer {

    /**
     * Empty constructor needed for Instruction.readInstruction. Not to be used otherwise.
     */
    NEW() {
    }

    public NEW(final int index) {
        super(com.sun.org.apache.bcel.internal.Const.NEW, index);
    }

    /**
     * Call corresponding visitor method(s). The order is: Call visitor methods of implemented interfaces first, then call
     * methods according to the class hierarchy in descending order, i.e., the most specific visitXXX() call comes last.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitLoadClass(this);
        v.visitAllocationInstruction(this);
        v.visitExceptionThrower(this);
        v.visitStackProducer(this);
        v.visitTypedInstruction(this);
        v.visitCPInstruction(this);
        v.visitNEW(this);
    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_CLASS_AND_INTERFACE_RESOLUTION, ExceptionConst.ILLEGAL_ACCESS_ERROR,
            ExceptionConst.INSTANTIATION_ERROR);
    }

    @Override
    public ObjectType getLoadClassType(final ConstantPoolGen cpg) {
        return (ObjectType) getType(cpg);
    }
}
