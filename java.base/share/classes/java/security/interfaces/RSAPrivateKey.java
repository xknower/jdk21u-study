package java.security.interfaces;

import java.math.BigInteger;

/**
 * The interface to an RSA private key.
 *
 * @author Jan Luehe
 * @since 1.2
 *
 *
 * @see RSAPrivateCrtKey
 */

public interface RSAPrivateKey extends java.security.PrivateKey, RSAKey
{

    /**
     * The type fingerprint that is set to indicate
     * serialization compatibility with a previous
     * version of the type.
     *
     * @deprecated A {@code serialVersionUID} field in an interface is
     * ineffectual. Do not use; no replacement.
     */
    @Deprecated
    @SuppressWarnings("serial")
    @java.io.Serial
    long serialVersionUID = 5187144804936595022L;

    /**
     * Returns the private exponent.
     *
     * @return the private exponent
     */
    BigInteger getPrivateExponent();
}
