package sun.security.krb5.internal.crypto;

import sun.security.krb5.Checksum;
import sun.security.krb5.KrbCryptoException;
import sun.security.krb5.internal.*;

public abstract class CksumType {

    private static boolean DEBUG = Krb5.DEBUG;

    public static CksumType getInstance(int cksumTypeConst)
        throws KdcErrException {
        CksumType cksumType = null;
        String cksumTypeName = null;
        switch (cksumTypeConst) {
        case Checksum.CKSUMTYPE_CRC32:
            cksumType = new Crc32CksumType();
            cksumTypeName = "sun.security.krb5.internal.crypto.Crc32CksumType";
            break;
        case Checksum.CKSUMTYPE_DES_MAC:
            cksumType = new DesMacCksumType();
            cksumTypeName = "sun.security.krb5.internal.crypto.DesMacCksumType";
            break;
        case Checksum.CKSUMTYPE_DES_MAC_K:
            cksumType = new DesMacKCksumType();
            cksumTypeName =
                "sun.security.krb5.internal.crypto.DesMacKCksumType";
            break;
        case Checksum.CKSUMTYPE_RSA_MD5:
            cksumType = new RsaMd5CksumType();
            cksumTypeName = "sun.security.krb5.internal.crypto.RsaMd5CksumType";
            break;
        case Checksum.CKSUMTYPE_RSA_MD5_DES:
            cksumType = new RsaMd5DesCksumType();
            cksumTypeName =
                "sun.security.krb5.internal.crypto.RsaMd5DesCksumType";
            break;

        case Checksum.CKSUMTYPE_HMAC_SHA1_DES3_KD:
            cksumType = new HmacSha1Des3KdCksumType();
            cksumTypeName =
                "sun.security.krb5.internal.crypto.HmacSha1Des3KdCksumType";
            break;

        case Checksum.CKSUMTYPE_HMAC_SHA1_96_AES128:
            cksumType = new HmacSha1Aes128CksumType();
            cksumTypeName =
                "sun.security.krb5.internal.crypto.HmacSha1Aes128CksumType";
            break;

        case Checksum.CKSUMTYPE_HMAC_SHA1_96_AES256:
            cksumType = new HmacSha1Aes256CksumType();
            cksumTypeName =
                "sun.security.krb5.internal.crypto.HmacSha1Aes256CksumType";
            break;

        case Checksum.CKSUMTYPE_HMAC_SHA256_128_AES128:
            cksumType = new HmacSha2Aes128CksumType();
            cksumTypeName =
                    "sun.security.krb5.internal.crypto.HmacSha2Aes128CksumType";
            break;

        case Checksum.CKSUMTYPE_HMAC_SHA384_192_AES256:
            cksumType = new HmacSha2Aes256CksumType();
            cksumTypeName =
                    "sun.security.krb5.internal.crypto.HmacSha2Aes256CksumType";
            break;

        case Checksum.CKSUMTYPE_HMAC_MD5_ARCFOUR:
            cksumType = new HmacMd5ArcFourCksumType();
            cksumTypeName =
                "sun.security.krb5.internal.crypto.HmacMd5ArcFourCksumType";
            break;

            // currently we don't support MD4.
        case Checksum.CKSUMTYPE_RSA_MD4_DES_K:
            // cksumType = new RsaMd4DesKCksumType();
            // cksumTypeName =
            //          "sun.security.krb5.internal.crypto.RsaMd4DesKCksumType";
        case Checksum.CKSUMTYPE_RSA_MD4:
            // cksumType = new RsaMd4CksumType();
            // linux box support rsamd4, how to solve conflict?
            // cksumTypeName =
            //          "sun.security.krb5.internal.crypto.RsaMd4CksumType";
        case Checksum.CKSUMTYPE_RSA_MD4_DES:
            // cksumType = new RsaMd4DesCksumType();
            // cksumTypeName =
            //          "sun.security.krb5.internal.crypto.RsaMd4DesCksumType";

        default:
            throw new KdcErrException(Krb5.KDC_ERR_SUMTYPE_NOSUPP);
        }
        if (DEBUG) {
            System.out.println(">>> CksumType: " + cksumTypeName);
        }
        return cksumType;
    }

    public abstract int confounderSize();

    public abstract int cksumType();

    public abstract boolean isKeyed();

    public abstract int cksumSize();

    public abstract int keyType();

    public abstract int keySize();

    // Note: key and usage will be ignored for an unkeyed checksum.
    public abstract byte[] calculateChecksum(byte[] data, int size,
        byte[] key, int usage) throws KrbCryptoException;

    // Note: key and usage will be ignored for an unkeyed checksum.
    public abstract boolean verifyChecksum(byte[] data, int size,
        byte[] key, byte[] checksum, int usage) throws KrbCryptoException;

    public static boolean isChecksumEqual(byte[] cksum1, byte[] cksum2) {
        if (cksum1 == cksum2)
            return true;
        if ((cksum1 == null && cksum2 != null) ||
            (cksum1 != null && cksum2 == null))
            return false;
        if (cksum1.length != cksum2.length)
            return false;
        for (int i = 0; i < cksum1.length; i++)
            if (cksum1[i] != cksum2[i])
                return false;
        return true;
    }

}
