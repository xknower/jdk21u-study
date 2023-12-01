package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.sun.org.apache.bcel.internal.Const;

/**
 * Represents the default value of a annotation for a method info.
 *
 * @since 6.0
 */
public class AnnotationDefault extends Attribute {

    private ElementValue defaultValue;

    /**
     * @param nameIndex Index pointing to the name <em>Code</em>
     * @param length Content length in bytes
     * @param input Input stream
     * @param constantPool Array of constants
     */
    AnnotationDefault(final int nameIndex, final int length, final DataInput input, final ConstantPool constantPool) throws IOException {
        this(nameIndex, length, (ElementValue) null, constantPool);
        defaultValue = ElementValue.readElementValue(input, constantPool);
    }

    /**
     * @param nameIndex Index pointing to the name <em>Code</em>
     * @param length Content length in bytes
     * @param defaultValue the annotation's default value
     * @param constantPool Array of constants
     */
    public AnnotationDefault(final int nameIndex, final int length, final ElementValue defaultValue, final ConstantPool constantPool) {
        super(Const.ATTR_ANNOTATION_DEFAULT, nameIndex, length, constantPool);
        this.defaultValue = defaultValue;
    }

    /**
     * Called by objects that are traversing the nodes of the tree implicitly defined by the contents of a Java class.
     * I.e., the hierarchy of methods, fields, attributes, etc. spawns a tree of objects.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitAnnotationDefault(this);
    }

    @Override
    public Attribute copy(final ConstantPool constantPool) {
        return (Attribute) clone();
    }

    @Override
    public final void dump(final DataOutputStream dos) throws IOException {
        super.dump(dos);
        defaultValue.dump(dos);
    }

    /**
     * @return the default value
     */
    public final ElementValue getDefaultValue() {
        return defaultValue;
    }

    /**
     * @param defaultValue the default value of this methodinfo's annotation
     */
    public final void setDefaultValue(final ElementValue defaultValue) {
        this.defaultValue = defaultValue;
    }
}
