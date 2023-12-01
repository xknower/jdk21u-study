package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.sun.org.apache.bcel.internal.Const;

/**
 * This class represents an entry in the provides table of the Module attribute. Each entry describes a service
 * implementation that the parent module provides.
 *
 * @see Module
 * @since 6.4.0
 */
public final class ModuleProvides implements Cloneable, Node {

    private final int providesIndex; // points to CONSTANT_Class_info
    private final int providesWithCount;
    private final int[] providesWithIndex; // points to CONSTANT_Class_info

    /**
     * Construct object from file stream.
     *
     * @param file Input stream
     * @throws IOException if an I/O Exception occurs in readUnsignedShort
     */
    ModuleProvides(final DataInput file) throws IOException {
        providesIndex = file.readUnsignedShort();
        providesWithCount = file.readUnsignedShort();
        providesWithIndex = new int[providesWithCount];
        for (int i = 0; i < providesWithCount; i++) {
            providesWithIndex[i] = file.readUnsignedShort();
        }
    }

    /**
     * Called by objects that are traversing the nodes of the tree implicitly defined by the contents of a Java class.
     * I.e., the hierarchy of methods, fields, attributes, etc. spawns a tree of objects.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitModuleProvides(this);
    }

    // TODO add more getters and setters?

    /**
     * @return deep copy of this object
     */
    public ModuleProvides copy() {
        try {
            return (ModuleProvides) clone();
        } catch (final CloneNotSupportedException e) {
            // TODO should this throw?
        }
        return null;
    }

    /**
     * Dump table entry to file stream in binary format.
     *
     * @param file Output file stream
     * @throws IOException if an I/O Exception occurs in writeShort
     */
    public void dump(final DataOutputStream file) throws IOException {
        file.writeShort(providesIndex);
        file.writeShort(providesWithCount);
        for (final int entry : providesWithIndex) {
            file.writeShort(entry);
        }
    }

    /**
     * @return String representation
     */
    @Override
    public String toString() {
        return "provides(" + providesIndex + ", " + providesWithCount + ", ...)";
    }

    /**
     * @return Resolved string representation
     */
    public String toString(final ConstantPool constantPool) {
        final StringBuilder buf = new StringBuilder();
        final String interfaceName = constantPool.constantToString(providesIndex, Const.CONSTANT_Class);
        buf.append(Utility.compactClassName(interfaceName, false));
        buf.append(", with(").append(providesWithCount).append("):\n");
        for (final int index : providesWithIndex) {
            final String className = constantPool.getConstantString(index, Const.CONSTANT_Class);
            buf.append("      ").append(Utility.compactClassName(className, false)).append("\n");
        }
        return buf.substring(0, buf.length() - 1); // remove the last newline
    }
}
