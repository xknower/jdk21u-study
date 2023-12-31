package sun.jvm.hotspot.gc.g1;

import java.util.Iterator;
import sun.jvm.hotspot.utilities.Observable;
import sun.jvm.hotspot.utilities.Observer;

import sun.jvm.hotspot.debugger.Address;
import sun.jvm.hotspot.runtime.VM;
import sun.jvm.hotspot.runtime.VMObject;
import sun.jvm.hotspot.runtime.VMObjectFactory;
import sun.jvm.hotspot.types.AddressField;
import sun.jvm.hotspot.types.CIntegerField;
import sun.jvm.hotspot.types.Type;
import sun.jvm.hotspot.types.TypeDataBase;

// Mirror class for HeapRegionManager.

public class HeapRegionManager extends VMObject {
    // G1HeapRegionTable _regions
    private static long regionsFieldOffset;

    static {
        VM.registerVMInitializedObserver(new Observer() {
                public void update(Observable o, Object data) {
                    initialize(VM.getVM().getTypeDataBase());
                }
            });
    }

    private static synchronized void initialize(TypeDataBase db) {
        Type type = db.lookupType("HeapRegionManager");

        regionsFieldOffset = type.getField("_regions").getOffset();
    }

    private G1HeapRegionTable regions() {
        Address regionsAddr = addr.addOffsetTo(regionsFieldOffset);
        return VMObjectFactory.newObject(G1HeapRegionTable.class, regionsAddr);
    }

    public long capacity() {
        return length() * HeapRegion.grainBytes();
    }

    public long length() {
        return regions().length();
    }

    public Iterator<HeapRegion> heapRegionIterator() {
        return regions().heapRegionIterator(length());
    }

    public HeapRegionManager(Address addr) {
        super(addr);
    }

    public HeapRegion getByAddress(Address addr) {
      return regions().getByAddress(addr);
    }
}
