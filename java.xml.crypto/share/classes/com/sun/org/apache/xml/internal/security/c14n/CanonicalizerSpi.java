package com.sun.org.apache.xml.internal.security.c14n;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.util.Set;

import com.sun.org.apache.xml.internal.security.parser.XMLParserException;
import com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * Base class which all Canonicalization algorithms extend.
 *
 */
public abstract class CanonicalizerSpi {

    /**
     * Method canonicalize
     *
     * @param inputBytes
     * @param writer OutputStream to write the canonicalization result
     * @param secureValidation Whether secure validation is enabled
     *
     * @throws XMLParserException
     * @throws java.io.IOException
     * @throws javax.xml.parsers.ParserConfigurationException
     */
    public void engineCanonicalize(byte[] inputBytes, OutputStream writer, boolean secureValidation)
        throws XMLParserException, java.io.IOException, CanonicalizationException {

        Document document = null;
        try (java.io.InputStream bais = new ByteArrayInputStream(inputBytes)) {
            document = XMLUtils.read(bais, secureValidation);
        }
        this.engineCanonicalizeSubTree(document, writer);
    }

    /**
     * Returns the URI of this engine.
     * @return the URI
     */
    public abstract String engineGetURI();

    /**
     * C14n a nodeset
     *
     * @param xpathNodeSet
     * @param writer OutputStream to write the canonicalization result
     * @throws CanonicalizationException
     */
    public abstract void engineCanonicalizeXPathNodeSet(Set<Node> xpathNodeSet, OutputStream writer)
        throws CanonicalizationException;

    /**
     * C14n a nodeset
     *
     * @param xpathNodeSet
     * @param inclusiveNamespaces
     * @param writer OutputStream to write the canonicalization result
     * @throws CanonicalizationException
     */
    public abstract void engineCanonicalizeXPathNodeSet(
        Set<Node> xpathNodeSet, String inclusiveNamespaces, OutputStream writer
    ) throws CanonicalizationException;

    /**
     * C14n a node tree.
     *
     * @param rootNode
     * @param writer OutputStream to write the canonicalization result
     * @throws CanonicalizationException
     */
    public abstract void engineCanonicalizeSubTree(Node rootNode, OutputStream writer)
        throws CanonicalizationException;

    /**
     * C14n a node tree.
     *
     * @param rootNode
     * @param inclusiveNamespaces
     * @param writer OutputStream to write the canonicalization result
     * @throws CanonicalizationException
     */
    public abstract void engineCanonicalizeSubTree(Node rootNode, String inclusiveNamespaces, OutputStream writer)
        throws CanonicalizationException;

    /**
     * C14n a node tree.
     *
     * @param rootNode
     * @param inclusiveNamespaces
     * @param propagateDefaultNamespace If true the default namespace will be propagated to the c14n-ized root element
     * @param writer OutputStream to write the canonicalization result
     * @throws CanonicalizationException
     */
    public abstract void engineCanonicalizeSubTree(
            Node rootNode, String inclusiveNamespaces, boolean propagateDefaultNamespace, OutputStream writer)
            throws CanonicalizationException;


}
