package sun.jvm.hotspot.gc.shared;

import java.util.List;

import sun.jvm.hotspot.memory.MemRegion;

public interface LiveRegionsProvider {
  public List<MemRegion> getLiveRegions();
}
