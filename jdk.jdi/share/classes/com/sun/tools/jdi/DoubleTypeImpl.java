package com.sun.tools.jdi;

import com.sun.jdi.DoubleType;
import com.sun.jdi.InvalidTypeException;
import com.sun.jdi.PrimitiveValue;
import com.sun.jdi.VirtualMachine;

public class DoubleTypeImpl extends PrimitiveTypeImpl implements DoubleType {

    DoubleTypeImpl(VirtualMachine vm) {
        super(vm);
    }

    public String signature() {
        return String.valueOf((char)JDWP.Tag.DOUBLE);
    }

    PrimitiveValue convert(PrimitiveValue value) throws InvalidTypeException {
        return vm.mirrorOf(((PrimitiveValueImpl)value).checkedDoubleValue());
    }
}
