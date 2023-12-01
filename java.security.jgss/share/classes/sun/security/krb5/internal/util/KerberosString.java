package sun.security.krb5.internal.util;

import java.io.IOException;
import sun.security.action.GetPropertyAction;
import sun.security.util.DerValue;

import static java.nio.charset.StandardCharsets.US_ASCII;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Implements the ASN.1 KerberosString type.
 *
 * <pre>
 * KerberosString  ::= GeneralString (IA5String)
 * </pre>
 *
 * This definition reflects the Network Working Group RFC 4120
 * specification available at
 * <a href="http://www.ietf.org/rfc/rfc4120.txt">
 * http://www.ietf.org/rfc/rfc4120.txt</a>.
 */
public final class KerberosString {
    /**
     * RFC 4120 defines KerberosString as GeneralString (IA5String), which
     * only includes ASCII characters. However, most implementations have been
     * known to use GeneralString to contain UTF-8 encoding. The following
     * system property is defined. When set as true, KerberosString is encoded
     * as UTF-8. Otherwise, it's ASCII. The default is true.
     *
     * Note that this only affects the byte encoding, the tag of the ASN.1
     * type is still GeneralString.
     */
    public static final boolean MSNAME;

    static {
        String prop = GetPropertyAction
                .privilegedGetProperty("sun.security.krb5.msinterop.kstring", "true");
        MSNAME = Boolean.parseBoolean(prop);
    }

    private final String s;

    public KerberosString(String s) {
        this.s = s;
    }

    public KerberosString(DerValue der) throws IOException {
        if (der.tag != DerValue.tag_GeneralString) {
            throw new IOException(
                "KerberosString's tag is incorrect: " + der.tag);
        }
        s = new String(der.getDataBytes(), MSNAME ? UTF_8 : US_ASCII);
    }

    public String toString() {
        return s;
    }

    public DerValue toDerValue() {
        // No need to cache the result since this method is
        // only called once.
        return new DerValue(DerValue.tag_GeneralString,
                s.getBytes(MSNAME ? UTF_8 : US_ASCII));
    }
}
