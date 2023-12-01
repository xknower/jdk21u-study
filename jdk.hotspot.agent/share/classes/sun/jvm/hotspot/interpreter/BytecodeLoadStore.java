package sun.jvm.hotspot.interpreter;

import sun.jvm.hotspot.oops.*;

public abstract class BytecodeLoadStore extends BytecodeWideable {
  BytecodeLoadStore(Method method, int bci) {
    super(method, bci);
  }

  public String toString() {
    StringBuilder buf = new StringBuilder();
    buf.append(getJavaBytecodeName());
    buf.append(spaces);
    buf.append('#');
    buf.append(getLocalVarIndex());
    return buf.toString();
  }
}
