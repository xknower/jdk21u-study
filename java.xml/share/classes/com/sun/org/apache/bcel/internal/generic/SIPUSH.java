package com.sun.org.apache.bcel.internal.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import com.sun.org.apache.bcel.internal.util.ByteSequence;

/**
 * SIPUSH - Push short
 *
 * <PRE>
 * Stack: ... -&gt; ..., value
 * </PRE>
 */
public class SIPUSH extends Instruction implements ConstantPushInstruction {

    private short b;

    /**
     * Empty constructor needed for Instruction.readInstruction. Not to be used otherwise.
     */
    SIPUSH() {
    }

    public SIPUSH(final short b) {
        super(com.sun.org.apache.bcel.internal.Const.SIPUSH, (short) 3);
        this.b = b;
    }

    /**
     * Call corresponding visitor method(s). The order is: Call visitor methods of implemented interfaces first, then call
     * methods according to the class hierarchy in descending order, i.e., the most specific visitXXX() call comes last.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitPushInstruction(this);
        v.visitStackProducer(this);
        v.visitTypedInstruction(this);
        v.visitConstantPushInstruction(this);
        v.visitSIPUSH(this);
    }

    /**
     * Dump instruction as short code to stream out.
     */
    @Override
    public void dump(final DataOutputStream out) throws IOException {
        super.dump(out);
        out.writeShort(b);
    }

    /**
     * @return Type.SHORT
     */
    @Override
    public Type getType(final ConstantPoolGen cp) {
        return Type.SHORT;
    }

    @Override
    public Number getValue() {
        return Integer.valueOf(b);
    }

    /**
     * Read needed data (e.g. index) from file.
     */
    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        super.setLength(3);
        b = bytes.readShort();
    }

    /**
     * @return mnemonic for instruction
     */
    @Override
    public String toString(final boolean verbose) {
        return super.toString(verbose) + " " + b;
    }
}
