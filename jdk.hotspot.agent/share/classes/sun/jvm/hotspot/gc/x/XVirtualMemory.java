package sun.jvm.hotspot.gc.x;

import sun.jvm.hotspot.debugger.Address;
import sun.jvm.hotspot.runtime.VM;
import sun.jvm.hotspot.runtime.VMObject;
import sun.jvm.hotspot.types.CIntegerField;
import sun.jvm.hotspot.types.Type;
import sun.jvm.hotspot.types.TypeDataBase;

public class XVirtualMemory extends VMObject {
    private static CIntegerField startField;
    private static CIntegerField endField;

    static {
        VM.registerVMInitializedObserver((o, d) -> initialize(VM.getVM().getTypeDataBase()));
    }

    private static synchronized void initialize(TypeDataBase db) {
        Type type = db.lookupType("XVirtualMemory");

        startField = type.getCIntegerField("_start");
        endField = type.getCIntegerField("_end");
    }

    public XVirtualMemory(Address addr) {
        super(addr);
    }

    long start() {
        return startField.getJLong(addr);
    }

    long end() {
        return endField.getJLong(addr);
    }
}
