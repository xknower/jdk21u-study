package jdk.internal.classfile.instruction;

import jdk.internal.classfile.CodeElement;
import jdk.internal.classfile.CodeModel;
import jdk.internal.classfile.Instruction;
import jdk.internal.classfile.impl.AbstractInstruction;

/**
 * Models an {@code athrow} instruction in the {@code code} array of a
 * {@code Code} attribute.  Delivered as a {@link CodeElement} when traversing
 * the elements of a {@link CodeModel}.
 */
public sealed interface ThrowInstruction extends Instruction
        permits AbstractInstruction.UnboundThrowInstruction {

    /**
     * {@return a throw instruction}
     */
    static ThrowInstruction of() {
        return new AbstractInstruction.UnboundThrowInstruction();
    }
}
