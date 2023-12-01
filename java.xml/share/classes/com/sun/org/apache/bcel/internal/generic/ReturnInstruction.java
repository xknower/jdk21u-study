package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.ExceptionConst;

/**
 * Super class for the xRETURN family of instructions.
 *
 * @LastModified: Feb 2023
 */
public abstract class ReturnInstruction extends Instruction implements ExceptionThrower, TypedInstruction, StackConsumer {

    /**
     * Empty constructor needed for Instruction.readInstruction. Not to be used otherwise.
     */
    ReturnInstruction() {
    }

    /**
     * @param opcode of instruction
     */
    protected ReturnInstruction(final short opcode) {
        super(opcode, (short) 1);
    }

    @Override
    public Class<?>[] getExceptions() {
        return new Class<?>[] {ExceptionConst.ILLEGAL_MONITOR_STATE};
    }

    public Type getType() {
        final short opcode = super.getOpcode();
        switch (opcode) {
        case Const.IRETURN:
            return Type.INT;
        case Const.LRETURN:
            return Type.LONG;
        case Const.FRETURN:
            return Type.FLOAT;
        case Const.DRETURN:
            return Type.DOUBLE;
        case Const.ARETURN:
            return Type.OBJECT;
        case Const.RETURN:
            return Type.VOID;
        default: // Never reached
            throw new ClassGenException("Unknown type " + opcode);
        }
    }

    /**
     * @return type associated with the instruction
     */
    @Override
    public Type getType(final ConstantPoolGen cp) {
        return getType();
    }
}
