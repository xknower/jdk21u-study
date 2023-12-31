package sun.jvm.hotspot.gc.shared;

import java.io.*;
import java.util.*;

import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.gc.shared.*;
import sun.jvm.hotspot.memory.*;
import sun.jvm.hotspot.runtime.*;
import sun.jvm.hotspot.types.*;
import sun.jvm.hotspot.utilities.BitMapInterface;
import sun.jvm.hotspot.utilities.BitMapSegmented;
import sun.jvm.hotspot.utilities.Observable;
import sun.jvm.hotspot.utilities.Observer;

public abstract class CollectedHeap extends VMObject {
  private static long         reservedFieldOffset;

  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static synchronized void initialize(TypeDataBase db) {
    Type type = db.lookupType("CollectedHeap");

    reservedFieldOffset = type.getField("_reserved").getOffset();
  }

  public CollectedHeap(Address addr) {
    super(addr);
  }

  /** Returns the lowest address of the heap. */
  public Address start() {
    return reservedRegion().start();
  }

  public abstract long capacity();
  public abstract long used();

  public MemRegion reservedRegion() {
    return new MemRegion(addr.addOffsetTo(reservedFieldOffset));
  }

  public boolean isIn(Address a) {
    return isInReserved(a);
  }

  public boolean isInReserved(Address a) {
    return reservedRegion().contains(a);
  }

  public abstract CollectedHeapName kind();

  public abstract void liveRegionsIterate(LiveRegionsClosure closure);

  public String oopAddressDescription(OopHandle handle) {
      return handle.toString();
  }

  public OopHandle oop_load_at(OopHandle handle, long offset) {
      return handle.getOopHandleAt(offset);
  }

  public OopHandle oop_load_in_native(Address addr) {
      return addr.getOopHandleAt(0);
  }

  public void print() { printOn(System.out); }
  public void printOn(PrintStream tty) {
    MemRegion mr = reservedRegion();
    tty.println("unknown subtype of CollectedHeap @ " + getAddress() + " (" +
                mr.start() + "," + mr.end() + ")");
  }

  public BitMapInterface createBitMap(long bits) {
    return new BitMapSegmented(bits);
  }
}
