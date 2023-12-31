package sun.jvm.hotspot.debugger.win32.coff;

/** Constants defined by the MS COFF specification indicating machine
    type of the given object file or image. (Some of the descriptions
    are taken directly from Microsoft's documentation and are
    copyrighted by Microsoft.) */

public interface MachineTypes {
  /** Contents assumed to be applicable to any machine type. */
  public static final short IMAGE_FILE_MACHINE_UNKNOWN = (short) 0x0;
  /** Alpha AXP. */
  public static final short IMAGE_FILE_MACHINE_ALPHA = (short) 0x184;
  public static final short IMAGE_FILE_MACHINE_ARM = (short) 0x1c0;
  /** Alpha AXP 64-bit. */
  public static final short IMAGE_FILE_MACHINE_ALPHA64 = (short) 0x284;
  /** Intel 386 or later, and compatible processors. */
  public static final short IMAGE_FILE_MACHINE_I386 = (short) 0x14c;
  /** Motorola 68000 series. */
  public static final short IMAGE_FILE_MACHINE_M68K = (short) 0x268;
  public static final short IMAGE_FILE_MACHINE_MIPS16 = (short) 0x266;
  /** MIPS with FPU */
  public static final short IMAGE_FILE_MACHINE_MIPSFPU = (short) 0x366;
  /** MIPS16 with FPU */
  public static final short IMAGE_FILE_MACHINE_MIPSFPU16 = (short) 0x466;
  /** Power PC, little endian. */
  public static final short IMAGE_FILE_MACHINE_POWERPC = (short) 0x1f0;
  public static final short IMAGE_FILE_MACHINE_R3000 = (short) 0x162;
  /** MIPS little endian. */
  public static final short IMAGE_FILE_MACHINE_R4000 = (short) 0x166;
  public static final short IMAGE_FILE_MACHINE_R10000 = (short) 0x168;
  /** Hitachi SH3 */
  public static final short IMAGE_FILE_MACHINE_SH3 = (short) 0x1a2;
  /** Hitachi SH4 */
  public static final short IMAGE_FILE_MACHINE_SH4 = (short) 0x1a6;
  public static final short IMAGE_FILE_MACHINE_THUMB = (short) 0x1c2;
}
