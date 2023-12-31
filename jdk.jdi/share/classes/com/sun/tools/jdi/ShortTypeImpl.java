package com.sun.tools.jdi;

import com.sun.jdi.InvalidTypeException;
import com.sun.jdi.PrimitiveValue;
import com.sun.jdi.ShortType;
import com.sun.jdi.VirtualMachine;

public class ShortTypeImpl extends PrimitiveTypeImpl implements ShortType {

    ShortTypeImpl(VirtualMachine vm) {
        super(vm);
    }

    public String signature() {
        return String.valueOf((char)JDWP.Tag.SHORT);
    }

    PrimitiveValue convert(PrimitiveValue value) throws InvalidTypeException {
        return vm.mirrorOf(((PrimitiveValueImpl)value).checkedShortValue());
    }
}
