package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.sun.org.apache.bcel.internal.Const;

/**
 * This class represents an entry in the requires table of the Module attribute. Each entry describes a module on which
 * the parent module depends.
 *
 * @see Module
 * @since 6.4.0
 */
public final class ModuleRequires implements Cloneable, Node {

    private final int requiresIndex; // points to CONSTANT_Module_info
    private final int requiresFlags;
    private final int requiresVersionIndex; // either 0 or points to CONSTANT_Utf8_info

    /**
     * Construct object from file stream.
     *
     * @param file Input stream
     * @throws IOException if an I/O Exception occurs in readUnsignedShort
     */
    ModuleRequires(final DataInput file) throws IOException {
        requiresIndex = file.readUnsignedShort();
        requiresFlags = file.readUnsignedShort();
        requiresVersionIndex = file.readUnsignedShort();
    }

    /**
     * Called by objects that are traversing the nodes of the tree implicitly defined by the contents of a Java class.
     * I.e., the hierarchy of methods, fields, attributes, etc. spawns a tree of objects.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitModuleRequires(this);
    }

    // TODO add more getters and setters?

    /**
     * @return deep copy of this object
     */
    public ModuleRequires copy() {
        try {
            return (ModuleRequires) clone();
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
        file.writeShort(requiresIndex);
        file.writeShort(requiresFlags);
        file.writeShort(requiresVersionIndex);
    }

    /**
     * @return String representation
     */
    @Override
    public String toString() {
        return "requires(" + requiresIndex + ", " + String.format("%04x", requiresFlags) + ", " + requiresVersionIndex + ")";
    }

    /**
     * @return Resolved string representation
     */
    public String toString(final ConstantPool constantPool) {
        final StringBuilder buf = new StringBuilder();
        final String moduleName = constantPool.constantToString(requiresIndex, Const.CONSTANT_Module);
        buf.append(Utility.compactClassName(moduleName, false));
        buf.append(", ").append(String.format("%04x", requiresFlags));
        final String version = requiresVersionIndex == 0 ? "0" : constantPool.getConstantString(requiresVersionIndex, Const.CONSTANT_Utf8);
        buf.append(", ").append(version);
        return buf.toString();
    }
}
