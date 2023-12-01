package sun.jvm.hotspot.gc.g1;

public interface HeapRegionClosure {
    public void doHeapRegion(HeapRegion hr);
}
