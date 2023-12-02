package com.sun.tools.jdi;

import com.sun.jdi.InvalidTypeException;
import com.sun.jdi.PrimitiveType;
import com.sun.jdi.PrimitiveValue;
import com.sun.jdi.VirtualMachine;

abstract class PrimitiveTypeImpl extends TypeImpl implements PrimitiveType {

    PrimitiveTypeImpl(VirtualMachine vm) {
        super(vm);
    }

    /*
     * Converts the given primitive value to a value of this type.
     */
    abstract PrimitiveValue convert(PrimitiveValue value) throws InvalidTypeException;

    public String toString() {
        return name();
    }
}
