package sun.jvm.hotspot.types;

import sun.jvm.hotspot.debugger.*;

/** A specialization of Field which represents a field containing a
    Java byte value (in either a C/C++ data structure or a Java
    object) and which adds typechecked getValue() routines returning
    bytes. */

public interface JByteField extends Field {
  /** The field must be nonstatic and the type of the field must be a
      Java byte, or a WrongTypeException will be thrown. */
  public byte getValue(Address addr) throws UnmappedAddressException, UnalignedAddressException, WrongTypeException;

  /** The field must be static and the type of the field must be a
      Java byte, or a WrongTypeException will be thrown. */
  public byte getValue() throws UnmappedAddressException, UnalignedAddressException, WrongTypeException;
}
