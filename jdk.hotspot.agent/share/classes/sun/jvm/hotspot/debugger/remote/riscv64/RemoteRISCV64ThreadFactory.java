package sun.jvm.hotspot.debugger.remote.riscv64;

import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.debugger.remote.*;

public class RemoteRISCV64ThreadFactory implements RemoteThreadFactory {
  private RemoteDebuggerClient debugger;

  public RemoteRISCV64ThreadFactory(RemoteDebuggerClient debugger) {
    this.debugger = debugger;
  }

  public ThreadProxy createThreadWrapper(Address threadIdentifierAddr) {
    return new RemoteRISCV64Thread(debugger, threadIdentifierAddr);
  }

  public ThreadProxy createThreadWrapper(long id) {
    return new RemoteRISCV64Thread(debugger, id);
  }
}
