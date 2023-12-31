package javax.swing.text.html.parser;

/**
 * SGML constants used in a DTD. The names of the
 * constants correspond to the equivalent SGML constructs
 * as described in "The SGML Handbook" by  Charles F. Goldfarb.
 *
 * @see DTD
 * @see Element
 * @author Arthur van Hoff
 */
public
interface DTDConstants {
    // Attribute value types

    /**
     * The DTD constant corresponds to CDATA
     */
    int CDATA           = 1;

    /**
     * The DTD constant corresponds to ENTITY
     */
    int ENTITY          = 2;

    /**
     * The DTD constant corresponds to ENTITIES
     */
    int ENTITIES        = 3;

    /**
     * The DTD constant corresponds to ID
     */
    int ID              = 4;

    /**
     * The DTD constant corresponds to IDREF
     */
    int IDREF           = 5;

    /**
     * The DTD constant corresponds to IDREFS
     */
    int IDREFS          = 6;

    /**
     * The DTD constant corresponds to NAME
     */
    int NAME            = 7;

    /**
     * The DTD constant corresponds to NAMES
     */
    int NAMES           = 8;

    /**
     * The DTD constant corresponds to NMTOKEN
     */
    int NMTOKEN         = 9;

    /**
     * The DTD constant corresponds to NMTOKENS
     */
    int NMTOKENS        = 10;

    /**
     * The DTD constant corresponds to NOTATION
     */
    int NOTATION        = 11;

    /**
     * The DTD constant corresponds to NUMBER
     */
    int NUMBER          = 12;

    /**
     * The DTD constant corresponds to NUMBERS
     */
    int NUMBERS         = 13;

    /**
     * The DTD constant corresponds to NUTOKEN
     */
    int NUTOKEN         = 14;

    /**
     * The DTD constant corresponds to NUTOKENS
     */
    int NUTOKENS        = 15;

    // Content model types

    /**
     * The DTD constant corresponds to RCDATA
     */
    int RCDATA          = 16;

    /**
     * The DTD constant corresponds to EMPTY
     */
    int EMPTY           = 17;

    /**
     * The DTD constant corresponds to MODEL
     */
    int MODEL           = 18;

    /**
     * The DTD constant corresponds to ANY
     */
    int ANY             = 19;

    // Attribute value modifiers

    /**
     * The DTD constant corresponds to FIXED
     */
    int FIXED           = 1;

    /**
     * The DTD constant corresponds to REQUIRED
     */
    int REQUIRED        = 2;

    /**
     * The DTD constant corresponds to CURRENT
     */
    int CURRENT         = 3;

    /**
     * The DTD constant corresponds to CONREF
     */
    int CONREF          = 4;

    /**
     * The DTD constant corresponds to IMPLIED
     */
    int IMPLIED         = 5;

    // Entity types

    /**
     * The DTD constant corresponds to PUBLIC
     */
    int PUBLIC          = 10;

    /**
     * The DTD constant corresponds to SDATA
     */
    int SDATA           = 11;

    /**
     * The DTD constant corresponds to PI
     */
    int PI              = 12;

    /**
     * The DTD constant corresponds to STARTTAG
     */
    int STARTTAG        = 13;

    /**
     * The DTD constant corresponds to ENDTAG
     */
    int ENDTAG          = 14;

    /**
     * The DTD constant corresponds to MS
     */
    int MS              = 15;

    /**
     * The DTD constant corresponds to MD
     */
    int MD              = 16;

    /**
     * The DTD constant corresponds to SYSTEM
     */
    int SYSTEM          = 17;

    /**
     * The DTD constant corresponds to GENERAL
     */

    int GENERAL         = 1<<16;

    /**
     * The DTD constant corresponds to DEFAULT
     */
    int DEFAULT         = 1<<17;

    /**
     * The DTD constant corresponds to PARAMETER
     */
    int PARAMETER       = 1<<18;
}
