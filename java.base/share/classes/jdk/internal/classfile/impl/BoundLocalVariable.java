package jdk.internal.classfile.impl;

import java.lang.constant.ClassDesc;
import jdk.internal.classfile.attribute.LocalVariableInfo;
import jdk.internal.classfile.constantpool.Utf8Entry;
import jdk.internal.classfile.instruction.LocalVariable;

public final class BoundLocalVariable
        extends AbstractBoundLocalVariable
        implements LocalVariableInfo,
                   LocalVariable {

    public BoundLocalVariable(CodeImpl code, int offset) {
        super(code, offset);
    }

    @Override
    public Utf8Entry type() {
        return secondaryEntry();
    }

    @Override
    public ClassDesc typeSymbol() {
        return ClassDesc.ofDescriptor(type().stringValue());
    }

    @Override
    public void writeTo(DirectCodeBuilder writer) {
        writer.addLocalVariable(this);
    }

    @Override
    public String toString() {
        return String.format("LocalVariable[name=%s, slot=%d, type=%s]", name().stringValue(), slot(), type().stringValue());
    }
}
