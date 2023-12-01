package sun.jvm.hotspot.gc.shared;

import sun.jvm.hotspot.debugger.*;

/** No additional functionality for now */

public class TenuredSpace extends ContiguousSpace {
  public TenuredSpace(Address addr) {
    super(addr);
  }
}
