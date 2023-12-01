package sun.jvm.hotspot.gc.g1;

import java.io.PrintStream;
import sun.jvm.hotspot.gc.g1.HeapRegion;

public class PrintRegionClosure implements HeapRegionClosure {
    private PrintStream tty;

    public PrintRegionClosure(PrintStream tty) {
        this.tty = tty;
    }

    public void doHeapRegion(HeapRegion hr) {
        hr.printOn(tty);
    }
}
