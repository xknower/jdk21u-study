package jdk.internal.classfile.instruction;

import jdk.internal.classfile.Label;
import jdk.internal.classfile.impl.AbstractInstruction;

/**
 * Models a single case in a {@code lookupswitch} or {@code tableswitch}
 * instruction.
 *
 * @see LookupSwitchInstruction
 * @see TableSwitchInstruction
 */
public sealed interface SwitchCase
        permits AbstractInstruction.SwitchCaseImpl {

    /** {@return the integer value corresponding to this case} */
    int caseValue();

    /** {@return the branch target corresponding to this case} */
    Label target();

    /**
     * Create a {@linkplain SwitchCase}
     *
     * @param caseValue the integer value for the case
     * @param target the branch target for the case
     * @return the {@linkplain SwitchCase}
     */
    static SwitchCase of(int caseValue, Label target) {
        return new AbstractInstruction.SwitchCaseImpl(caseValue, target);
    }
}
