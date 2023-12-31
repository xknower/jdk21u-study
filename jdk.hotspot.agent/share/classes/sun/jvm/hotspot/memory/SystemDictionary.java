package sun.jvm.hotspot.memory;

import java.util.*;
import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.classfile.*;
import sun.jvm.hotspot.oops.*;
import sun.jvm.hotspot.runtime.*;
import sun.jvm.hotspot.types.*;
import sun.jvm.hotspot.utilities.Observable;
import sun.jvm.hotspot.utilities.Observer;

public class SystemDictionary {

  private static AddressField objectKlassField;
  private static AddressField classLoaderKlassField;
  private static AddressField stringKlassField;
  private static AddressField systemKlassField;
  private static AddressField threadKlassField;
  private static AddressField threadFieldHolderKlassField;
  private static AddressField methodHandleKlassField;

  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static synchronized void initialize(TypeDataBase db) {
    Type type = db.lookupType("vmClasses");

    // Note, vmStructs contains a field with the name "_klasses[static_cast<int>(vmClassID::Object_klass_knum)]"
    objectKlassField = type.getAddressField(VM_CLASS_AT("Object_klass"));
    classLoaderKlassField = type.getAddressField(VM_CLASS_AT("ClassLoader_klass"));
    stringKlassField = type.getAddressField(VM_CLASS_AT("String_klass"));
    systemKlassField = type.getAddressField(VM_CLASS_AT("System_klass"));
    threadKlassField = type.getAddressField(VM_CLASS_AT("Thread_klass"));
    threadFieldHolderKlassField = type.getAddressField(VM_CLASS_AT("Thread_FieldHolder_klass"));
    methodHandleKlassField = type.getAddressField(VM_CLASS_AT("MethodHandle_klass"));
  }

  // The following 3 methods must match the corresponding macros in vmClassMacros.hpp and vmClasses.hpp.
  private static String _VM_CLASS_ENUM(String kname) {
    // #define _VM_CLASS_ENUM(kname)    kname##_knum
    return (kname+"_knum");
  }

  private static String VM_CLASS_ID(String kname) {
    // #define VM_CLASS_ID(kname)      vmClassID::_VM_CLASS_ENUM(kname)
    return "vmClassID::" + _VM_CLASS_ENUM(kname);
  }

  private static String VM_CLASS_AT(String name) {
    // #define VM_CLASS_AT(name) _klasses[static_cast<int>(VM_CLASS_ID(name))]
    return "_klasses[static_cast<int>(" + VM_CLASS_ID(name) + ")]";
  }

  // few well known classes -- not all are added here.
  // add more if needed.
  public static InstanceKlass getThreadKlass() {
    return (InstanceKlass)Metadata.instantiateWrapperFor(threadKlassField.getValue());
  }

  public static InstanceKlass getThreadFieldHolderKlass() {
    return (InstanceKlass)Metadata.instantiateWrapperFor(threadFieldHolderKlassField.getValue());
  }

  public static InstanceKlass getObjectKlass() {
    return (InstanceKlass)Metadata.instantiateWrapperFor(objectKlassField.getValue());
  }

  public static InstanceKlass getStringKlass() {
    return (InstanceKlass)Metadata.instantiateWrapperFor(stringKlassField.getValue());
  }

  public static InstanceKlass getClassLoaderKlass() {
    return (InstanceKlass)Metadata.instantiateWrapperFor(classLoaderKlassField.getValue());
  }

  public static InstanceKlass getSystemKlass() {
    return (InstanceKlass)Metadata.instantiateWrapperFor(systemKlassField.getValue());
  }

  public static InstanceKlass getMethodHandleKlass() {
    return (InstanceKlass)Metadata.instantiateWrapperFor(methodHandleKlassField.getValue());
  }

  public InstanceKlass getAbstractOwnableSynchronizerKlass() {
    ClassLoaderDataGraph cldg = VM.getVM().getClassLoaderDataGraph();
    return (InstanceKlass) cldg.find("java/util/concurrent/locks/AbstractOwnableSynchronizer");
  }

  private static Oop newOop(OopHandle handle) {
    return VM.getVM().getObjectHeap().newOop(handle);
  }
}
