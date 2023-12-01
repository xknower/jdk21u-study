package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.IOException;

import com.sun.org.apache.bcel.internal.Const;

/**
 * This class represents a constant pool reference to a method.
 */
public final class ConstantMethodref extends ConstantCP {

    /**
     * Initialize from another object.
     *
     * @param c Source to copy.
     */
    public ConstantMethodref(final ConstantMethodref c) {
        super(Const.CONSTANT_Methodref, c.getClassIndex(), c.getNameAndTypeIndex());
    }

    /**
     * Initialize instance from input data.
     *
     * @param input input stream
     * @throws IOException if an I/O error occurs.
     */
    ConstantMethodref(final DataInput input) throws IOException {
        super(Const.CONSTANT_Methodref, input);
    }

    /**
     * @param classIndex Reference to the class containing the method
     * @param nameAndTypeIndex and the method signature
     */
    public ConstantMethodref(final int classIndex, final int nameAndTypeIndex) {
        super(Const.CONSTANT_Methodref, classIndex, nameAndTypeIndex);
    }

    /**
     * Called by objects that are traversing the nodes of the tree implicitly defined by the contents of a Java class.
     * I.e., the hierarchy of methods, fields, attributes, etc. spawns a tree of objects.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitConstantMethodref(this);
    }
}
