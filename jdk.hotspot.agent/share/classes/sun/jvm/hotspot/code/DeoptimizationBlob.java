package sun.jvm.hotspot.code;

import java.util.*;
import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.runtime.*;
import sun.jvm.hotspot.types.*;
import sun.jvm.hotspot.utilities.Observable;
import sun.jvm.hotspot.utilities.Observer;

public class DeoptimizationBlob extends SingletonBlob {
  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static void initialize(TypeDataBase db) {
    Type type = db.lookupType("DeoptimizationBlob");

    // FIXME: add any needed fields
  }

  public DeoptimizationBlob(Address addr) {
    super(addr);
  }

  public boolean isDeoptimizationStub() {
    return true;
  }
}
