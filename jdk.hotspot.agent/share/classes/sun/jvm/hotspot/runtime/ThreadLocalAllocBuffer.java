package sun.jvm.hotspot.runtime;

import java.io.*;
import java.util.*;
import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.oops.*;
import sun.jvm.hotspot.types.*;
import sun.jvm.hotspot.utilities.Observable;
import sun.jvm.hotspot.utilities.Observer;

/** <P> ThreadLocalAllocBuffer: a descriptor for thread-local storage
    used by the threads for allocation. </P>

    <P> It is thread-private at any time, but maybe multiplexed over
    time across multiple threads. </P> */

public class ThreadLocalAllocBuffer extends VMObject {
  private static AddressField  startField;
  private static AddressField  topField;
  private static AddressField  endField;
  private static CIntegerField desired_sizeField;

  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static synchronized void initialize(TypeDataBase db) {
    Type type = db.lookupType("ThreadLocalAllocBuffer");

    startField         = type.getAddressField("_start");
    topField           = type.getAddressField("_top");
    endField           = type.getAddressField("_end");
    desired_sizeField          = type.getCIntegerField("_desired_size");
  }

  public ThreadLocalAllocBuffer(Address addr) {
    super(addr);
  }

  public Address start()    { return startField.getValue(addr); }
  public Address end()      { return   endField.getValue(addr); }
  public Address top()      { return   topField.getValue(addr); }
  public Address hardEnd()  { return end().addOffsetTo(alignmentReserve()); }

  private long alignmentReserve() {
    return Oop.alignObjectSize(endReserve());
  }

  private long endReserve() {
    long minFillerArraySize = Array.baseOffsetInBytes(BasicType.T_INT);
    long reserveForAllocationPrefetch = VM.getVM().getReserveForAllocationPrefetch();
    long heapWordSize = VM.getVM().getHeapWordSize();

    return Math.max(minFillerArraySize, reserveForAllocationPrefetch * heapWordSize);
  }

  /** Support for iteration over heap -- not sure how this will
      interact with GC in reflective system, but necessary for the
      debugging mechanism */
  public OopHandle startAsOopHandle() {
    return startField.getOopHandle(addr);
  }

  /** Support for iteration over heap -- not sure how this will
      interact with GC in reflective system, but necessary for the
      debugging mechanism */
  public OopHandle nextOopHandle(OopHandle handle, long size) {
    return handle.addOffsetToAsOopHandle(size);
  }

  //  public int     size();           // FIXME: must adjust this to be in bytes
  //  public int     availableSize();  // FIXME: must adjust this to be in bytes
  public void print() {
    printOn(System.out);
  }

  public boolean contains(Address p) {
    if (top() == null) {
      return false;
    }
    return (start().lessThanOrEqual(p) && top().greaterThan(p));
  }

  public void printOn(PrintStream tty) {
    tty.println(" [" + start() + "," +
                top() + "," + end() + ",{" + hardEnd() + "})");
  }
}
