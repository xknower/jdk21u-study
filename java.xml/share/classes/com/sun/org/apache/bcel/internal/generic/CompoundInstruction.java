package com.sun.org.apache.bcel.internal.generic;

/**
 * Wrapper class for 'compound' operations, virtual instructions that don't exist as byte code, but give a useful
 * meaning. For example, the (virtual) PUSH instruction takes an arbitrary argument and produces the appropriate code at
 * dump time (ICONST, LDC, BIPUSH, ...). Also you can use the SWITCH instruction as a useful template for either
 * LOOKUPSWITCH or TABLESWITCH.
 *
 * The interface provides the possibility for the user to write 'templates' or 'macros' for such reusable code patterns.
 *
 * @see PUSH
 * @see SWITCH
 */
public interface CompoundInstruction {

    InstructionList getInstructionList();
}
