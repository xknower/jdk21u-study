package sun.jvm.hotspot.debugger.remote.ppc64;

import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.debugger.ppc64.*;
import sun.jvm.hotspot.debugger.remote.*;

public class RemotePPC64ThreadContext extends PPC64ThreadContext {
  private RemoteDebuggerClient debugger;

  public RemotePPC64ThreadContext(RemoteDebuggerClient debugger) {
    super();
    this.debugger = debugger;
  }

  /** This can't be implemented in this class since we would have to
      tie the implementation to, for example, the debugging system */
  public void setRegisterAsAddress(int index, Address value) {
    setRegister(index, debugger.getAddressValue(value));
  }

  /** This can't be implemented in this class since we would have to
      tie the implementation to, for example, the debugging system */
  public Address getRegisterAsAddress(int index) {
    return debugger.newAddress(getRegister(index));
  }
}
