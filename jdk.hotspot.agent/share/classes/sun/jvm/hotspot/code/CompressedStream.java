package sun.jvm.hotspot.code;

import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.utilities.*;

/** NOTE that this class takes the address of a buffer. This means
    that it can read previously-generated debug information directly
    from the target VM. However, it also means that you can't create a
    "wrapper" object for a CompressedStream down in the VM. It looks
    like these are only kept persistently in OopMaps, and the code has
    been special-cased in OopMap.java to handle this. */

public class CompressedStream {
  protected Address buffer;
  protected int     position;

  /** Equivalent to CompressedStream(buffer, 0) */
  public CompressedStream(Address buffer) {
    this(buffer, 0);
  }

  public CompressedStream(Address buffer, int position) {
    this.buffer   = buffer;
    this.position = position;
  }

  public Address getBuffer() {
    return buffer;
  }

  public static final int LogBitsPerByte = 3;
  public static final int BitsPerByte = 1 << 3;

  // Positioning
  public int getPosition() {
    return position;
  }
  public void setPosition(int position) {
    this.position = position;
  }

  public int encodeSign(int value) {
    return Unsigned5.encodeSign(value);
  }

  public int decodeSign(int value) {
    return Unsigned5.decodeSign(value);
  }

  // 32-bit self-inverse encoding of float bits
  // converts trailing zeros (common in floats) to leading zeros
  public int reverseInt(int i) {
    return Integer.reverse(i);
  }
}
