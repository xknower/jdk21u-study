package sun.jvm.hotspot.runtime;

import java.util.*;
import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.types.*;
import sun.jvm.hotspot.utilities.Observable;
import sun.jvm.hotspot.utilities.Observer;

public class Thread extends VMObject {
  private static long tlabFieldOffset;

  private static CIntegerField suspendFlagsField;

  private static AddressField currentPendingMonitorField;
  private static AddressField currentWaitingMonitorField;

  private static JLongField allocatedBytesField;

  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static synchronized void initialize(TypeDataBase db) {
    Type typeThread = db.lookupType("Thread");
    Type typeJavaThread = db.lookupType("JavaThread");

    suspendFlagsField = typeJavaThread.getCIntegerField("_suspend_flags");

    tlabFieldOffset    = typeThread.getField("_tlab").getOffset();
    currentPendingMonitorField = typeJavaThread.getAddressField("_current_pending_monitor");
    currentWaitingMonitorField = typeJavaThread.getAddressField("_current_waiting_monitor");
    allocatedBytesField = typeThread.getJLongField("_allocated_bytes");
  }

  public Thread(Address addr) {
    super(addr);
  }

  public int suspendFlags() {
    return (int) suspendFlagsField.getValue(addr);
  }

  public ThreadLocalAllocBuffer tlab() {
    return new ThreadLocalAllocBuffer(addr.addOffsetTo(tlabFieldOffset));
  }

  public long allocatedBytes() {
    return allocatedBytesField.getValue(addr);
  }

  public boolean   isVMThread()                  { return false; }
  public boolean   isJavaThread()                { return false; }
  public boolean   isCompilerThread()            { return false; }
  public boolean   isCodeCacheSweeperThread()    { return false; }
  public boolean   isHiddenFromExternalView()    { return false; }
  public boolean   isJvmtiAgentThread()          { return false; }
  public boolean   isWatcherThread()             { return false; }
  public boolean   isServiceThread()             { return false; }
  public boolean   isMonitorDeflationThread()    { return false; }

  /** Memory operations */
  public void oopsDo(AddressVisitor oopVisitor) {
    // FIXME: Empty for now; will later traverse JNI handles and
    // pending exception
  }

  public ObjectMonitor getCurrentPendingMonitor() {
    Address monitorAddr = currentPendingMonitorField.getValue(addr);
    if (monitorAddr == null) {
      return null;
    }
    return new ObjectMonitor(monitorAddr);
  }

  public ObjectMonitor getCurrentWaitingMonitor() {
    Address monitorAddr = currentWaitingMonitorField.getValue(addr);
    if (monitorAddr == null) {
      return null;
    }
    return new ObjectMonitor(monitorAddr);
  }

  public boolean isLockOwned(Address lock) {
    if (isInStack(lock)) return true;
    return false;
  }

  public boolean isInStack(Address a) {
    // In the Serviceability Agent we need access to the thread's
    // stack pointer to be able to answer this question. Since it is
    // only a debugging system at the moment we need access to the
    // underlying thread, which is only present for Java threads; see
    // JavaThread.java.
    return false;
  }

  /** Assistance for ObjectMonitor implementation */
  Address threadObjectAddress() { return addr; }
}
