package com.sun.org.apache.bcel.internal.generic;

import java.util.Objects;

import com.sun.org.apache.bcel.internal.Const;

/**
 * Wrapper class for push operations, which are implemented either as BIPUSH, LDC or xCONST_n instructions.
 * @LastModified: Feb 2023
 */
public final class PUSH implements CompoundInstruction, VariableLengthInstruction {

    private final Instruction instruction;

    /**
     * Pushes an array type constant, for example {@code int[].class}, {@code String[].class}, and so on.
     *
     * @param cp generated constant pool.
     * @param value to be pushed.
     * @since 6.7.0
     */
    public PUSH(final ConstantPoolGen cp, final ArrayType value) {
        if (value == null) {
            instruction = InstructionConst.ACONST_NULL;
        } else {
            instruction = new LDC(cp.addArrayClass(value));
        }
    }

   /**
     * @param cp Constant pool
     * @param value to be pushed
     */
    public PUSH(final ConstantPoolGen cp, final boolean value) {
        Objects.requireNonNull(cp, "cp");
        instruction = InstructionConst.getInstruction(Const.ICONST_0 + (value ? 1 : 0));
    }

    /**
     * @param cp Constant pool
     * @param value to be pushed
     */
    public PUSH(final ConstantPoolGen cp, final Boolean value) {
        this(cp, value.booleanValue());
    }

    /**
     * creates a push object from a Character value. Warning: Make sure not to attempt to allow autoboxing to create this
     * value parameter, as an alternative constructor will be called
     *
     * @param cp Constant pool
     * @param value to be pushed
     */
    public PUSH(final ConstantPoolGen cp, final Character value) {
        this(cp, value.charValue());
    }

    /**
     * @param cp Constant pool
     * @param value to be pushed
     */
    public PUSH(final ConstantPoolGen cp, final double value) {
        if (value == 0.0) {
            instruction = InstructionConst.DCONST_0;
        } else if (value == 1.0) {
            instruction = InstructionConst.DCONST_1;
        } else {
            instruction = new LDC2_W(cp.addDouble(value));
        }
    }

    /**
     * @param cp Constant pool
     * @param value to be pushed
     */
    public PUSH(final ConstantPoolGen cp, final float value) {
        if (value == 0.0) {
            instruction = InstructionConst.FCONST_0;
        } else if (value == 1.0) {
            instruction = InstructionConst.FCONST_1;
        } else if (value == 2.0) {
            instruction = InstructionConst.FCONST_2;
        } else {
            instruction = new LDC(cp.addFloat(value));
        }
    }

    /**
     * This constructor also applies for values of type short, char, byte
     *
     * @param cp Constant pool
     * @param value to be pushed
     */
    public PUSH(final ConstantPoolGen cp, final int value) {
        if (value >= -1 && value <= 5) {
            instruction = InstructionConst.getInstruction(Const.ICONST_0 + value);
        } else if (Instruction.isValidByte(value)) {
            instruction = new BIPUSH((byte) value);
        } else if (Instruction.isValidShort(value)) {
            instruction = new SIPUSH((short) value);
        } else {
            instruction = new LDC(cp.addInteger(value));
        }
    }

    /**
     * @param cp Constant pool
     * @param value to be pushed
     */
    public PUSH(final ConstantPoolGen cp, final long value) {
        if (value == 0) {
            instruction = InstructionConst.LCONST_0;
        } else if (value == 1) {
            instruction = InstructionConst.LCONST_1;
        } else {
            instruction = new LDC2_W(cp.addLong(value));
        }
    }

    /**
     * @param cp Constant pool
     * @param value to be pushed
     */
    public PUSH(final ConstantPoolGen cp, final Number value) {
        if (value instanceof Integer || value instanceof Short || value instanceof Byte) {
            instruction = new PUSH(cp, value.intValue()).instruction;
        } else if (value instanceof Double) {
            instruction = new PUSH(cp, value.doubleValue()).instruction;
        } else if (value instanceof Float) {
            instruction = new PUSH(cp, value.floatValue()).instruction;
        } else if (value instanceof Long) {
            instruction = new PUSH(cp, value.longValue()).instruction;
        } else {
            throw new ClassGenException("What's this: " + value);
        }
    }

    /**
     *
     * @param cp
     * @param value
     * @since 6.0
     */
    public PUSH(final ConstantPoolGen cp, final ObjectType value) {
        if (value == null) {
            instruction = InstructionConst.ACONST_NULL;
        } else {
            instruction = new LDC(cp.addClass(value));
        }
    }

    /**
     * @param cp Constant pool
     * @param value to be pushed
     */
    public PUSH(final ConstantPoolGen cp, final String value) {
        if (value == null) {
            instruction = InstructionConst.ACONST_NULL;
        } else {
            instruction = new LDC(cp.addString(value));
        }
    }

    public Instruction getInstruction() {
        return instruction;
    }

    @Override
    public InstructionList getInstructionList() {
        return new InstructionList(instruction);
    }

    /**
     * @return mnemonic for instruction
     */
    @Override
    public String toString() {
        return instruction + " (PUSH)";
    }
}
