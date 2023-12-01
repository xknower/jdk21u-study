package com.sun.org.apache.bcel.internal.generic;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * JSR - Jump to subroutine
 */
public class JSR extends JsrInstruction implements VariableLengthInstruction {

    /**
     * Empty constructor needed for Instruction.readInstruction. Not to be used otherwise.
     */
    JSR() {
    }

    public JSR(final InstructionHandle target) {
        super(com.sun.org.apache.bcel.internal.Const.JSR, target);
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
        v.visitVariableLengthInstruction(this);
        v.visitBranchInstruction(this);
        v.visitJsrInstruction(this);
        v.visitJSR(this);
    }

    /**
     * Dump instruction as byte code to stream out.
     *
     * @param out Output stream
     */
    @Override
    public void dump(final DataOutputStream out) throws IOException {
        super.setIndex(getTargetOffset());
        if (super.getOpcode() == com.sun.org.apache.bcel.internal.Const.JSR) {
            super.dump(out);
        } else { // JSR_W
            super.setIndex(getTargetOffset());
            out.writeByte(super.getOpcode());
            out.writeInt(super.getIndex());
        }
    }

    @Override
    protected int updatePosition(final int offset, final int maxOffset) {
        final int i = getTargetOffset(); // Depending on old position value
        setPosition(getPosition() + offset); // Position may be shifted by preceding expansions
        if (Math.abs(i) >= Short.MAX_VALUE - maxOffset) { // to large for short (estimate)
            super.setOpcode(com.sun.org.apache.bcel.internal.Const.JSR_W);
            final short oldLength = (short) super.getLength();
            super.setLength(5);
            return super.getLength() - oldLength;
        }
        return 0;
    }
}
