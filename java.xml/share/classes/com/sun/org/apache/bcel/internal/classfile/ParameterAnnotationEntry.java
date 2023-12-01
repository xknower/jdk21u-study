package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * represents one parameter annotation in the parameter annotation table
 *
 * @since 6.0
 */
public class ParameterAnnotationEntry implements Node {

    static final ParameterAnnotationEntry[] EMPTY_ARRAY = {};

    public static ParameterAnnotationEntry[] createParameterAnnotationEntries(final Attribute[] attrs) {
        // Find attributes that contain parameter annotation data
        final List<ParameterAnnotationEntry> accumulatedAnnotations = new ArrayList<>(attrs.length);
        for (final Attribute attribute : attrs) {
            if (attribute instanceof ParameterAnnotations) {
                final ParameterAnnotations runtimeAnnotations = (ParameterAnnotations) attribute;
                Collections.addAll(accumulatedAnnotations, runtimeAnnotations.getParameterAnnotationEntries());
            }
        }
        return accumulatedAnnotations.toArray(ParameterAnnotationEntry.EMPTY_ARRAY);
    }

    private final AnnotationEntry[] annotationTable;

    /**
     * Construct object from input stream.
     *
     * @param input Input stream
     * @throws IOException if an I/O error occurs.
     */
    ParameterAnnotationEntry(final DataInput input, final ConstantPool constantPool) throws IOException {
        final int annotationTableLength = input.readUnsignedShort();
        annotationTable = new AnnotationEntry[annotationTableLength];
        for (int i = 0; i < annotationTableLength; i++) {
            // TODO isRuntimeVisible
            annotationTable[i] = AnnotationEntry.read(input, constantPool, false);
        }
    }

    /**
     * Called by objects that are traversing the nodes of the tree implicitly defined by the contents of a Java class.
     * I.e., the hierarchy of methods, fields, attributes, etc. spawns a tree of objects.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitParameterAnnotationEntry(this);
    }

    public void dump(final DataOutputStream dos) throws IOException {
        dos.writeShort(annotationTable.length);
        for (final AnnotationEntry entry : annotationTable) {
            entry.dump(dos);
        }
    }

    /**
     * returns the array of annotation entries in this annotation
     */
    public AnnotationEntry[] getAnnotationEntries() {
        return annotationTable;
    }
}
