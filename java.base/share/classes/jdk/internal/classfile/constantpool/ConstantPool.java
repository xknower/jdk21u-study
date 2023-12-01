package jdk.internal.classfile.constantpool;

import jdk.internal.classfile.BootstrapMethodEntry;
import jdk.internal.classfile.ClassReader;

/**
 * Provides read access to the constant pool and bootstrap method table of a
 * classfile.
 */
public sealed interface ConstantPool
        permits ClassReader, ConstantPoolBuilder {

    /**
     * {@return the entry at the specified index}
     *
     * @param index the index within the pool of the desired entry
     */
    PoolEntry entryByIndex(int index);

    /**
     * {@return the number of entries in the constant pool}
     */
    int entryCount();

    /**
     * {@return the {@link BootstrapMethodEntry} at the specified index within
     * the bootstrap method table}
     *
     * @param index the index within the bootstrap method table of the desired
     *              entry
     */
    BootstrapMethodEntry bootstrapMethodEntry(int index);

    /**
     * {@return the number of entries in the bootstrap method table}
     */
    int bootstrapMethodCount();
}
