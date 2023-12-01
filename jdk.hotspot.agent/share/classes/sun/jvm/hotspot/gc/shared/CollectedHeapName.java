package sun.jvm.hotspot.gc.shared;

/** Mimics the enums in the VM under CollectedHeap::Name */

public class CollectedHeapName {
  private String name;

  private CollectedHeapName(String name) { this.name = name; }

  public static final CollectedHeapName SERIAL = new CollectedHeapName("Serial");
  public static final CollectedHeapName PARALLEL = new CollectedHeapName("Parallel");
  public static final CollectedHeapName G1 = new CollectedHeapName("G1");
  public static final CollectedHeapName EPSILON = new CollectedHeapName("Epsilon");
  public static final CollectedHeapName Z = new CollectedHeapName("Z");
  public static final CollectedHeapName SHENANDOAH = new CollectedHeapName("Shenandoah");

  public String toString() {
    return name;
  }
}
