package com.sun.org.apache.xml.internal.security.keys.keyresolver.implementations;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import javax.crypto.SecretKey;
import com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolverException;
import com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolverSpi;
import com.sun.org.apache.xml.internal.security.keys.storage.StorageResolver;
import com.sun.org.apache.xml.internal.security.utils.Constants;
import com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import org.w3c.dom.Element;

/**
 * Resolves a single Key based on the KeyName.
 */
public class SingleKeyResolver extends KeyResolverSpi {

    private final String keyName;
    private final PublicKey publicKey;
    private final PrivateKey privateKey;
    private final SecretKey secretKey;

    /**
     * Constructor.
     * @param keyName
     * @param publicKey
     */
    public SingleKeyResolver(String keyName, PublicKey publicKey) {
        this.keyName = keyName;
        this.publicKey = publicKey;
        privateKey = null;
        secretKey = null;
    }

    /**
     * Constructor.
     * @param keyName
     * @param privateKey
     */
    public SingleKeyResolver(String keyName, PrivateKey privateKey) {
        this.keyName = keyName;
        this.privateKey = privateKey;
        publicKey = null;
        secretKey = null;
    }

    /**
     * Constructor.
     * @param keyName
     * @param secretKey
     */
    public SingleKeyResolver(String keyName, SecretKey secretKey) {
        this.keyName = keyName;
        this.secretKey = secretKey;
        publicKey = null;
        privateKey = null;
    }

    /** {@inheritDoc} */
    @Override
    protected boolean engineCanResolve(Element element, String baseURI, StorageResolver storage) {
        return XMLUtils.elementIsInSignatureSpace(element, Constants._TAG_KEYNAME);
    }

    /** {@inheritDoc} */
    @Override
    protected PublicKey engineResolvePublicKey(
        Element element, String baseURI, StorageResolver storage, boolean secureValidation
    ) throws KeyResolverException {
        if (publicKey != null) {
            String name = element.getFirstChild().getNodeValue();
            if (keyName.equals(name)) {
                return publicKey;
            }
        }

        return null;
    }

    /** {@inheritDoc} */
    @Override
    protected X509Certificate engineResolveX509Certificate(
        Element element, String baseURI, StorageResolver storage, boolean secureValidation
    ) throws KeyResolverException {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    protected SecretKey engineResolveSecretKey(
        Element element, String baseURI, StorageResolver storage, boolean secureValidation
    ) throws KeyResolverException {
        if (secretKey != null) {
            String name = element.getFirstChild().getNodeValue();
            if (keyName.equals(name)) {
                return secretKey;
            }
        }

        return null;
    }

    /** {@inheritDoc} */
    @Override
    public PrivateKey engineResolvePrivateKey(
        Element element, String baseURI, StorageResolver storage, boolean secureValidation
    ) throws KeyResolverException {

        if (privateKey != null) {
            String name = element.getFirstChild().getNodeValue();
            if (keyName.equals(name)) {
                return privateKey;
            }
        }

        return null;
    }
}
