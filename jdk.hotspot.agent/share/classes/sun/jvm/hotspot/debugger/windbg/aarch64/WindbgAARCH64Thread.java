package sun.jvm.hotspot.debugger.windbg.aarch64;

import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.debugger.windbg.*;

class WindbgAARCH64Thread implements ThreadProxy {
  private WindbgDebugger debugger;
  private long           sysId;
  private boolean        gotID;
  private long           id;

  // The address argument must be the address of the OSThread::_thread_id
  WindbgAARCH64Thread(WindbgDebugger debugger, Address addr) {
    this.debugger = debugger;
    this.sysId    = addr.getCIntegerAt(0, 4, true);
    gotID         = false;
  }

  WindbgAARCH64Thread(WindbgDebugger debugger, long sysId) {
    this.debugger = debugger;
    this.sysId    = sysId;
    gotID         = false;
  }

  public ThreadContext getContext() throws IllegalThreadStateException {
    long[] data = debugger.getThreadIntegerRegisterSet(getThreadID());
    WindbgAARCH64ThreadContext context = new WindbgAARCH64ThreadContext(debugger);
    for (int i = 0; i < data.length; i++) {
      context.setRegister(i, data[i]);
    }
    return context;
  }

  public boolean canSetContext() throws DebuggerException {
    return false;
  }

  public void setContext(ThreadContext thrCtx)
    throws IllegalThreadStateException, DebuggerException {
    throw new DebuggerException("Unimplemented");
  }

  public boolean equals(Object obj) {
    if (!(obj instanceof WindbgAARCH64Thread other)) {
      return false;
    }

    return (other.getThreadID() == getThreadID());
  }

  public int hashCode() {
    return Long.hashCode(getThreadID());
  }

  public String toString() {
    return Long.toString(getThreadID());
  }

  /** Retrieves the thread ID of this thread by examining the Thread
      Information Block. */
  private long getThreadID() {
    if (!gotID) {
       id = debugger.getThreadIdFromSysId(sysId);
    }

    return id;
  }
}
