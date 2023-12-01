package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.sun.org.apache.bcel.internal.Const;

/**
 * This class is derived from the abstract {@link Constant} and represents a reference to a (external) class.
 *
 * @see Constant
 */
public final class ConstantClass extends Constant implements ConstantObject {

    private int nameIndex; // Identical to ConstantString except for the name

    /**
     * Initialize from another object.
     *
     * @param c Source to copy.
     */
    public ConstantClass(final ConstantClass c) {
        this(c.getNameIndex());
    }

    /**
     * Constructs an instance from file data.
     *
     * @param dataInput Input stream
     * @throws IOException if an I/O error occurs reading from the given {@code dataInput}.
     */
    ConstantClass(final DataInput dataInput) throws IOException {
        this(dataInput.readUnsignedShort());
    }

    /**
     * @param nameIndex Name index in constant pool. Should refer to a ConstantUtf8.
     */
    public ConstantClass(final int nameIndex) {
        super(Const.CONSTANT_Class);
        this.nameIndex = nameIndex;
    }

    /**
     * Called by objects that are traversing the nodes of the tree implicitly defined by the contents of a Java class.
     * I.e., the hierarchy of methods, fields, attributes, etc. spawns a tree of objects.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitConstantClass(this);
    }

    /**
     * Dumps constant class to file stream in binary format.
     *
     * @param file Output file stream
     * @throws IOException if an I/O error occurs writing to the DataOutputStream.
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
     * @return Name index in constant pool of class name.
     */
    public int getNameIndex() {
        return nameIndex;
    }

    /**
     * @param nameIndex the name index in the constant pool of this Constant Class
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
