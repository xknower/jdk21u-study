package sun.security.krb5.internal.crypto;

import sun.security.krb5.Checksum;
import sun.security.krb5.KrbCryptoException;
import sun.security.krb5.internal.*;
import java.security.GeneralSecurityException;

/*
 * This class encapsulates the checksum type for aes256-cts-sha384
 */

public class HmacSha2Aes256CksumType extends CksumType {

    public HmacSha2Aes256CksumType() {
    }

    public int confounderSize() {
        return 16;
    }

    public int cksumType() {
        return Checksum.CKSUMTYPE_HMAC_SHA384_192_AES256;
    }

    public boolean isKeyed() {
        return true;
    }

    public int cksumSize() {
        return 24;  // bytes
    }

    public int keyType() {
        return Krb5.KEYTYPE_AES;
    }

    public int keySize() {
        return 32;   // bytes
    }

    /**
     * Calculates keyed checksum.
     * @param data the data used to generate the checksum.
     * @param size length of the data.
     * @param key the key used to encrypt the checksum.
     * @return keyed checksum.
     */
    public byte[] calculateChecksum(byte[] data, int size, byte[] key,
        int usage) throws KrbCryptoException {

         try {
            return Aes256Sha2.calculateChecksum(key, usage, data, 0, size);
         } catch (GeneralSecurityException e) {
            KrbCryptoException ke = new KrbCryptoException(e.getMessage());
            ke.initCause(e);
            throw ke;
         }
    }

    /**
     * Verifies keyed checksum.
     * @param data the data.
     * @param size the length of data.
     * @param key the key used to encrypt the checksum.
     * @param checksum the checksum.
     * @return true if verification is successful.
     */
    public boolean verifyChecksum(byte[] data, int size,
        byte[] key, byte[] checksum, int usage) throws KrbCryptoException {

         try {
            byte[] newCksum = Aes256Sha2.calculateChecksum(key, usage, data,
                                                        0, size);
            return isChecksumEqual(checksum, newCksum);
         } catch (GeneralSecurityException e) {
            KrbCryptoException ke = new KrbCryptoException(e.getMessage());
            ke.initCause(e);
            throw ke;
         }
    }
}
