package sun.jvm.hotspot.memory;

import java.io.PrintStream;
import sun.jvm.hotspot.utilities.Observable;
import sun.jvm.hotspot.utilities.Observer;

import sun.jvm.hotspot.debugger.Address;
import sun.jvm.hotspot.debugger.OopHandle;
import sun.jvm.hotspot.gc.epsilon.EpsilonHeap;
import sun.jvm.hotspot.gc.g1.G1CollectedHeap;
import sun.jvm.hotspot.gc.parallel.ParallelScavengeHeap;
import sun.jvm.hotspot.gc.serial.SerialHeap;
import sun.jvm.hotspot.gc.shared.CollectedHeap;
import sun.jvm.hotspot.gc.shenandoah.ShenandoahHeap;
import sun.jvm.hotspot.gc.x.XCollectedHeap;
import sun.jvm.hotspot.gc.z.ZCollectedHeap;
import sun.jvm.hotspot.oops.Oop;
import sun.jvm.hotspot.runtime.BasicType;
import sun.jvm.hotspot.runtime.VM;
import sun.jvm.hotspot.runtime.VMObject;
import sun.jvm.hotspot.runtime.VirtualConstructor;
import sun.jvm.hotspot.types.AddressField;
import sun.jvm.hotspot.types.CIntegerField;
import sun.jvm.hotspot.types.Type;
import sun.jvm.hotspot.types.TypeDataBase;


public class Universe {
  private static AddressField collectedHeapField;
  private static VirtualConstructor heapConstructor;

  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static boolean typeExists(TypeDataBase db, String type) {
      try {
          db.lookupType(type);
      } catch (RuntimeException e) {
          return false;
      }
      return true;
  }

  private static void addHeapTypeIfInDB(TypeDataBase db, Class<? extends VMObject> heapClass) {
      String heapName = heapClass.getSimpleName();
      if (typeExists(db, heapName)) {
          heapConstructor.addMapping(heapName, heapClass);
      }
  }

  private static synchronized void initialize(TypeDataBase db) {
    Type type = db.lookupType("Universe");

    collectedHeapField = type.getAddressField("_collectedHeap");

    heapConstructor = new VirtualConstructor(db);
    addHeapTypeIfInDB(db, SerialHeap.class);
    addHeapTypeIfInDB(db, ParallelScavengeHeap.class);
    addHeapTypeIfInDB(db, G1CollectedHeap.class);
    addHeapTypeIfInDB(db, EpsilonHeap.class);
    addHeapTypeIfInDB(db, XCollectedHeap.class);
    addHeapTypeIfInDB(db, ZCollectedHeap.class);
    addHeapTypeIfInDB(db, ShenandoahHeap.class);

    UniverseExt.initialize(heapConstructor);
  }

  public Universe() {
  }
  public CollectedHeap heap() {
    return (CollectedHeap) heapConstructor.instantiateWrapperFor(collectedHeapField.getValue());
  }


  /** Returns "TRUE" iff "p" points into the allocated area of the heap. */
  public boolean isIn(Address p) {
    return heap().isIn(p);
  }

  /** Returns "TRUE" iff "p" points into the reserved area of the heap. */
  public boolean isInReserved(Address p) {
    return heap().isInReserved(p);
  }

  public void print() { printOn(System.out); }
  public void printOn(PrintStream tty) {
    heap().printOn(tty);
  }

  // Check whether an element of a typeArrayOop with the given type must be
  // aligned 0 mod 8.  The typeArrayOop itself must be aligned at least this
  // strongly.
  public static boolean elementTypeShouldBeAligned(BasicType type) {
    return type == BasicType.T_DOUBLE || type == BasicType.T_LONG;
  }

  // Check whether an object field (static/non-static) of the given type must be
  // aligned 0 mod 8.
  public static boolean fieldTypeShouldBeAligned(BasicType type) {
    return type == BasicType.T_DOUBLE || type == BasicType.T_LONG;
  }
}
