package sun.jvm.hotspot.code;

import sun.jvm.hotspot.debugger.Address;

public class VtableBlob extends BufferBlob {

    public VtableBlob(Address addr) {
        super(addr);
    }

    public boolean isVtableBlob() {
        return true;
    }

    public String getName() {
        return "VtableBlob: " + super.getName();
    }

}
