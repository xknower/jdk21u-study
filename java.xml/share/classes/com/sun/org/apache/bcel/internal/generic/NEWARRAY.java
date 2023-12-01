package com.sun.org.apache.bcel.internal.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import com.sun.org.apache.bcel.internal.ExceptionConst;
import com.sun.org.apache.bcel.internal.util.ByteSequence;

/**
 * NEWARRAY - Create new array of basic type (int, short, ...)
 *
 * <PRE>
 * Stack: ..., count -&gt; ..., arrayref
 * </PRE>
 *
 * type must be one of T_INT, T_SHORT, ...
 * @LastModified: Jan 2020
 */
public class NEWARRAY extends Instruction implements AllocationInstruction, ExceptionThrower, StackProducer {

    private byte type;

    /**
     * Empty constructor needed for Instruction.readInstruction. Not to be used otherwise.
     */
    NEWARRAY() {
    }

    public NEWARRAY(final BasicType type) {
        this(type.getType());
    }

    public NEWARRAY(final byte type) {
        super(com.sun.org.apache.bcel.internal.Const.NEWARRAY, (short) 2);
        this.type = type;
    }

    /**
     * Call corresponding visitor method(s). The order is: Call visitor methods of implemented interfaces first, then call
     * methods according to the class hierarchy in descending order, i.e., the most specific visitXXX() call comes last.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitAllocationInstruction(this);
        v.visitExceptionThrower(this);
        v.visitStackProducer(this);
        v.visitNEWARRAY(this);
    }

    /**
     * Dump instruction as byte code to stream out.
     *
     * @param out Output stream
     */
    @Override
    public void dump(final DataOutputStream out) throws IOException {
        out.writeByte(super.getOpcode());
        out.writeByte(type);
    }

    @Override
    public Class<?>[] getExceptions() {
        return new Class<?>[] {ExceptionConst.NEGATIVE_ARRAY_SIZE_EXCEPTION};
    }

    /**
     * @return type of constructed array
     */
    public final Type getType() {
        return new ArrayType(BasicType.getType(type), 1);
    }

    /**
     * @return numeric code for basic element type
     */
    public final byte getTypecode() {
        return type;
    }

    /**
     * Read needed data (e.g. index) from file.
     */
    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        type = bytes.readByte();
        super.setLength(2);
    }

    /**
     * @return mnemonic for instruction
     */
    @Override
    public String toString(final boolean verbose) {
        return super.toString(verbose) + " " + com.sun.org.apache.bcel.internal.Const.getTypeName(type);
    }
}
