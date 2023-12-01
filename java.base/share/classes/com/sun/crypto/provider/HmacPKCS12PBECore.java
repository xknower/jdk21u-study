package com.sun.crypto.provider;

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.PBEKeySpec;
import java.security.*;
import java.security.spec.*;
import java.util.Arrays;

import jdk.internal.access.SharedSecrets;
import sun.security.util.PBEUtil;

/**
 * This is an implementation of the HMAC algorithms as defined
 * in PKCS#12 v1.1 standard (see RFC 7292 Appendix B.4).
 *
 * @author Valerie Peng
 */
abstract class HmacPKCS12PBECore extends HmacCore {

    public static final class HmacPKCS12PBE_SHA1 extends HmacPKCS12PBECore {
        public HmacPKCS12PBE_SHA1() throws NoSuchAlgorithmException {
            super("SHA1", 64);
        }
    }

    public static final class HmacPKCS12PBE_SHA224 extends HmacPKCS12PBECore {
        public HmacPKCS12PBE_SHA224() throws NoSuchAlgorithmException {
            super("SHA-224", 64);
        }
    }

    public static final class HmacPKCS12PBE_SHA256 extends HmacPKCS12PBECore {
        public HmacPKCS12PBE_SHA256() throws NoSuchAlgorithmException {
            super("SHA-256", 64);
        }
    }

    public static final class HmacPKCS12PBE_SHA384 extends HmacPKCS12PBECore {
        public HmacPKCS12PBE_SHA384() throws NoSuchAlgorithmException {
            super("SHA-384", 128);
        }
    }

    public static final class HmacPKCS12PBE_SHA512 extends HmacPKCS12PBECore {
        public HmacPKCS12PBE_SHA512() throws NoSuchAlgorithmException {
            super("SHA-512", 128);
        }
    }

    public static final class HmacPKCS12PBE_SHA512_224 extends HmacPKCS12PBECore {
        public HmacPKCS12PBE_SHA512_224() throws NoSuchAlgorithmException {
            super("SHA-512/224", 128);
        }
    }

    public static final class HmacPKCS12PBE_SHA512_256 extends HmacPKCS12PBECore {
        public HmacPKCS12PBE_SHA512_256() throws NoSuchAlgorithmException {
            super("SHA-512/256", 128);
        }
    }

    private final String algorithm;
    private final int bl;

    /**
     * Standard constructor, creates a new HmacSHA1 instance.
     */
    public HmacPKCS12PBECore(String algorithm, int bl) throws NoSuchAlgorithmException {
        super(algorithm, bl);
        this.algorithm = algorithm;
        this.bl = bl;
    }

    /**
     * Initializes the HMAC with the given secret key and algorithm parameters.
     *
     * @param key the secret key.
     * @param params the algorithm parameters.
     *
     * @exception InvalidKeyException if the given key is inappropriate for
     * initializing this MAC.
     * @exception InvalidAlgorithmParameterException if the given algorithm
     * parameters are inappropriate for this MAC.
     */
    protected void engineInit(Key key, AlgorithmParameterSpec params)
        throws InvalidKeyException, InvalidAlgorithmParameterException {
        char[] password = null;
        byte[] derivedKey = null;
        SecretKeySpec cipherKey = null;
        PBEKeySpec keySpec = PBEUtil.getPBAKeySpec(key, params);
        try {
            password = keySpec.getPassword();
            derivedKey = PKCS12PBECipherCore.derive(
                    password, keySpec.getSalt(),
                    keySpec.getIterationCount(), engineGetMacLength(),
                    PKCS12PBECipherCore.MAC_KEY, algorithm, bl);
            cipherKey = new SecretKeySpec(derivedKey, "HmacSHA1");
            super.engineInit(cipherKey, null);
        } finally {
            if (cipherKey != null) {
                SharedSecrets.getJavaxCryptoSpecAccess()
                        .clearSecretKeySpec(cipherKey);
            }
            if (derivedKey != null) {
                Arrays.fill(derivedKey, (byte) 0);
            }
            if (password != null) {
                Arrays.fill(password, '\0');
            }
            keySpec.clearPassword();
        }
    }
}
