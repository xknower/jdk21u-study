package jdk.internal.classfile.constantpool;

import java.lang.constant.ConstantDesc;
import jdk.internal.classfile.TypeKind;

/**
 * Marker interface for constant pool entries suitable for loading via the
 * {@code LDC} instructions.
 */
public sealed interface LoadableConstantEntry extends PoolEntry
        permits ClassEntry, ConstantDynamicEntry, ConstantValueEntry, MethodHandleEntry, MethodTypeEntry {

    /**
     * {@return the constant described by this entry}
     */
    ConstantDesc constantValue();

    /**
     * {@return the type of the constant}
     */
    default TypeKind typeKind() {
        return TypeKind.ReferenceType;
    }
}
