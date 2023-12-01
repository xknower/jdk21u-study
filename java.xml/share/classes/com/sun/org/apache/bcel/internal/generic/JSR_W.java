package com.sun.org.apache.bcel.internal.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import com.sun.org.apache.bcel.internal.util.ByteSequence;

/**
 * JSR_W - Jump to subroutine
 */
public class JSR_W extends JsrInstruction {

    /**
     * Empty constructor needed for Instruction.readInstruction. Not to be used otherwise.
     */
    JSR_W() {
    }

    public JSR_W(final InstructionHandle target) {
        super(com.sun.org.apache.bcel.internal.Const.JSR_W, target);
        super.setLength(5);
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
        v.visitBranchInstruction(this);
        v.visitJsrInstruction(this);
        v.visitJSR_W(this);
    }

    /**
     * Dump instruction as byte code to stream out.
     *
     * @param out Output stream
     */
    @Override
    public void dump(final DataOutputStream out) throws IOException {
        super.setIndex(getTargetOffset());
        out.writeByte(super.getOpcode());
        out.writeInt(super.getIndex());
    }

    /**
     * Read needed data (e.g. index) from file.
     */
    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        super.setIndex(bytes.readInt());
        super.setLength(5);
    }
}
