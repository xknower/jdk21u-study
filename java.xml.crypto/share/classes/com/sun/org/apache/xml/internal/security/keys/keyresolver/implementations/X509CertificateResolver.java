package com.sun.org.apache.xml.internal.security.keys.keyresolver.implementations;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import com.sun.org.apache.xml.internal.security.keys.content.x509.XMLX509Certificate;
import com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolverException;
import com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolverSpi;
import com.sun.org.apache.xml.internal.security.keys.storage.StorageResolver;
import com.sun.org.apache.xml.internal.security.utils.Constants;
import com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import org.w3c.dom.Element;

/**
 * Resolves Certificates which are directly contained inside a
 * {@code ds:X509Certificate} Element.
 *
 */
public class X509CertificateResolver extends KeyResolverSpi {

    private static final com.sun.org.slf4j.internal.Logger LOG =
        com.sun.org.slf4j.internal.LoggerFactory.getLogger(X509CertificateResolver.class);

    /** {@inheritDoc} */
    @Override
    protected boolean engineCanResolve(Element element, String baseURI, StorageResolver storage) {
        return Constants.SignatureSpecNS.equals(element.getNamespaceURI());
    }

    /** {@inheritDoc} */
    @Override
    protected PublicKey engineResolvePublicKey(
        Element element, String baseURI, StorageResolver storage, boolean secureValidation
    ) throws KeyResolverException {

        X509Certificate cert =
            this.engineResolveX509Certificate(element, baseURI, storage, secureValidation);

        if (cert != null) {
            return cert.getPublicKey();
        }

        return null;
    }

    /** {@inheritDoc} */
    @Override
    protected X509Certificate engineResolveX509Certificate(
        Element element, String baseURI, StorageResolver storage, boolean secureValidation
    ) throws KeyResolverException {

        try {
            Element[] els =
                XMLUtils.selectDsNodes(element.getFirstChild(), Constants._TAG_X509CERTIFICATE);
            if (els == null || els.length == 0) {
                Element el =
                    XMLUtils.selectDsNode(element.getFirstChild(), Constants._TAG_X509DATA, 0);
                if (el != null) {
                    return engineResolveX509Certificate(el, baseURI, storage, secureValidation);
                }
                return null;
            }

            // populate Object array
            for (int i = 0; i < els.length; i++) {
                XMLX509Certificate xmlCert = new XMLX509Certificate(els[i], baseURI);
                X509Certificate cert = xmlCert.getX509Certificate();
                if (cert != null) {
                    return cert;
                }
            }
            return null;
        } catch (XMLSecurityException ex) {
            LOG.debug("Security Exception", ex);
            throw new KeyResolverException(ex);
        }
    }

    /** {@inheritDoc} */
    @Override
    protected javax.crypto.SecretKey engineResolveSecretKey(
        Element element, String baseURI, StorageResolver storage, boolean secureValidation
    ) {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    protected PrivateKey engineResolvePrivateKey(
        Element element, String baseURI, StorageResolver storage, boolean secureValidation
    ) {
        return null;
    }
}
