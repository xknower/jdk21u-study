package com.sun.org.apache.bcel.internal.generic;

/**
 * Denotes a push instruction that produces a literal on the stack such as SIPUSH, BIPUSH, ICONST, etc.
 *
 *
 * @see ICONST
 * @see SIPUSH
 */
public interface ConstantPushInstruction extends PushInstruction, TypedInstruction {

    Number getValue();
}
