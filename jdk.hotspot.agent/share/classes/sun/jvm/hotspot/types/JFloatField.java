package sun.jvm.hotspot.types;

import sun.jvm.hotspot.debugger.*;

/** A specialization of Field which represents a field containing a
    Java float value (in either a C/C++ data structure or a Java
    object) and which adds typechecked getValue() routines returning
    floats. */

public interface JFloatField extends Field {
  /** The field must be nonstatic and the type of the field must be a
      Java float, or a WrongTypeException will be thrown. */
  public float getValue(Address addr) throws UnmappedAddressException, UnalignedAddressException, WrongTypeException;

  /** The field must be static and the type of the field must be a
      Java float, or a WrongTypeException will be thrown. */
  public float getValue() throws UnmappedAddressException, UnalignedAddressException, WrongTypeException;
}
