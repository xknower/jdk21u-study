package sun.jvm.hotspot.oops;

public interface CallTypeDataInterface<K> {
  int numberOfArguments();
  boolean hasArguments();
  K argumentType(int i);
  boolean hasReturn();
  K returnType();
  int argumentTypeIndex(int i);
  int returnTypeIndex();
}
