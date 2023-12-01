package sun.jvm.hotspot.interpreter;

import sun.jvm.hotspot.oops.*;
import sun.jvm.hotspot.utilities.*;

public abstract class BytecodeJmp extends Bytecode {
  BytecodeJmp(Method method, int bci) {
    super(method, bci);
  }

  public abstract int getTargetBCI();

  public String toString() {
    StringBuilder buf = new StringBuilder();
    buf.append(getJavaBytecodeName());
    buf.append(spaces);
    buf.append(getTargetBCI());
    return buf.toString();
  }
}
