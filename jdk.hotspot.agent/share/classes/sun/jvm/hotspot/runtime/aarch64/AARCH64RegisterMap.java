package sun.jvm.hotspot.runtime.aarch64;

import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.runtime.*;

public class AARCH64RegisterMap extends RegisterMap {

  /** This is the only public constructor */
  public AARCH64RegisterMap(JavaThread thread, boolean updateMap) {
    super(thread, updateMap);
  }

  protected AARCH64RegisterMap(RegisterMap map) {
    super(map);
  }

  public Object clone() {
    AARCH64RegisterMap retval = new AARCH64RegisterMap(this);
    return retval;
  }

  // no PD state to clear or copy:
  protected void clearPD() {}
  protected void initializePD() {}
  protected void initializeFromPD(RegisterMap map) {}
  protected Address getLocationPD(VMReg reg) { return null; }
}
