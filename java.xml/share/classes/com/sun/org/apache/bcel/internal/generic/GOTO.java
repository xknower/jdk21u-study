package com.sun.org.apache.bcel.internal.generic;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * GOTO - Branch always (to relative offset, not absolute address)
 */
public class GOTO extends GotoInstruction implements VariableLengthInstruction {

    /**
     * Empty constructor needed for Instruction.readInstruction. Not to be used otherwise.
     */
    GOTO() {
    }

    public GOTO(final InstructionHandle target) {
        super(com.sun.org.apache.bcel.internal.Const.GOTO, target);
    }

    /**
     * Call corresponding visitor method(s). The order is: Call visitor methods of implemented interfaces first, then call
     * methods according to the class hierarchy in descending order, i.e., the most specific visitXXX() call comes last.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitVariableLengthInstruction(this);
        v.visitUnconditionalBranch(this);
        v.visitBranchInstruction(this);
        v.visitGotoInstruction(this);
        v.visitGOTO(this);
    }

    /**
     * Dump instruction as byte code to stream out.
     *
     * @param out Output stream
     */
    @Override
    public void dump(final DataOutputStream out) throws IOException {
        super.setIndex(getTargetOffset());
        final short opcode = getOpcode();
        if (opcode == com.sun.org.apache.bcel.internal.Const.GOTO) {
            super.dump(out);
        } else { // GOTO_W
            super.setIndex(getTargetOffset());
            out.writeByte(opcode);
            out.writeInt(super.getIndex());
        }
    }

    /**
     * Called in pass 2 of InstructionList.setPositions() in order to update the branch target, that may shift due to
     * variable length instructions.
     *
     * @param offset additional offset caused by preceding (variable length) instructions
     * @param maxOffset the maximum offset that may be caused by these instructions
     * @return additional offset caused by possible change of this instruction's length
     */
    @Override
    protected int updatePosition(final int offset, final int maxOffset) {
        final int i = getTargetOffset(); // Depending on old position value
        setPosition(getPosition() + offset); // Position may be shifted by preceding expansions
        if (Math.abs(i) >= Short.MAX_VALUE - maxOffset) { // to large for short (estimate)
            super.setOpcode(com.sun.org.apache.bcel.internal.Const.GOTO_W);
            final short oldLength = (short) super.getLength();
            super.setLength(5);
            return super.getLength() - oldLength;
        }
        return 0;
    }
}
