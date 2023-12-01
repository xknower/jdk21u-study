package jdk.internal.classfile.constantpool;

import jdk.internal.classfile.impl.AbstractPoolEntry;

/**
 * Models a {@code CONSTANT_Fieldref_info} constant in the constant pool of a
 * classfile.
 */
public sealed interface FieldRefEntry extends MemberRefEntry
        permits AbstractPoolEntry.FieldRefEntryImpl {

}
