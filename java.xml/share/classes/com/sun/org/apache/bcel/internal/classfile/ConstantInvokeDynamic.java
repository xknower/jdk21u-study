package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.IOException;

import com.sun.org.apache.bcel.internal.Const;

/**
 * This class is derived from the abstract {@link Constant} and represents a reference to a invoke dynamic.
 *
 * @see Constant
 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.10"> The
 *      CONSTANT_InvokeDynamic_info Structure in The Java Virtual Machine Specification</a>
 * @since 6.0
 */
public final class ConstantInvokeDynamic extends ConstantCP {

    /**
     * Initialize from another object.
     *
     * @param c Source to copy.
     */
    public ConstantInvokeDynamic(final ConstantInvokeDynamic c) {
        this(c.getBootstrapMethodAttrIndex(), c.getNameAndTypeIndex());
    }

    /**
     * Initialize instance from file data.
     *
     * @param file Input stream
     * @throws IOException if an I/O error occurs.
     */
    ConstantInvokeDynamic(final DataInput file) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort());
    }

    public ConstantInvokeDynamic(final int bootstrapMethodAttrIndex, final int nameAndTypeIndex) {
        super(Const.CONSTANT_InvokeDynamic, bootstrapMethodAttrIndex, nameAndTypeIndex);
    }

    /**
     * Called by objects that are traversing the nodes of the tree implicitly defined by the contents of a Java class. I.e.,
     * the hierarchy of methods, fields, attributes, etc. spawns a tree of objects.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitConstantInvokeDynamic(this);
    }

    /**
     * @return Reference (index) to bootstrap method this constant refers to.
     *
     *         Note that this method is a functional duplicate of getClassIndex for use by ConstantInvokeDynamic.
     * @since 6.0
     */
    public int getBootstrapMethodAttrIndex() {
        return super.getClassIndex(); // AKA bootstrap_method_attr_index
    }

    /**
     * @return String representation
     */
    @Override
    public String toString() {
        return super.toString().replace("class_index", "bootstrap_method_attr_index");
    }
}
