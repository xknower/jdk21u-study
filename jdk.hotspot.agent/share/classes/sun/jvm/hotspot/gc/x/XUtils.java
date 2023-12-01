package sun.jvm.hotspot.gc.x;

import sun.jvm.hotspot.debugger.Address;
import sun.jvm.hotspot.runtime.VM;

class XUtils {
    static Address longToAddress(long value) {
        return VM.getVM().getDebugger().newAddress(value);
    }

    static long alignUp(long size, long alignment) {
        long mask = alignment - 1;
        long adjusted = size + mask;
        return adjusted & ~mask;
    }
}
