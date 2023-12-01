package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.sun.org.apache.bcel.internal.Const;

/**
 * This class is derived from the abstract {@link Constant} and represents a reference to a module.
 *
 * <p>
 * Note: Early access Java 9 support- currently subject to change
 * </p>
 *
 * @see Constant
 * @since 6.1
 */
public final class ConstantModule extends Constant implements ConstantObject {

    private int nameIndex;

    /**
     * Initialize from another object.
     *
     * @param c Source to copy.
     */
    public ConstantModule(final ConstantModule c) {
        this(c.getNameIndex());
    }

    /**
     * Initialize instance from file data.
     *
     * @param file Input stream
     * @throws IOException if an I/O error occurs.
     */
    ConstantModule(final DataInput file) throws IOException {
        this(file.readUnsignedShort());
    }

    /**
     * @param nameIndex Name index in constant pool. Should refer to a ConstantUtf8.
     */
    public ConstantModule(final int nameIndex) {
        super(Const.CONSTANT_Module);
        this.nameIndex = nameIndex;
    }

    /**
     * Called by objects that are traversing the nodes of the tree implicitly defined by the contents of a Java class. I.e.,
     * the hierarchy of methods, fields, attributes, etc. spawns a tree of objects.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitConstantModule(this);
    }

    /**
     * Dump constant module to file stream in binary format.
     *
     * @param file Output file stream
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public void dump(final DataOutputStream file) throws IOException {
        file.writeByte(super.getTag());
        file.writeShort(nameIndex);
    }

    /**
     * @return dereferenced string
     */
    public String getBytes(final ConstantPool cp) {
        return (String) getConstantValue(cp);
    }

    /**
     * @return String object
     */
    @Override
    public Object getConstantValue(final ConstantPool cp) {
        return cp.getConstantUtf8(nameIndex).getBytes();
    }

    /**
     * @return Name index in constant pool of module name.
     */
    public int getNameIndex() {
        return nameIndex;
    }

    /**
     * @param nameIndex the name index in the constant pool of this Constant Module
     */
    public void setNameIndex(final int nameIndex) {
        this.nameIndex = nameIndex;
    }

    /**
     * @return String representation.
     */
    @Override
    public String toString() {
        return super.toString() + "(nameIndex = " + nameIndex + ")";
    }
}
