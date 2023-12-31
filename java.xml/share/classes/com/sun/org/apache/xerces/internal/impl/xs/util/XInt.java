package com.sun.org.apache.xerces.internal.impl.xs.util;

/**
 * @xerces.internal
 *
 * @author Henry Zongaro, IBM
 */

public final class XInt {

    private int fValue;

    XInt(int value) {
        fValue = value;
    }

    public final int intValue() {
        return fValue;
    }

    public final short shortValue() {
        return (short)fValue;
    }

    public final boolean equals(XInt compareVal) {
        return (this.fValue == compareVal.fValue);
    }

    public String toString() {
        return Integer.toString(fValue);
    }
}
