package sun.jvm.hotspot.debugger;

/** Common routines for data conversion */

public class DebuggerUtilities {
  protected long addressSize;
  protected boolean isBigEndian;
  protected boolean supports32bitAlignmentOf64bitTypes;

    public DebuggerUtilities(long addressSize, boolean isBigEndian,
                             boolean supports32bitAlignmentOf64bitTypes) {
    this.addressSize = addressSize;
    this.isBigEndian = isBigEndian;
    this.supports32bitAlignmentOf64bitTypes = supports32bitAlignmentOf64bitTypes;
  }

  public String addressValueToString(long address) {
    StringBuilder buf = new StringBuilder();
    buf.append("0x");
    String val;
    // Make negative addresses have the correct size
    if (addressSize == 8) {
      val = Long.toHexString(address);
    } else {
      val = Integer.toHexString((int) address);
    }
    for (int i = 0; i < ((2 * addressSize) - val.length()); i++) {
      buf.append('0');
    }
    buf.append(val);
    return buf.toString();
  }

  public void checkAlignment(long address, long alignment) {
    // Allow 32-bit alignment for 64-bit types on some hosts.
    if (supports32bitAlignmentOf64bitTypes) {
      if (address % 4 == 0) {
        return;
      }
    }

    if (address % alignment != 0) {
      throw new UnalignedAddressException("Trying to read at address: " +
                                           addressValueToString(address) +
                                           " with alignment: " + alignment,
                                          address);
    }
  }

  public long scanAddress(String addrStr) throws NumberFormatException {
    String s = addrStr.trim();
    if (!s.startsWith("0x")) {
      throw new NumberFormatException(addrStr);
    }
    long l = 0;
    for (int i = 2; i < s.length(); ++i) {
      int val = charToNibble(s.charAt(i));
      l <<= 4;
      l |= val;
    }
    return l;
  }

  public int charToNibble(char ascii) throws NumberFormatException {
    if (ascii >= '0' && ascii <= '9') {
      return ascii - '0';
    } else if (ascii >= 'A' && ascii <= 'F') {
      return 10 + ascii - 'A';
    } else if (ascii >= 'a' && ascii <= 'f') {
      return 10 + ascii - 'a';
    }
    throw new NumberFormatException(Character.toString(ascii));
  }

  public boolean dataToJBoolean(byte[] data, long jbooleanSize) {
    checkDataContents(data, jbooleanSize);

    return (data[0] != 0);
  }

  public byte dataToJByte(byte[] data, long jbyteSize) {
    checkDataContents(data, jbyteSize);

    return data[0];
  }

  public char dataToJChar(byte[] data, long jcharSize) {
    checkDataContents(data, jcharSize);

    if (!isBigEndian) {
      byteSwap(data);
    }

    return (char) (((data[0] & 0xFF) << 8) | (data[1] & 0xFF));
  }

  public double dataToJDouble(byte[] data, long jdoubleSize) {
    long longBits = dataToJLong(data, jdoubleSize);

    return Double.longBitsToDouble(longBits);
  }

  public float dataToJFloat(byte[] data, long jfloatSize) {
    int intBits = dataToJInt(data, jfloatSize);

    return Float.intBitsToFloat(intBits);
  }

  public int dataToJInt(byte[] data, long jintSize) {
    checkDataContents(data, jintSize);

    if (!isBigEndian) {
      byteSwap(data);
    }

    return (((data[0] & 0xFF) << 24) | ((data[1] & 0xFF) << 16) | ((data[2] & 0xFF) << 8) | (data[3] & 0xFF));
  }

  public long dataToJLong(byte[] data, long jlongSize) {
    checkDataContents(data, jlongSize);

    if (!isBigEndian) {
      byteSwap(data);
    }

    return rawDataToJLong(data);
  }

  public short dataToJShort(byte[] data, long jshortSize) {
    checkDataContents(data, jshortSize);

    if (!isBigEndian) {
      byteSwap(data);
    }

    return (short) (((data[0] & 0xFF) << 8) | (data[1] & 0xFF));
  }

  public long dataToCInteger(byte[] data, boolean isUnsigned) {
    checkDataContents(data, data.length);

    if (!isBigEndian) {
      byteSwap(data);
    }

    // By default we'll be zero-extending.
    // Therefore we need to check to see whether isUnsigned is false and
    // also the high bit of the data is set.
    if ((data.length < 8) &&
        (isUnsigned == false) &&
        ((data[0] & 0x80) != 0)) {
      // Must sign-extend and right-align the data
      byte[] newData = new byte[8];
      for (int i = 0; i < 8; ++i) {
        if ((7 - i) < data.length) {
          newData[i] = data[i + data.length - 8];
        } else {
          newData[i] = (byte) 0xFF;
        }
      }
      data = newData;
    }

    // Now just do the usual loop
    return rawDataToJLong(data);
  }

  public long dataToAddressValue(byte[] data) {
    checkDataContents(data, addressSize);

    if (!isBigEndian) {
      byteSwap(data);
    }

    return rawDataToJLong(data);
  }

  public byte[] jbooleanToData(boolean value) {
    byte[] res = new byte[1];
    res[0] = (byte) (value ? 1 : 0);
    return res;
  }

  public byte[] jbyteToData(byte value) {
    byte[] res = new byte[1];
    res[0] = value;
    return res;
  }

  public byte[] jcharToData(char value) {
    byte[] res = new byte[2];
    res[0] = (byte) ((value >> 8) & 0xFF);
    res[1] = (byte) value;
    if (!isBigEndian) {
      byteSwap(res);
    }
    return res;
  }

  public byte[] jdoubleToData(double value) {
    return jlongToData(Double.doubleToLongBits(value));
  }

  public byte[] jfloatToData(float value) {
    return jintToData(Float.floatToIntBits(value));
  }

  public byte[] jintToData(int value) {
    byte[] res = new byte[4];
    for (int i = 0; i < 4; i++) {
      res[3 - i] = (byte) (value & 0xFF);
      value >>>= 8;
    }
    if (!isBigEndian) {
      byteSwap(res);
    }
    return res;
  }

  public byte[] jlongToData(long value) {
    byte[] res = new byte[8];
    for (int i = 0; i < 8; i++) {
      res[7 - i] = (byte) (value & 0xFF);
      value >>>= 8;
    }
    if (!isBigEndian) {
      byteSwap(res);
    }
    return res;
  }

  public byte[] jshortToData(short value) {
    byte[] res = new byte[2];
    res[0] = (byte) ((value >> 8) & 0xFF);
    res[1] = (byte) value;
    if (!isBigEndian) {
      byteSwap(res);
    }
    return res;
  }

  public byte[] cIntegerToData(long longNumBytes, long value) {
    int numBytes = (int) longNumBytes;
    byte[] res = new byte[numBytes];
    for (int i = 0; i < numBytes; i++) {
      res[numBytes - i - 1] = (byte) (value & 0xFF);
      value >>>= 8;
    }
    if (!isBigEndian) {
      byteSwap(res);
    }
    return res;
  }

  //--------------------------------------------------------------------------------
  // Internals only below this point
  //

  private void checkDataContents(byte[] data, long len) {
    if (data.length != (int) len) {
      throw new InternalError("Bug in Win32Debugger");
    }
  }

  private void byteSwap(byte[] data) {
    for (int i = 0; i < (data.length / 2); ++i) {
      int altIndex = data.length - i - 1;
      byte t = data[altIndex];
      data[altIndex] = data[i];
      data[i] = t;
    }
  }

  private long rawDataToJLong(byte[] data) {
    long addr = 0;
    for (int i = 0; i < data.length; ++i) {
      addr <<= 8;
      addr |= data[i] & 0xFF;
    }

    return addr;
  }
}
