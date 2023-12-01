package java.security;

import java.util.Set;

/**
 * This interface specifies constraints for cryptographic algorithms,
 * keys (key sizes), and other algorithm parameters.
 * <p>
 * {@code AlgorithmConstraints} objects are immutable.  An implementation
 * of this interface should not provide methods that can change the state
 * of an instance once it has been created.
 * <p>
 * Note that {@code AlgorithmConstraints} can be used to represent the
 * restrictions described by the security properties
 * {@code jdk.certpath.disabledAlgorithms} and
 * {@code jdk.tls.disabledAlgorithms}, or could be used by a
 * concrete {@code PKIXCertPathChecker} to check whether a specified
 * certificate in the certification path contains the required algorithm
 * constraints.
 *
 * @see javax.net.ssl.SSLParameters#getAlgorithmConstraints
 * @see javax.net.ssl.SSLParameters#setAlgorithmConstraints(AlgorithmConstraints)
 *
 * @since 1.7
 */

public interface AlgorithmConstraints {

    /**
     * Determines whether an algorithm is granted permission for the
     * specified cryptographic primitives.
     *
     * @param primitives a set of cryptographic primitives
     * @param algorithm the algorithm name
     * @param parameters the algorithm parameters, or {@code null}
     *     if no additional parameters
     *
     * @return {@code true} if the algorithm is permitted and can be used for
     *     all the specified cryptographic primitives
     *
     * @throws IllegalArgumentException if primitives or algorithm is
     *     {@code null} or empty
     */
    boolean permits(Set<CryptoPrimitive> primitives,
            String algorithm, AlgorithmParameters parameters);

    /**
     * Determines whether a key is granted permission for the specified
     * cryptographic primitives.
     * <p>
     * This method is usually used to check key size and key usage.
     *
     * @param primitives a set of cryptographic primitives
     * @param key the key
     *
     * @return {@code true} if the key can be used for all the specified
     *     cryptographic primitives
     *
     * @throws IllegalArgumentException if primitives is {@code null}
     * or empty, or the key is {@code null}
     */
    boolean permits(Set<CryptoPrimitive> primitives, Key key);

    /**
     * Determines whether an algorithm and the corresponding key are granted
     * permission for the specified cryptographic primitives.
     *
     * @param primitives a set of cryptographic primitives
     * @param algorithm the algorithm name
     * @param key the key
     * @param parameters the algorithm parameters, or {@code null}
     *     if no additional parameters
     *
     * @return {@code true} if the key and the algorithm can be used for all the
     *     specified cryptographic primitives
     *
     * @throws IllegalArgumentException if primitives or algorithm is
     *     {@code null} or empty, or the key is {@code null}
     */
    boolean permits(Set<CryptoPrimitive> primitives,
                String algorithm, Key key, AlgorithmParameters parameters);

}
