package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @since 6.0
 */
public class ArrayElementValue extends ElementValue {
    // For array types, this is the array
    private final ElementValue[] elementValues;

    public ArrayElementValue(final int type, final ElementValue[] datums, final ConstantPool cpool) {
        super(type, cpool);
        if (type != ARRAY) {
            throw new ClassFormatException("Only element values of type array can be built with this ctor - type specified: " + type);
        }
        this.elementValues = datums;
    }

    @Override
    public void dump(final DataOutputStream dos) throws IOException {
        dos.writeByte(super.getType()); // u1 type of value (ARRAY == '[')
        dos.writeShort(elementValues.length);
        for (final ElementValue evalue : elementValues) {
            evalue.dump(dos);
        }
    }

    public ElementValue[] getElementValuesArray() {
        return elementValues;
    }

    public int getElementValuesArraySize() {
        return elementValues.length;
    }

    @Override
    public String stringifyValue() {
        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < elementValues.length; i++) {
            sb.append(elementValues[i].stringifyValue());
            if (i + 1 < elementValues.length) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < elementValues.length; i++) {
            sb.append(elementValues[i]);
            if (i + 1 < elementValues.length) {
                sb.append(",");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
