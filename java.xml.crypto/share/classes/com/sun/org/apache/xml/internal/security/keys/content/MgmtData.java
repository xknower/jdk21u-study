package com.sun.org.apache.xml.internal.security.keys.content;

import com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;
import com.sun.org.apache.xml.internal.security.utils.Constants;
import com.sun.org.apache.xml.internal.security.utils.SignatureElementProxy;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 */
public class MgmtData extends SignatureElementProxy implements KeyInfoContent {

    /**
     * Constructor MgmtData
     *
     * @param element
     * @param baseURI
     * @throws XMLSecurityException
     */
    public MgmtData(Element element, String baseURI)
        throws XMLSecurityException {
        super(element, baseURI);
    }

    /**
     * Constructor MgmtData
     *
     * @param doc
     * @param mgmtData
     */
    public MgmtData(Document doc, String mgmtData) {
        super(doc);

        this.addText(mgmtData);
    }

    /**
     * Method getMgmtData
     *
     * @return the management data
     */
    public String getMgmtData() {
        return this.getTextFromTextChild();
    }

    /** {@inheritDoc} */
    public String getBaseLocalName() {
        return Constants._TAG_MGMTDATA;
    }
}
