package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.sun.org.apache.bcel.internal.Const;

/**
 * This class is derived from the abstract {@link Constant} and represents a reference to an int object.
 *
 * @see     Constant
 * @LastModified: Jun 2019
 */
public final class ConstantInteger extends Constant implements ConstantObject {

    private int bytes;

    /**
     * Initialize from another object.
     *
     * @param c Source to copy.
     */
    public ConstantInteger(final ConstantInteger c) {
        this(c.getBytes());
    }

    /**
     * Initialize instance from file data.
     *
     * @param file Input stream
     * @throws IOException if an I/O error occurs.
     */
    ConstantInteger(final DataInput file) throws IOException {
        this(file.readInt());
    }

    /**
     * @param bytes Data
     */
    public ConstantInteger(final int bytes) {
        super(Const.CONSTANT_Integer);
        this.bytes = bytes;
    }

    /**
     * Called by objects that are traversing the nodes of the tree implicitly defined by the contents of a Java class.
     * I.e., the hierarchy of methods, fields, attributes, etc. spawns a tree of objects.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitConstantInteger(this);
    }

    /**
     * Dump constant integer to file stream in binary format.
     *
     * @param file Output file stream
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public void dump(final DataOutputStream file) throws IOException {
        file.writeByte(super.getTag());
        file.writeInt(bytes);
    }

    /**
     * @return data, i.e., 4 bytes.
     */
    public int getBytes() {
        return bytes;
    }

    /**
     * @return Integer object
     */
    @Override
    public Object getConstantValue(final ConstantPool cp) {
        return Integer.valueOf(bytes);
    }

    /**
     * @param bytes the raw bytes that represent this integer
     */
    public void setBytes(final int bytes) {
        this.bytes = bytes;
    }

    /**
     * @return String representation.
     */
    @Override
    public String toString() {
        return super.toString() + "(bytes = " + bytes + ")";
    }
}
