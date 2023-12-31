package sun.security.krb5.internal.crypto;

import sun.security.krb5.*;
import sun.security.krb5.internal.*;

public class Crc32CksumType extends CksumType {

    public Crc32CksumType() {
    }

    public int confounderSize() {
        return 0;
    }

    public int cksumType() {
        return Checksum.CKSUMTYPE_CRC32;
    }

    public boolean isKeyed() {
        return false;
    }

    public int cksumSize() {
        return 4;
    }

    public int keyType() {
        return Krb5.KEYTYPE_NULL;
    }

    public int keySize() {
        return 0;
    }

    public byte[] calculateChecksum(byte[] data, int size,
            byte[] key, int usage) {
        return crc32.byte2crc32sum_bytes(data, size);
    }

    public boolean verifyChecksum(byte[] data, int size,
            byte[] key, byte[] checksum, int usage) {
        return CksumType.isChecksumEqual(checksum,
                crc32.byte2crc32sum_bytes(data));
    }

    public static byte[] int2quad(long input) {
        byte[] output = new byte[4];
        for (int i = 0; i < 4; i++) {
            output[i] = (byte)((input >>> (i * 8)) & 0xff);
        }
        return output;
    }

    public static long bytes2long(byte[] input) {
        long result = 0;

        result |= (((long)input[0]) & 0xffL) << 24;
        result |= (((long)input[1]) & 0xffL) << 16;
        result |= (((long)input[2]) & 0xffL) << 8;
        result |= (((long)input[3]) & 0xffL);
        return result;
    }
}
