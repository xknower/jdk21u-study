package sun.jvm.hotspot.runtime;

import sun.jvm.hotspot.debugger.Address;

public class StringDedupThread extends JavaThread {
    public StringDedupThread(Address addr) {
        super(addr);
    }

    public boolean isJavaThread() { return false; }
    public boolean isHiddenFromExternalView() { return true; }
}
