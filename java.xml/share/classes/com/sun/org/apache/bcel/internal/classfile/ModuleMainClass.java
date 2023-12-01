package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.util.Args;

/**
 * This class is derived from <em>Attribute</em> and indicates the main class of a module. There may be at most one
 * ModuleMainClass attribute in a ClassFile structure.
 *
 * @see Attribute
 */
public final class ModuleMainClass extends Attribute {

    private int mainClassIndex;

    /**
     * Construct object from input stream.
     *
     * @param nameIndex Index in constant pool
     * @param length Content length in bytes
     * @param input Input stream
     * @param constantPool Array of constants
     * @throws IOException if an I/O error occurs.
     */
    ModuleMainClass(final int nameIndex, final int length, final DataInput input, final ConstantPool constantPool) throws IOException {
        this(nameIndex, length, 0, constantPool);
        mainClassIndex = input.readUnsignedShort();
    }

    /**
     * @param nameIndex Index in constant pool
     * @param length Content length in bytes
     * @param mainClassIndex Host class index
     * @param constantPool Array of constants
     */
    public ModuleMainClass(final int nameIndex, final int length, final int mainClassIndex, final ConstantPool constantPool) {
        super(Const.ATTR_NEST_MEMBERS, nameIndex, length, constantPool);
        this.mainClassIndex = Args.requireU2(mainClassIndex, "mainClassIndex");
    }

    /**
     * Initialize from another object. Note that both objects use the same references (shallow copy). Use copy() for a
     * physical copy.
     *
     * @param c Source to copy.
     */
    public ModuleMainClass(final ModuleMainClass c) {
        this(c.getNameIndex(), c.getLength(), c.getHostClassIndex(), c.getConstantPool());
    }

    /**
     * Called by objects that are traversing the nodes of the tree implicitly defined by the contents of a Java class. I.e.,
     * the hierarchy of methods, fields, attributes, etc. spawns a tree of objects.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitModuleMainClass(this);
    }

    /**
     * @return deep copy of this attribute
     */
    @Override
    public Attribute copy(final ConstantPool constantPool) {
        final ModuleMainClass c = (ModuleMainClass) clone();
        c.setConstantPool(constantPool);
        return c;
    }

    /**
     * Dump ModuleMainClass attribute to file stream in binary format.
     *
     * @param file Output file stream
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public void dump(final DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(mainClassIndex);
    }

    /**
     * @return index into constant pool of host class name.
     */
    public int getHostClassIndex() {
        return mainClassIndex;
    }

    /**
     * @param mainClassIndex the host class index
     */
    public void setHostClassIndex(final int mainClassIndex) {
        this.mainClassIndex = mainClassIndex;
    }

    /**
     * @return String representation
     */
    @Override
    public String toString() {
        final StringBuilder buf = new StringBuilder();
        buf.append("ModuleMainClass: ");
        final String className = super.getConstantPool().getConstantString(mainClassIndex, Const.CONSTANT_Class);
        buf.append(Utility.compactClassName(className, false));
        return buf.toString();
    }
}
