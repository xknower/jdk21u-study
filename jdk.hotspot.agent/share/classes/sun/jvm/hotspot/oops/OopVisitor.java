package sun.jvm.hotspot.oops;

// An OopVisitor can be used to inspect all fields within an object.
// Fields include vm fields, java fields, indexable fields.

public interface OopVisitor extends FieldVisitor {
  // Called before visiting an object
  public void prologue();

  // Called after visiting an object
  public void epilogue();

  public void setObj(Oop obj);

  // Returns the object being visited
  public Oop getObj();
};
