package com.sun.org.apache.xml.internal.security.keys.content;

import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import com.sun.org.apache.xml.internal.security.utils.Constants;
import com.sun.org.apache.xml.internal.security.utils.SignatureElementProxy;
import org.w3c.dom.Element;

/**
 * $todo$ implement
 */
public class SPKIData extends SignatureElementProxy implements KeyInfoContent {

    /**
     * Constructor SPKIData
     *
     * @param element
     * @param baseURI
     * @throws XMLSecurityException
     */
    public SPKIData(Element element, String baseURI)
        throws XMLSecurityException {
        super(element, baseURI);
    }

    /** {@inheritDoc} */
    public String getBaseLocalName() {
        return Constants._TAG_SPKIDATA;
    }
}
