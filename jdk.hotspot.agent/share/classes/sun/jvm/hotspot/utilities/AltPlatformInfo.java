package sun.jvm.hotspot.utilities;

public interface AltPlatformInfo {

  // Additional cpu types can be tested via this interface
  public boolean knownCPU(String cpu);

  // Mangle a cpu name if necessary
  public String getCPU(String cpu);
}
