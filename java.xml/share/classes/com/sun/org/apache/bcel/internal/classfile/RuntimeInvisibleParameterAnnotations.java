package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.IOException;

import com.sun.org.apache.bcel.internal.Const;

/**
 * Represents a parameter annotation that is represented in the class file but is not provided to the JVM.
 *
 * @since 6.0
 */
public class RuntimeInvisibleParameterAnnotations extends ParameterAnnotations {

    /**
     * @param nameIndex Index pointing to the name <em>Code</em>
     * @param length Content length in bytes
     * @param input Input stream
     * @param constantPool Array of constants
     * @throws IOException Thrown when an I/O exception of some sort has occurred.
     */
    public RuntimeInvisibleParameterAnnotations(final int nameIndex, final int length, final DataInput input, final ConstantPool constantPool)
        throws IOException {
        super(Const.ATTR_RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS, nameIndex, length, input, constantPool);
    }
}
