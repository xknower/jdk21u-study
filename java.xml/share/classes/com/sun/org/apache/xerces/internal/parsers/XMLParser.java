package com.sun.org.apache.xerces.internal.parsers;

import java.io.IOException;

import com.sun.org.apache.xerces.internal.impl.Constants;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityPropertyManager;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration;
import jdk.xml.internal.JdkConstants;

import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;

/**
 * Base class of all XML-related parsers.
 * <p>
 * In addition to the features and properties recognized by the parser
 * configuration, this parser recognizes these additional features and
 * properties:
 * <ul>
 * <li>Properties
 *  <ul>
 *   <li>http://apache.org/xml/properties/internal/error-handler</li>
 *   <li>http://apache.org/xml/properties/internal/entity-resolver</li>
 *  </ul>
 * </ul>
 *
 * @author Arnaud  Le Hors, IBM
 * @author Andy Clark, IBM
 * @LastModified: May 2021
 */
public abstract class XMLParser {

    //
    // Constants
    //

    // properties

    /** Property identifier: entity resolver. */
    protected static final String ENTITY_RESOLVER =
        Constants.XERCES_PROPERTY_PREFIX + Constants.ENTITY_RESOLVER_PROPERTY;

    /** Property identifier: error handler. */
    protected static final String ERROR_HANDLER =
        Constants.XERCES_PROPERTY_PREFIX + Constants.ERROR_HANDLER_PROPERTY;

    /** Recognized properties. */
    private static final String[] RECOGNIZED_PROPERTIES = {
        ENTITY_RESOLVER,
        ERROR_HANDLER,
    };

    //
    // Data
    //

    /** The parser configuration. */
    protected XMLParserConfiguration fConfiguration;

    /** The XML Security Manager. */
    XMLSecurityManager securityManager;

    /** The XML Security Property Manager. */
    XMLSecurityPropertyManager securityPropertyManager;


    //
    // Constructors
    //

    /**
     * Query the state of a feature.
     */
    public boolean getFeature(String featureId)
            throws SAXNotSupportedException, SAXNotRecognizedException {
        return fConfiguration.getFeature(featureId);

    }

    /**
     * Default Constructor.
     */
    protected XMLParser(XMLParserConfiguration config) {

        // save configuration
        fConfiguration = config;

        // add default recognized properties
        fConfiguration.addRecognizedProperties(RECOGNIZED_PROPERTIES);

    } // <init>(XMLParserConfiguration)

    //
    // Public methods
    //

    /**
     * parse
     *
     * @param inputSource
     *
     * @exception XNIException
     * @exception java.io.IOException
     */
    public void parse(XMLInputSource inputSource)
        throws XNIException, IOException {
        // null indicates that the parser is called directly, initialize them
        if (securityManager == null) {
            securityManager = new XMLSecurityManager(true);
            fConfiguration.setProperty(Constants.SECURITY_MANAGER, securityManager);
        }
        if (securityPropertyManager == null) {
            securityPropertyManager = new XMLSecurityPropertyManager();
            fConfiguration.setProperty(JdkConstants.XML_SECURITY_PROPERTY_MANAGER, securityPropertyManager);
        }

        reset();
        fConfiguration.parse(inputSource);

    } // parse(XMLInputSource)

    //
    // Protected methods
    //

    /**
     * reset all components before parsing
     */
    protected void reset() throws XNIException {
    } // reset()

} // class XMLParser
