package sun.jvm.hotspot.debugger.remote.aarch64;

import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.debugger.remote.*;

public class RemoteAARCH64ThreadFactory implements RemoteThreadFactory {
  private RemoteDebuggerClient debugger;

  public RemoteAARCH64ThreadFactory(RemoteDebuggerClient debugger) {
    this.debugger = debugger;
  }

  public ThreadProxy createThreadWrapper(Address threadIdentifierAddr) {
    return new RemoteAARCH64Thread(debugger, threadIdentifierAddr);
  }

  public ThreadProxy createThreadWrapper(long id) {
    return new RemoteAARCH64Thread(debugger, id);
  }
}
