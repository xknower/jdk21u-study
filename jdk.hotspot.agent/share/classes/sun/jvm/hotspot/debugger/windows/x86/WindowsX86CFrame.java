package sun.jvm.hotspot.debugger.windows.x86;

import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.debugger.x86.*;
import sun.jvm.hotspot.debugger.cdbg.*;
import sun.jvm.hotspot.debugger.cdbg.basic.*;
import sun.jvm.hotspot.debugger.windbg.*;

public class WindowsX86CFrame extends BasicCFrame {
  private Address ebp;
  private Address pc;

  private static final int ADDRESS_SIZE = 4;

  /** Constructor for topmost frame */
  public WindowsX86CFrame(WindbgDebugger dbg, Address ebp, Address pc) {
    super(dbg.getCDebugger());
    this.ebp = ebp;
    this.pc  = pc;
    this.dbg = dbg;
  }

  public CFrame sender(ThreadProxy thread) {
    X86ThreadContext context = (X86ThreadContext) thread.getContext();
    /*
     * Native code fills in the stack pointer register value using index
     * X86ThreadContext.SP.
     * See file sawindbg.cpp macro REG_INDEX(x).
     *
     * Be sure to use SP, or UESP which is aliased to SP in Java code,
     * for the frame pointer validity check.
     */
    Address esp = context.getRegisterAsAddress(X86ThreadContext.SP);

    if ( (ebp == null) || ebp.lessThan(esp) ) {
      return null;
    }

    // Check alignment of ebp
    if ( dbg.getAddressValue(ebp) % ADDRESS_SIZE != 0) {
        return null;
    }

    Address nextEBP = ebp.getAddressAt( 0 * ADDRESS_SIZE);
    if (nextEBP == null || nextEBP.lessThanOrEqual(ebp)) {
      return null;
    }
    Address nextPC  = ebp.getAddressAt( 1 * ADDRESS_SIZE);
    if (nextPC == null) {
      return null;
    }
    return new WindowsX86CFrame(dbg, nextEBP, nextPC);
  }

  public Address pc() {
    return pc;
  }

  public Address localVariableBase() {
    return ebp;
  }

  private WindbgDebugger dbg;
}
