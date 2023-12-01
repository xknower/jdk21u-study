package com.sun.org.apache.bcel.internal.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import com.sun.org.apache.bcel.internal.classfile.Constant;
import com.sun.org.apache.bcel.internal.classfile.ConstantClass;
import com.sun.org.apache.bcel.internal.classfile.ConstantPool;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import com.sun.org.apache.bcel.internal.util.ByteSequence;

/**
 * Abstract super class for instructions that use an index into the constant pool such as LDC, INVOKEVIRTUAL, etc.
 *
 * @see ConstantPoolGen
 * @see LDC
 * @see INVOKEVIRTUAL
 *
 * @LastModified: Jan 2020
 */
public abstract class CPInstruction extends Instruction implements TypedInstruction, IndexedInstruction {

    /**
     * @deprecated (since 6.0) will be made private; do not access directly, use getter/setter
     */
    @Deprecated
    protected int index; // index to constant pool

    /**
     * Empty constructor needed for Instruction.readInstruction. Not to be used otherwise.
     */
    CPInstruction() {
    }

    /**
     * @param index to constant pool
     */
    protected CPInstruction(final short opcode, final int index) {
        super(opcode, (short) 3);
        setIndex(index);
    }

    /**
     * Dump instruction as byte code to stream out.
     *
     * @param out Output stream
     */
    @Override
    public void dump(final DataOutputStream out) throws IOException {
        out.writeByte(super.getOpcode());
        out.writeShort(index);
    }

    /**
     * @return index in constant pool referred by this instruction.
     */
    @Override
    public final int getIndex() {
        return index;
    }

    /**
     * @return type related with this instruction.
     */
    @Override
    public Type getType(final ConstantPoolGen cpg) {
        final ConstantPool cp = cpg.getConstantPool();
        String name = cp.getConstantString(index, com.sun.org.apache.bcel.internal.Const.CONSTANT_Class);
        if (!name.startsWith("[")) {
            name = "L" + name + ";";
        }
        return Type.getType(name);
    }

    /**
     * Read needed data (i.e., index) from file.
     *
     * @param bytes input stream
     * @param wide wide prefix?
     */
    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        setIndex(bytes.readUnsignedShort());
        super.setLength(3);
    }

    /**
     * Set the index to constant pool.
     *
     * @param index in constant pool.
     */
    @Override
    public void setIndex(final int index) { // TODO could be package-protected?
        if (index < 0) {
            throw new ClassGenException("Negative index value: " + index);
        }
        this.index = index;
    }

    /**
     * Long output format:
     *
     * &lt;name of opcode&gt; "["&lt;opcode number&gt;"]" "("&lt;length of instruction&gt;")" "&lt;"&lt; constant pool
     * index&gt;"&gt;"
     *
     * @param verbose long/short format switch
     * @return mnemonic for instruction
     */
    @Override
    public String toString(final boolean verbose) {
        return super.toString(verbose) + " " + index;
    }

    /**
     * @return mnemonic for instruction with symbolic references resolved
     */
    @Override
    public String toString(final ConstantPool cp) {
        final Constant c = cp.getConstant(index);
        String str = cp.constantToString(c);
        if (c instanceof ConstantClass) {
            str = Utility.packageToPath(str);
        }
        return com.sun.org.apache.bcel.internal.Const.getOpcodeName(super.getOpcode()) + " " + str;
    }
}
