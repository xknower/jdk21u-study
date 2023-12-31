package sun.jvm.hotspot.interpreter;

import sun.jvm.hotspot.oops.*;
import sun.jvm.hotspot.utilities.*;
import sun.jvm.hotspot.runtime.VM;

public class Bytecode {
  Method method;
  int bci;
  static final int jintSize = 4;
  static final String spaces = " ";
  static final String comma  = ", ";

  Bytecode(Method method, int bci) {
    this.method = method;
    this.bci    = bci;
  }

  // Address computation
  // NOTE: assumes that the start of the method's bytecodes is 4-byte aligned
  int alignedOffset(int offset) {
    return Bits.roundTo(bci + offset, jintSize) - bci;
  }

  public int     getIndexU1()               { return method.getBytecodeOrBPAt(bci() + 1) & 0xFF; }
  public int     getIndexU2(int bc, boolean isWide) {
    if (can_use_native_byte_order(bc, isWide)) {
      return method.getNativeShortArg(bci() + (isWide ? 2 : 1)) & 0xFFFF;
    }
    return method.getBytecodeShortArg(bci() + (isWide ? 2 : 1)) & 0xFFFF;
  }
  public int     getIndexU4()               { return method.getNativeIntArg(bci() + 1); }
  public boolean hasIndexU4()               { return code() == Bytecodes._invokedynamic; }

  public int     getIndexU1Cpcache()        { return method.getBytecodeOrBPAt(bci() + 1) & 0xFF; }
  public int     getIndexU2Cpcache()        { return method.getNativeShortArg(bci() + 1) & 0xFFFF; }

  static boolean can_use_native_byte_order(int bc, boolean is_wide) {
    return (VM.getVM().isBigEndian() || Bytecodes.native_byte_order(bc /*, is_wide*/));
  }

  int javaSignedWordAt(int offset) {
    return method.getBytecodeIntArg(bci + offset);
  }

  short javaShortAt(int offset) {
    return method.getBytecodeShortArg(bci + offset);
  }

  byte javaByteAt(int offset) {
    return method.getBytecodeByteArg(bci + offset);
  }

  public Method method() { return method; }
  public int    bci()    { return bci;    }

  // hotspot byte code
  public int code() {
    return Bytecodes.codeAt(method(), bci());
  }

  // jvm byte code
  public int javaCode() {
    return Bytecodes.javaCode(code());
  }

  public String getBytecodeName() {
    return Bytecodes.name(code());
  }

  public String getJavaBytecodeName() {
    return Bytecodes.name(javaCode());
  }

  public int getLength() {
    return Bytecodes.lengthAt(method(), bci());
  }

  public int getJavaLength() {
    return Bytecodes.javaLengthAt(method(), bci());
  }

  public String toString() {
    StringBuilder buf = new StringBuilder(getJavaBytecodeName());
    if (code() != javaCode()) {
       buf.append(spaces);
       buf.append('[');
       buf.append(getBytecodeName());
       buf.append(']');
    }
    return buf.toString();
  }
}
