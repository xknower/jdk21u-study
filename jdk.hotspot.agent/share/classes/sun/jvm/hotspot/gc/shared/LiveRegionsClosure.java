package sun.jvm.hotspot.gc.shared;

public interface LiveRegionsClosure {
  public void doLiveRegions(LiveRegionsProvider lrp);
}
