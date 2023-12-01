package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.ExceptionConst;

/**
 * ANEWARRAY - Create new array of references
 *
 * <PRE>
 * Stack: ..., count -&gt; ..., arrayref
 * </PRE>
 */
public class ANEWARRAY extends CPInstruction implements LoadClass, AllocationInstruction, ExceptionThrower, StackConsumer, StackProducer {

    /**
     * Empty constructor needed for Instruction.readInstruction. Not to be used otherwise.
     */
    ANEWARRAY() {
    }

    public ANEWARRAY(final int index) {
        super(com.sun.org.apache.bcel.internal.Const.ANEWARRAY, index);
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
        v.visitANEWARRAY(this);
    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_CLASS_AND_INTERFACE_RESOLUTION, ExceptionConst.NEGATIVE_ARRAY_SIZE_EXCEPTION);
    }

    @Override
    public ObjectType getLoadClassType(final ConstantPoolGen cpg) {
        Type t = getType(cpg);
        if (t instanceof ArrayType) {
            t = ((ArrayType) t).getBasicType();
        }
        return t instanceof ObjectType ? (ObjectType) t : null;
    }
}
