package jdk.internal.classfile.constantpool;

import jdk.internal.classfile.impl.AbstractPoolEntry;

/**
 * Models a {@code CONSTANT_String_info} constant in the constant pool of a
 * classfile.
 */
public sealed interface StringEntry
        extends ConstantValueEntry
        permits AbstractPoolEntry.StringEntryImpl {
    /**
     * {@return the UTF constant pool entry describing the string contents}
     */
    Utf8Entry utf8();

    /**
     * {@return the string value for this entry}
     */
    String stringValue();
}
