package sun.jvm.hotspot.memory;

//These definitions should be kept in sync with the definitions in the HotSpot code.

public enum ReferenceType {
  REF_NONE ("None reference"),       // Regular class
  REF_SOFT ("Soft reference"),       // Subclass of java/lang/ref/SoftReference
  REF_WEAK ("Weak reference"),       // Subclass of java/lang/ref/WeakReference
  REF_FINAL ("Final reference"),     // Subclass of java/lang/ref/FinalReference
  REF_PHANTOM ("Phantom reference"); // Subclass of java/lang/ref/PhantomReference

  private final String value;

  ReferenceType(String val) {
    this.value = val;
  }
  public String value() {
    return value;
  }
}
