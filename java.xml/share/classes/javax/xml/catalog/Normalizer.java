package javax.xml.catalog;

import java.net.URLDecoder;
import java.net.URLEncoder;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * The Normalizer is responsible for normalizing Public and System Identifiers
 * as specified in section 6.2, 6.3 and 6.4 of the specification
 *  * <a
 * href="https://www.oasis-open.org/committees/download.php/14809/xml-catalogs.html">
 * XML Catalogs, OASIS Standard V1.1, 7 October 2005</a>.
 *
 * @since 9
 */
class Normalizer {

    /**
     * Normalize a public identifier in accordance with section 6.2 of the
     * Catalog specification.
     *
     * <p>
     * All strings of white space in public identifiers must be normalized to
     * single space characters (#x20), and leading and trailing white space must
     * be removed.
     *
     * @param publicId The unnormalized public identifier
     *
     * @return The normalized identifier
     */
    static String normalizePublicId(String publicId) {
        if (publicId == null) return null;

        StringBuilder sb = new StringBuilder(publicId.length());
        char last = 'a';
        for (char c : publicId.toCharArray()) {
            //skip beginning and duplicate space
            if ((c == ' ') && (sb.length() == 0 || last == ' ')) {
                continue;
            }

            //replace whitespace with space
            if (c == '\t' || c == '\r' || c == '\n') {
                if (last != ' ') {
                    sb.append(' ');
                    last = ' ';
                }
            } else {
                sb.append(c);
                last = c;
            }
        }
        //remove the last space
        if (last == ' ') {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    /**
     * Encode a public identifier as a "publicid" URN.
     *
     * @param publicId The unnormalized public identifier
     *
     * @return The normalized identifier
     * @throws CatalogException if encoding failed
     */
    static String encodeURN(String publicId) {
        String urn = normalizePublicId(publicId);

        urn = URLEncoder.encode(urn, UTF_8);
        urn = urn.replace("::", ";");
        urn = urn.replace("//", ":");
        return Util.URN + urn;
    }

    /**
     * Decode a "publicid" URN into a public identifier.
     *
     * @param urn The urn:publicid: URN
     *
     * @return The normalized identifier
     * @throws CatalogException if decoding failed
     */
    static String decodeURN(String urn) {
        String publicId;

        if (urn != null && urn.startsWith(Util.URN)) {
            publicId = urn.substring(13);
        } else {
            return urn;
        }
        publicId = publicId.replace(":", "//");
        publicId = publicId.replace(";", "::");
        publicId = URLDecoder.decode(publicId, UTF_8);

        return publicId;
    }

    /**
     * Perform character normalization on a URI reference.
     *
     * @param uriref The URI reference
     * @return The normalized URI reference
     */
    static String normalizeURI(String uriref) {
        if (uriref == null) {
            return null;
        }

        uriref = uriref.trim();
        byte[] bytes = uriref.getBytes(UTF_8);

        StringBuilder newRef = new StringBuilder(bytes.length);
        for (int count = 0; count < bytes.length; count++) {
            int ch = bytes[count] & 0xFF;

            if ((ch <= 0x20) // ctrl
                    || (ch > 0x7F) // high ascii
                    || (ch == 0x22) // "
                    || (ch == 0x3C) // <
                    || (ch == 0x3E) // >
                    || (ch == 0x5C) // \
                    || (ch == 0x5E) // ^
                    || (ch == 0x60) // `
                    || (ch == 0x7B) // {
                    || (ch == 0x7C) // |
                    || (ch == 0x7D) // }
                    || (ch == 0x7F)) {
                newRef.append("%").append(String.format("%02X", ch));
            } else {
                newRef.append((char) bytes[count]);
            }
        }

        return newRef.toString().trim();
    }
}
