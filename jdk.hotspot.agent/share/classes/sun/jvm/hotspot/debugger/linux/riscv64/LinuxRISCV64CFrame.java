package sun.jvm.hotspot.debugger.linux.riscv64;

import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.debugger.riscv64.*;
import sun.jvm.hotspot.debugger.linux.*;
import sun.jvm.hotspot.debugger.cdbg.*;
import sun.jvm.hotspot.debugger.cdbg.basic.*;

public final class LinuxRISCV64CFrame extends BasicCFrame {
   private static final int C_FRAME_LINK_OFFSET        = -2;
   private static final int C_FRAME_RETURN_ADDR_OFFSET = -1;

   public LinuxRISCV64CFrame(LinuxDebugger dbg, Address fp, Address pc) {
      super(dbg.getCDebugger());
      this.fp = fp;
      this.pc = pc;
      this.dbg = dbg;
   }

   // override base class impl to avoid ELF parsing
   public ClosestSymbol closestSymbolToPC() {
      // try native lookup in debugger.
      return dbg.lookup(dbg.getAddressValue(pc()));
   }

   public Address pc() {
      return pc;
   }

   public Address localVariableBase() {
      return fp;
   }

   public CFrame sender(ThreadProxy thread) {
      RISCV64ThreadContext context = (RISCV64ThreadContext) thread.getContext();
      Address rsp = context.getRegisterAsAddress(RISCV64ThreadContext.SP);

      if ((fp == null) || fp.lessThan(rsp)) {
        return null;
      }

      // Check alignment of fp
      if (dbg.getAddressValue(fp) % (2 * ADDRESS_SIZE) != 0) {
        return null;
      }

      Address nextFP = fp.getAddressAt(C_FRAME_LINK_OFFSET * ADDRESS_SIZE);
      if (nextFP == null || nextFP.lessThanOrEqual(fp)) {
        return null;
      }
      Address nextPC  = fp.getAddressAt(C_FRAME_RETURN_ADDR_OFFSET * ADDRESS_SIZE);
      if (nextPC == null) {
        return null;
      }
      return new LinuxRISCV64CFrame(dbg, nextFP, nextPC);
   }

   // package/class internals only
   private static final int ADDRESS_SIZE = 8;
   private Address pc;
   private Address sp;
   private Address fp;
   private LinuxDebugger dbg;
}
