package javax.xml.catalog;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import static javax.xml.catalog.CatalogMessages.ERR_INVALID_ARGUMENT;

/**
 * Represents a general Catalog entry.
 *
 * @since 9
 */
abstract class BaseEntry {
    final String SLASH = "/";

    CatalogEntryType type;

    //The id attribute
    String id;

    //The attribute to be matched, e.g. systemId
    String matchId;

    //The baseURI attribute
    URL baseURI;

    //Indicates whether the base attribute is specified
    boolean baseSpecified = false;

    /**
     * CatalogEntryType represents catalog entry types.
     */
    static enum CatalogEntryType {

        CATALOG("catalogfile"),
        CATALOGENTRY("catalog"),
        GROUP("group"),
        PUBLIC("public"),
        SYSTEM("system"),
        REWRITESYSTEM("rewriteSystem"),
        SYSTEMSUFFIX("systemSuffix"),
        DELEGATEPUBLIC("delegatePublic"),
        DELEGATESYSTEM("delegateSystem"),
        URI("uri"),
        REWRITEURI("rewriteURI"),
        URISUFFIX("uriSuffix"),
        DELEGATEURI("delegateURI"),
        NEXTCATALOG("nextCatalog");

        final String literal;

        CatalogEntryType(String literal) {
            this.literal = literal;
        }

        public boolean isType(String type) {
            return literal.equals(type);
        }

        static public CatalogEntryType getType(String entryType) {
            for (CatalogEntryType type : CatalogEntryType.values()) {
                if (type.isType(entryType)) {
                    return type;
                }
            }
            return null;
        }
    }

    /**
     * Constructs a CatalogEntry
     *
     * @param type The type of the entry
     */
    public BaseEntry(CatalogEntryType type) {
        this.type = Objects.requireNonNull(type);
    }

    /**
     * Constructs a CatalogEntry
     *
     * @param type The type of the entry
     * @param base The base URI
     */
    public BaseEntry(CatalogEntryType type, String base) {
        this.type = Objects.requireNonNull(type);
        setBaseURI(base);
    }

    /**
     * Returns the type of the entry
     *
     * @return The type of the entry
     */
    public CatalogEntryType getType() {
        return type;
    }

    /**
     * Sets the entry type
     *
     * @param type The entry type
     */
    public void setType(CatalogEntryType type) {
        this.type = type;
    }

    /**
     * Returns the id of the entry
     *
     * @return The id of the entry
     */
    public String getId() {
        return id;
    }

    /**
     * Set the entry Id
     *
     * @param id The Id attribute
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets the base URI for the entry
     *
     * @param base The base URI
     */
    public final void setBaseURI(String base) {
        baseURI = verifyURI("base", null, base);
    }

    /**
     * Gets the base URI for the entry
     *
     * @return The base URI as a string.
     */
    public URL getBaseURI() {
        return baseURI;
    }

    /**
     * Gets the attribute used for matching
     *
     * @return The value of the field
     */
    public String getMatchId() {
        return matchId;
    }

    /**
     * Sets the matchId field
     * @param matchId The value of the Id
     */
    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    /**
     * Matches the specified string with the identifier attribute of the entry.
     *
     * @param match The identifier attribute to be matched
     * @return The replacement URI if a matching entry is found, null if not.
     */
    public String match(String match) {
        return null;
    }

    /**
     * Try to match the specified id with the entry. Return the match if it
     * is successful and the length of the start String is longer than the
     * longest of any previous match.
     *
     * @param id The id to be matched.
     * @param currentMatch The length of start String of previous match if any.
     * @return The replacement URI if the match is successful, null if not.
     */
    public String match(String id, int currentMatch) {
        return null;
    }

    /**
     * Verifies the specified URI.
     *
     * @param arg The name of the argument
     * @param uri The URI to be verified
     * @return The URI created from the specified uri
     * @throws NullPointerException if the specified uri is null
     * @throws IllegalArgumentException if a URL can not be created based on
     * the specified base and uri
     */
    URL verifyURI(String arg, URL base, String uri) {
        CatalogMessages.reportNPEOnNull(arg, uri);

        URL url = null;
        uri = Normalizer.normalizeURI(uri);

        try {
            if (base != null) {
                @SuppressWarnings("deprecation")
                URL _unused = url = new URL(base, uri);
            } else {
                @SuppressWarnings("deprecation")
                URL _unused = url = new URL(uri);
            }
        } catch (MalformedURLException e) {
            CatalogMessages.reportIAE(ERR_INVALID_ARGUMENT,
                    new Object[]{uri, arg}, e);
        }
        return url;
    }
}
