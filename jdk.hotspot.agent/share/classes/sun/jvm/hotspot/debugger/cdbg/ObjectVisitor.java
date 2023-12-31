package sun.jvm.hotspot.debugger.cdbg;

import sun.jvm.hotspot.debugger.*;

/** Provides uniform visitation to primitive and compound objects.
    Typically one will have an Address of an "object" (in the
    idealistic C++ definition, including "primitive objects" like
    ints) and a Type for that object. This visitor interface allows
    one to either get the value of the object (if of primitive type)
    or iterate through its fields, getting the value of each, in a
    consistent fashion. Also supports iteration through arrays of
    known length. */

public interface ObjectVisitor {
  /** This is called before beginning iterating through either the
      fields declared in this compound type (not its superclasses) or
      the elements of this array */
  public void enterType(Type type, Address objectAddress);

  /** This is called after finishing iterating through this compound
      type */
  public void exitType();

  /** Primitive field or object of integer bitfield
      type. FieldIdentifier is null if not a field of an enclosing
      object. */
  public void doBit(FieldIdentifier f, long val);

  /** Primitive field or object of integer type. FieldIdentifier is
      null if not a field of an enclosing object. */
  public void doInt(FieldIdentifier f, long val);

  /** Primitive field or object of enumerated type type.
      FieldIdentifier is null if not a field of an enclosing
      object. */
  public void doEnum(FieldIdentifier f, long val, String enumName);

  /** Primitive field or object of single-precision floating-point
      type. FieldIdentifier is null if not a field of an enclosing
      object. */
  public void doFloat(FieldIdentifier f, float val);

  /** Primitive field or object of double-precision floating-point
      type. FieldIdentifier is null if not a field of an enclosing
      object. */
  public void doDouble(FieldIdentifier f, double val);

  /** Primitive field or object of pointer type. FieldIdentifier is
      null if not a field of an enclosing object. */
  public void doPointer(FieldIdentifier f, Address val);

  /** Primitive field or object of array type. FieldIdentifier is null
      if not a field of an enclosing object. */
  public void doArray(FieldIdentifier f, Address val);

  /** Primitive field or object of (C++) reference
      type. FieldIdentifier is null if not a field of an enclosing
      object. */
  public void doRef(FieldIdentifier f, Address val);

  /** Identifies embedded objects in compound objects. FieldIdentifier
      is null if not a field of an enclosing object. */
  public void doCompound(FieldIdentifier f, Address addressOfEmbeddedCompoundObject);
}
