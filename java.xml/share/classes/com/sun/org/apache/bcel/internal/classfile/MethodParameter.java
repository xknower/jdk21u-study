package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.sun.org.apache.bcel.internal.Const;

/**
 * Entry of the parameters table.
 *
 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.7.24"> The class File Format :
 *      The MethodParameters Attribute</a>
 * @since 6.0
 */
public class MethodParameter implements Cloneable, Node {

    /** Index of the CONSTANT_Utf8_info structure in the constant_pool table representing the name of the parameter */
    private int nameIndex;

    /** The access flags */
    private int accessFlags;

    public MethodParameter() {
    }

    /**
     * Construct object from input stream.
     *
     * @param input Input stream
     * @throws IOException if an I/O error occurs.
     * @throws ClassFormatException if a class is malformed or cannot be interpreted as a class file
     */
    MethodParameter(final DataInput input) throws IOException {
        nameIndex = input.readUnsignedShort();
        accessFlags = input.readUnsignedShort();
    }

    @Override
    public void accept(final Visitor v) {
        v.visitMethodParameter(this);
    }

    /**
     * @return deep copy of this object
     */
    public MethodParameter copy() {
        try {
            return (MethodParameter) clone();
        } catch (final CloneNotSupportedException e) {
            // TODO should this throw?
        }
        return null;
    }

    /**
     * Dump object to file stream on binary format.
     *
     * @param file Output file stream
     * @throws IOException if an I/O error occurs.
     */
    public final void dump(final DataOutputStream file) throws IOException {
        file.writeShort(nameIndex);
        file.writeShort(accessFlags);
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    /**
     * Returns the name of the parameter.
     */
    public String getParameterName(final ConstantPool constantPool) {
        if (nameIndex == 0) {
            return null;
        }
        return constantPool.getConstantUtf8(nameIndex).getBytes();
    }

    public boolean isFinal() {
        return (accessFlags & Const.ACC_FINAL) != 0;
    }

    public boolean isMandated() {
        return (accessFlags & Const.ACC_MANDATED) != 0;
    }

    public boolean isSynthetic() {
        return (accessFlags & Const.ACC_SYNTHETIC) != 0;
    }

    public void setAccessFlags(final int accessFlags) {
        this.accessFlags = accessFlags;
    }

    public void setNameIndex(final int nameIndex) {
        this.nameIndex = nameIndex;
    }
}
