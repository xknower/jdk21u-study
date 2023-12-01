package com.sun.org.apache.bcel.internal.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import com.sun.org.apache.bcel.internal.util.ByteSequence;

/**
 * RET - Return from subroutine
 *
 * <PRE>
 * Stack: ... -&gt; ...
 * </PRE>
 */
public class RET extends Instruction implements IndexedInstruction, TypedInstruction {

    private boolean wide;
    private int index; // index to local variable containg the return address

    /**
     * Empty constructor needed for Instruction.readInstruction. Not to be used otherwise.
     */
    RET() {
    }

    public RET(final int index) {
        super(com.sun.org.apache.bcel.internal.Const.RET, (short) 2);
        setIndex(index); // May set wide as side effect
    }

    /**
     * Call corresponding visitor method(s). The order is: Call visitor methods of implemented interfaces first, then call
     * methods according to the class hierarchy in descending order, i.e., the most specific visitXXX() call comes last.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitRET(this);
    }

    /**
     * Dump instruction as byte code to stream out.
     *
     * @param out Output stream
     */
    @Override
    public void dump(final DataOutputStream out) throws IOException {
        if (wide) {
            out.writeByte(com.sun.org.apache.bcel.internal.Const.WIDE);
        }
        out.writeByte(super.getOpcode());
        if (wide) {
            out.writeShort(index);
        } else {
            out.writeByte(index);
        }
    }

    /**
     * @return index of local variable containg the return address
     */
    @Override
    public final int getIndex() {
        return index;
    }

    /**
     * @return return address type
     */
    @Override
    public Type getType(final ConstantPoolGen cp) {
        return ReturnaddressType.NO_TARGET;
    }

    /**
     * Read needed data (e.g. index) from file.
     */
    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        this.wide = wide;
        if (wide) {
            index = bytes.readUnsignedShort();
            super.setLength(4);
        } else {
            index = bytes.readUnsignedByte();
            super.setLength(2);
        }
    }

    /**
     * Set index of local variable containg the return address
     */
    @Override
    public final void setIndex(final int n) {
        if (n < 0) {
            throw new ClassGenException("Negative index value: " + n);
        }
        index = n;
        setWide();
    }

    private void setWide() {
        wide = index > com.sun.org.apache.bcel.internal.Const.MAX_BYTE;
        if (wide) {
            super.setLength(4); // Including the wide byte
        } else {
            super.setLength(2);
        }
    }

    /**
     * @return mnemonic for instruction
     */
    @Override
    public String toString(final boolean verbose) {
        return super.toString(verbose) + " " + index;
    }
}
