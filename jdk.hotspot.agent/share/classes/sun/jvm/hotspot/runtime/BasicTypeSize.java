package sun.jvm.hotspot.runtime;

import sun.jvm.hotspot.types.TypeDataBase;


/** Encapsulates the BasicTypeSize enum in globalDefinitions.hpp in
    the VM. */

public class BasicTypeSize {
  private static int tBooleanSize;
  private static int tCharSize;
  private static int tFloatSize;
  private static int tDoubleSize;
  private static int tByteSize;
  private static int tShortSize;
  private static int tIntSize;
  private static int tLongSize;
  private static int tObjectSize;
  private static int tArraySize;
  private static int tNarrowOopSize;
  private static int tNarrowKlassSize;
  private static int tVoidSize;

  static {
    VM.registerVMInitializedObserver(
        (o, d) -> initialize(VM.getVM().getTypeDataBase()));
  }

  private static synchronized void initialize(TypeDataBase db) {
    tBooleanSize     = db.lookupIntConstant("T_BOOLEAN_size").intValue();
    tCharSize        = db.lookupIntConstant("T_INT_size").intValue();
    tFloatSize       = db.lookupIntConstant("T_FLOAT_size").intValue();
    tDoubleSize      = db.lookupIntConstant("T_DOUBLE_size").intValue();
    tByteSize        = db.lookupIntConstant("T_BYTE_size").intValue();
    tShortSize       = db.lookupIntConstant("T_SHORT_size").intValue();
    tIntSize         = db.lookupIntConstant("T_INT_size").intValue();
    tLongSize        = db.lookupIntConstant("T_LONG_size").intValue();
    tObjectSize      = db.lookupIntConstant("T_OBJECT_size").intValue();
    tArraySize       = db.lookupIntConstant("T_ARRAY_size").intValue();
    tNarrowOopSize   = db.lookupIntConstant("T_NARROWOOP_size").intValue();
    tNarrowKlassSize = db.lookupIntConstant("T_NARROWKLASS_size").intValue();
    tVoidSize        = db.lookupIntConstant("T_VOID_size").intValue();
  }

  public static int getTBooleanSize() {
    return tBooleanSize;
  }

  public static int getTCharSize() {
    return tCharSize;
  }

  public static int getTFloatSize() {
    return tFloatSize;
  }

  public static int getTDoubleSize() {
    return tDoubleSize;
  }

  public static int getTByteSize() {
    return tByteSize;
  }

  public static int getTShortSize() {
    return tShortSize;
  }

  public static int getTIntSize() {
    return tIntSize;
  }

  public static int getTLongSize() {
    return tLongSize;
  }

  public static int getTObjectSize() {
    return tObjectSize;
  }

  public static int getTArraySize() {
    return tArraySize;
  }

  public static int getTNarrowOopSize() {
    return tNarrowOopSize;
  }

  public static int getTNarrowKlassSize() {
    return tNarrowKlassSize;
  }

  public static int getTVoidSize() {
    return tVoidSize;
  }
}
