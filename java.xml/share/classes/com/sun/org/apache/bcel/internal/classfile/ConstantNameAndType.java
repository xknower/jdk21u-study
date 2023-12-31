package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.sun.org.apache.bcel.internal.Const;

/**
 * This class is derived from the abstract {@link Constant} and represents a reference to the name and signature of a
 * field or method.
 *
 * @see Constant
 */
public final class ConstantNameAndType extends Constant {

    private int nameIndex; // Name of field/method
    private int signatureIndex; // and its signature.

    /**
     * Initialize from another object.
     *
     * @param c Source to copy.
     */
    public ConstantNameAndType(final ConstantNameAndType c) {
        this(c.getNameIndex(), c.getSignatureIndex());
    }

    /**
     * Initialize instance from file data.
     *
     * @param file Input stream
     * @throws IOException if an I/O error occurs.
     */
    ConstantNameAndType(final DataInput file) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort());
    }

    /**
     * @param nameIndex Name of field/method
     * @param signatureIndex and its signature
     */
    public ConstantNameAndType(final int nameIndex, final int signatureIndex) {
        super(Const.CONSTANT_NameAndType);
        this.nameIndex = nameIndex;
        this.signatureIndex = signatureIndex;
    }

    /**
     * Called by objects that are traversing the nodes of the tree implicitly defined by the contents of a Java class.
     * I.e., the hierarchy of methods, fields, attributes, etc. spawns a tree of objects.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitConstantNameAndType(this);
    }

    /**
     * Dump name and signature index to file stream in binary format.
     *
     * @param file Output file stream
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public void dump(final DataOutputStream file) throws IOException {
        file.writeByte(super.getTag());
        file.writeShort(nameIndex);
        file.writeShort(signatureIndex);
    }

    /**
     * @return name
     */
    public String getName(final ConstantPool cp) {
        return cp.constantToString(getNameIndex(), Const.CONSTANT_Utf8);
    }

    /**
     * @return Name index in constant pool of field/method name.
     */
    public int getNameIndex() {
        return nameIndex;
    }

    /**
     * @return signature
     */
    public String getSignature(final ConstantPool cp) {
        return cp.constantToString(getSignatureIndex(), Const.CONSTANT_Utf8);
    }

    /**
     * @return Index in constant pool of field/method signature.
     */
    public int getSignatureIndex() {
        return signatureIndex;
    }

    /**
     * @param nameIndex the name index of this constant
     */
    public void setNameIndex(final int nameIndex) {
        this.nameIndex = nameIndex;
    }

    /**
     * @param signatureIndex the signature index in the constant pool of this type
     */
    public void setSignatureIndex(final int signatureIndex) {
        this.signatureIndex = signatureIndex;
    }

    /**
     * @return String representation
     */
    @Override
    public String toString() {
        return super.toString() + "(nameIndex = " + nameIndex + ", signatureIndex = " + signatureIndex + ")";
    }
}
