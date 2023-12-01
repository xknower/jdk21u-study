package sun.jvm.hotspot.code;

import java.util.*;
import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.runtime.*;
import sun.jvm.hotspot.types.*;
import sun.jvm.hotspot.utilities.Observable;
import sun.jvm.hotspot.utilities.Observer;

public class AdapterBlob extends RuntimeBlob {
  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static void initialize(TypeDataBase db) {
    // Type type = db.lookupType("AdapterBlob");

    // // FIXME: add any needed fields
  }

  public AdapterBlob(Address addr) {
    super(addr);
  }

  public boolean isAdapterBlob() {
    return true;
  }

  public String getName() {
    return "AdapterBlob: " + super.getName();
  }
}
