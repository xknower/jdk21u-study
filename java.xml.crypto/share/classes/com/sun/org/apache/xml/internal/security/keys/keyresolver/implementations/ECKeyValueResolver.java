package com.sun.org.apache.xml.internal.security.keys.keyresolver.implementations;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import com.sun.org.apache.xml.internal.security.keys.content.keyvalues.ECKeyValue;
import com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolverSpi;
import com.sun.org.apache.xml.internal.security.keys.storage.StorageResolver;
import com.sun.org.apache.xml.internal.security.utils.Constants;
import com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import org.w3c.dom.Element;

public class ECKeyValueResolver extends KeyResolverSpi {

    private static final com.sun.org.slf4j.internal.Logger LOG =
        com.sun.org.slf4j.internal.LoggerFactory.getLogger(ECKeyValueResolver.class);

    /** {@inheritDoc} */
    @Override
    protected boolean engineCanResolve(Element element, String baseURI, StorageResolver storage) {
        return XMLUtils.elementIsInSignatureSpace(element, Constants._TAG_KEYVALUE)
            || XMLUtils.elementIsInSignatureSpace(element, Constants._TAG_ECKEYVALUE);
    }

    /** {@inheritDoc} */
    @Override
    protected PublicKey engineResolvePublicKey(
        Element element, String baseURI, StorageResolver storage, boolean secureValidation
    ) {
        if (element == null) {
            return null;
        }
        Element ecKeyElement = null;
        boolean isKeyValue =
            XMLUtils.elementIsInSignatureSpace(element, Constants._TAG_KEYVALUE);
        if (isKeyValue) {
            ecKeyElement =
                XMLUtils.selectDs11Node(element.getFirstChild(), Constants._TAG_ECKEYVALUE, 0);
        } else if (XMLUtils.elementIsInSignature11Space(element, Constants._TAG_ECKEYVALUE)) {
            // this trick is needed to allow the RetrievalMethodResolver to eat a
            // ds:ECKeyValue directly (without KeyValue)
            ecKeyElement = element;
        }

        if (ecKeyElement == null) {
            return null;
        }

        try {
            ECKeyValue ecKeyValue = new ECKeyValue(ecKeyElement, baseURI);
            return ecKeyValue.getPublicKey();
        } catch (XMLSecurityException ex) {
            LOG.debug(ex.getMessage(), ex);
            //do nothing
        }

        return null;
    }


    /** {@inheritDoc} */
    @Override
    protected X509Certificate engineResolveX509Certificate(
        Element element, String baseURI, StorageResolver storage, boolean secureValidation
    ) {
        return null;
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
