package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * base class for parameter annotations
 *
 * @since 6.0
 */
public abstract class ParameterAnnotations extends Attribute implements Iterable<ParameterAnnotationEntry> {

    /** Table of parameter annotations */
    private ParameterAnnotationEntry[] parameterAnnotationTable;

    /**
     * @param parameterAnnotationType the subclass type of the parameter annotation
     * @param nameIndex Index pointing to the name <em>Code</em>
     * @param length Content length in bytes
     * @param input Input stream
     * @param constantPool Array of constants
     */
    ParameterAnnotations(final byte parameterAnnotationType, final int nameIndex, final int length, final DataInput input, final ConstantPool constantPool)
        throws IOException {
        this(parameterAnnotationType, nameIndex, length, (ParameterAnnotationEntry[]) null, constantPool);
        final int numParameters = input.readUnsignedByte();
        parameterAnnotationTable = new ParameterAnnotationEntry[numParameters];
        for (int i = 0; i < numParameters; i++) {
            parameterAnnotationTable[i] = new ParameterAnnotationEntry(input, constantPool);
        }
    }

    /**
     * @param parameterAnnotationType the subclass type of the parameter annotation
     * @param nameIndex Index pointing to the name <em>Code</em>
     * @param length Content length in bytes
     * @param parameterAnnotationTable the actual parameter annotations
     * @param constantPool Array of constants
     */
    public ParameterAnnotations(final byte parameterAnnotationType, final int nameIndex, final int length,
        final ParameterAnnotationEntry[] parameterAnnotationTable, final ConstantPool constantPool) {
        super(parameterAnnotationType, nameIndex, length, constantPool);
        this.parameterAnnotationTable = parameterAnnotationTable;
    }

    /**
     * Called by objects that are traversing the nodes of the tree implicitly defined by the contents of a Java class.
     * I.e., the hierarchy of methods, fields, attributes, etc. spawns a tree of objects.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitParameterAnnotation(this);
    }

    /**
     * @return deep copy of this attribute
     */
    @Override
    public Attribute copy(final ConstantPool constantPool) {
        return (Attribute) clone();
    }

    @Override
    public void dump(final DataOutputStream dos) throws IOException {
        super.dump(dos);
        dos.writeByte(parameterAnnotationTable.length);

        for (final ParameterAnnotationEntry element : parameterAnnotationTable) {
            element.dump(dos);
        }

    }

    /**
     * returns the array of parameter annotation entries in this parameter annotation
     */
    public ParameterAnnotationEntry[] getParameterAnnotationEntries() {
        return parameterAnnotationTable;
    }

    /**
     * @return the parameter annotation entry table
     */
    public final ParameterAnnotationEntry[] getParameterAnnotationTable() {
        return parameterAnnotationTable;
    }

    @Override
    public Iterator<ParameterAnnotationEntry> iterator() {
        return Stream.of(parameterAnnotationTable).iterator();
    }

    /**
     * @param parameterAnnotationTable the entries to set in this parameter annotation
     */
    public final void setParameterAnnotationTable(final ParameterAnnotationEntry[] parameterAnnotationTable) {
        this.parameterAnnotationTable = parameterAnnotationTable;
    }
}
