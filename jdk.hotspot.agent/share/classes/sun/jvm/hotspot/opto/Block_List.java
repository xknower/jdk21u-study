package sun.jvm.hotspot.opto;

import java.util.*;
import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.runtime.*;
import sun.jvm.hotspot.oops.*;
import sun.jvm.hotspot.types.*;
import sun.jvm.hotspot.utilities.Observable;
import sun.jvm.hotspot.utilities.Observer;

public class Block_List extends Block_Array {
  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static synchronized void initialize(TypeDataBase db) throws WrongTypeException {
    Type type      = db.lookupType("Block_List");
    cntField = new CIntField(type.getCIntegerField("_cnt"), 0);
  }

  private static CIntField cntField;

  public Block_List(Address addr) {
    super(addr);
  }

  public int size() {
    return (int) cntField.getValue(getAddress());
  }
}
