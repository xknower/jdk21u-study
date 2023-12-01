package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.classfile.ConstantPool;

/**
 * Super class for the GET/PUTxxx family of instructions.
 */
public abstract class FieldInstruction extends FieldOrMethod {

    /**
     * Empty constructor needed for Instruction.readInstruction. Not to be used otherwise.
     */
    FieldInstruction() {
    }

    /**
     * @param index to constant pool
     */
    protected FieldInstruction(final short opcode, final int index) {
        super(opcode, index);
    }

    /**
     * @return name of referenced field.
     */
    public String getFieldName(final ConstantPoolGen cpg) {
        return getName(cpg);
    }

    /**
     * @return size of field (1 or 2)
     */
    protected int getFieldSize(final ConstantPoolGen cpg) {
        return Type.size(Type.getTypeSize(getSignature(cpg)));
    }

    /**
     * @return type of field
     */
    public Type getFieldType(final ConstantPoolGen cpg) {
        return Type.getType(getSignature(cpg));
    }

    /**
     * @return return type of referenced field
     */
    @Override
    public Type getType(final ConstantPoolGen cpg) {
        return getFieldType(cpg);
    }

    /**
     * @return mnemonic for instruction with symbolic references resolved
     */
    @Override
    public String toString(final ConstantPool cp) {
        return com.sun.org.apache.bcel.internal.Const.getOpcodeName(super.getOpcode()) + " " + cp.constantToString(super.getIndex(), com.sun.org.apache.bcel.internal.Const.CONSTANT_Fieldref);
    }
}
