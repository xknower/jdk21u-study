package jdk.internal.classfile.constantpool;

import jdk.internal.classfile.impl.AbstractPoolEntry;

/**
 * Models a {@code CONSTANT_MethodRef_info} constant in the constant pool of a
 * classfile.
 */
public sealed interface MethodRefEntry extends MemberRefEntry
        permits AbstractPoolEntry.MethodRefEntryImpl {

}
