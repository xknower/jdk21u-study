package sun.jvm.hotspot.runtime.riscv64;

import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.runtime.*;

public class RISCV64RegisterMap extends RegisterMap {

  /** This is the only public constructor */
  public RISCV64RegisterMap(JavaThread thread, boolean updateMap) {
    super(thread, updateMap);
  }

  protected RISCV64RegisterMap(RegisterMap map) {
    super(map);
  }

  public Object clone() {
    RISCV64RegisterMap retval = new RISCV64RegisterMap(this);
    return retval;
  }

  // no PD state to clear or copy:
  protected void clearPD() {}
  protected void initializePD() {}
  protected void initializeFromPD(RegisterMap map) {}
  protected Address getLocationPD(VMReg reg) { return null; }
}
