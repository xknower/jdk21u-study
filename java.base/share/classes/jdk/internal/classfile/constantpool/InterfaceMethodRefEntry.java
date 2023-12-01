package jdk.internal.classfile.constantpool;

import jdk.internal.classfile.impl.AbstractPoolEntry;

/**
 * Models a {@code CONSTANT_InterfaceMethodRef_info} constant in the constant pool of a
 * classfile.
 */
public sealed interface InterfaceMethodRefEntry
        extends MemberRefEntry
        permits AbstractPoolEntry.InterfaceMethodRefEntryImpl {

}
