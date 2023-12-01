package sun.jvm.hotspot.runtime;


import sun.jvm.hotspot.debugger.Address;

public class NotificationThread extends JavaThread {
    public NotificationThread(Address addr) {
        super(addr);
    }

    public boolean isJavaThread() { return false; }

}
