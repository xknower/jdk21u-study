package sun.jvm.hotspot.gc.g1;

import java.io.PrintStream;
import java.util.Iterator;
import sun.jvm.hotspot.utilities.Observable;
import sun.jvm.hotspot.utilities.Observer;

import sun.jvm.hotspot.debugger.Address;
import sun.jvm.hotspot.gc.g1.HeapRegionClosure;
import sun.jvm.hotspot.gc.g1.PrintRegionClosure;
import sun.jvm.hotspot.gc.shared.CollectedHeap;
import sun.jvm.hotspot.gc.shared.CollectedHeapName;
import sun.jvm.hotspot.gc.shared.LiveRegionsClosure;
import sun.jvm.hotspot.memory.MemRegion;
import sun.jvm.hotspot.runtime.VM;
import sun.jvm.hotspot.runtime.VMObjectFactory;
import sun.jvm.hotspot.types.AddressField;
import sun.jvm.hotspot.types.CIntegerField;
import sun.jvm.hotspot.types.Type;
import sun.jvm.hotspot.types.TypeDataBase;
import sun.jvm.hotspot.tools.HeapSummary;

// Mirror class for G1CollectedHeap.

public class G1CollectedHeap extends CollectedHeap {
    // HeapRegionManager _hrm;
    private static long hrmFieldOffset;
    // MemRegion _g1_reserved;
    private static long g1ReservedFieldOffset;
    // size_t _summary_bytes_used;
    private static CIntegerField summaryBytesUsedField;
    // G1MonitoringSupport* _monitoring_support;
    private static AddressField monitoringSupportField;
    // HeapRegionSet _old_set;
    private static long oldSetFieldOffset;
    // HeapRegionSet _humongous_set;
    private static long humongousSetFieldOffset;

    static {
        VM.registerVMInitializedObserver(new Observer() {
                public void update(Observable o, Object data) {
                    initialize(VM.getVM().getTypeDataBase());
                }
            });
    }

    private static synchronized void initialize(TypeDataBase db) {
        Type type = db.lookupType("G1CollectedHeap");

        hrmFieldOffset = type.getField("_hrm").getOffset();
        summaryBytesUsedField = type.getCIntegerField("_summary_bytes_used");
        monitoringSupportField = type.getAddressField("_monitoring_support");
        oldSetFieldOffset = type.getField("_old_set").getOffset();
        humongousSetFieldOffset = type.getField("_humongous_set").getOffset();
    }

    public long capacity() {
        return hrm().capacity();
    }

    public long used() {
        return summaryBytesUsedField.getValue(addr);
    }

    public long n_regions() {
        return hrm().length();
    }

    public HeapRegionManager hrm() {
        Address hrmAddr = addr.addOffsetTo(hrmFieldOffset);
        return VMObjectFactory.newObject(HeapRegionManager.class, hrmAddr);
    }

    public G1MonitoringSupport monitoringSupport() {
        Address monitoringSupportAddr = monitoringSupportField.getValue(addr);
        return VMObjectFactory.newObject(G1MonitoringSupport.class, monitoringSupportAddr);
    }

    public HeapRegionSetBase oldSet() {
        Address oldSetAddr = addr.addOffsetTo(oldSetFieldOffset);
        return VMObjectFactory.newObject(HeapRegionSetBase.class, oldSetAddr);
    }

    public HeapRegionSetBase humongousSet() {
        Address humongousSetAddr = addr.addOffsetTo(humongousSetFieldOffset);
        return VMObjectFactory.newObject(HeapRegionSetBase.class, humongousSetAddr);
    }

    private Iterator<HeapRegion> heapRegionIterator() {
        return hrm().heapRegionIterator();
    }

    public void heapRegionIterate(HeapRegionClosure hrcl) {
        Iterator<HeapRegion> iter = heapRegionIterator();
        while (iter.hasNext()) {
            HeapRegion hr = iter.next();
            hrcl.doHeapRegion(hr);
        }
    }

    public CollectedHeapName kind() {
        return CollectedHeapName.G1;
    }

    @Override
    public void liveRegionsIterate(LiveRegionsClosure closure) {
        Iterator<HeapRegion> iter = heapRegionIterator();
        while (iter.hasNext()) {
            HeapRegion hr = iter.next();
            closure.doLiveRegions(hr);
        }
    }

    @Override
    public void printOn(PrintStream tty) {
        MemRegion mr = reservedRegion();

        tty.print("garbage-first heap");
        tty.print(" [" + mr.start() + ", " + mr.end() + "]");
        tty.println(" region size " + (HeapRegion.grainBytes() / 1024) + "K");

        HeapSummary sum = new HeapSummary();
        sum.printG1HeapSummary(tty, this);
    }

    public void printRegionDetails(PrintStream tty) {
        PrintRegionClosure prc = new PrintRegionClosure(tty);
        heapRegionIterate(prc);
    }

    public G1CollectedHeap(Address addr) {
        super(addr);
    }
}
