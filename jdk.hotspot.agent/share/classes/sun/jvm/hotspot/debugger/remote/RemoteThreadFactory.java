package sun.jvm.hotspot.debugger.remote;

import sun.jvm.hotspot.debugger.*;

/** An interface used only internally by the RemoteDebuggerClient to be able to
    create platform-specific Thread objects */

public interface RemoteThreadFactory {
  public ThreadProxy createThreadWrapper(Address threadIdentifierAddr);
  public ThreadProxy createThreadWrapper(long id);
}
