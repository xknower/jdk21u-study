package sun.security.krb5.internal;

import sun.security.util.*;
import sun.security.krb5.Asn1Exception;
import java.io.IOException;
import sun.security.krb5.internal.util.KerberosString;

/**
 * Implements the ASN.1 ETYPE-INFO-ENTRY type.
 *
 * ETYPE-INFO2-ENTRY    ::= SEQUENCE {
 *          etype       [0] Int32,
 *          salt        [1] KerberosString OPTIONAL,
 *          s2kparams   [2] OCTET STRING OPTIONAL
 * }
 *
 * @author Seema Malkani
 */

public class ETypeInfo2 {

    private int etype;
    private String saltStr = null;
    private byte[] s2kparams = null;

    private static final byte TAG_TYPE = 0;
    private static final byte TAG_VALUE1 = 1;
    private static final byte TAG_VALUE2 = 2;

    private ETypeInfo2() {
    }

    public ETypeInfo2(int etype, String salt, byte[] s2kparams) {
        this.etype = etype;
        this.saltStr = salt;
        if (s2kparams != null) {
            this.s2kparams = s2kparams.clone();
        }
    }

    public Object clone() {
        ETypeInfo2 etypeInfo2 = new ETypeInfo2();
        etypeInfo2.etype = etype;
        etypeInfo2.saltStr = saltStr;
        if (s2kparams != null) {
            etypeInfo2.s2kparams = new byte[s2kparams.length];
            System.arraycopy(s2kparams, 0, etypeInfo2.s2kparams,
                                0, s2kparams.length);
        }
        return etypeInfo2;
    }

    /**
     * Constructs a ETypeInfo2 object.
     * @param encoding a DER-encoded data.
     * @exception Asn1Exception if an error occurs while decoding an
     *            ASN1 encoded data.
     * @exception IOException if an I/O error occurs while reading encoded data.
     */
    public ETypeInfo2(DerValue encoding) throws Asn1Exception, IOException {
        DerValue der = null;

        if (encoding.getTag() != DerValue.tag_Sequence) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }

        // etype
        der = encoding.getData().getDerValue();
        if ((der.getTag() & 0x1F) == 0x00) {
            this.etype = der.getData().getBigInteger().intValue();
        }
        else
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);

        // salt
        if (encoding.getData().available() > 0) {
            if ((encoding.getData().peekByte() & 0x1F) == 0x01) {
                der = encoding.getData().getDerValue();
                this.saltStr = new KerberosString(
                        der.getData().getDerValue()).toString();
            }
        }

        // s2kparams
        if (encoding.getData().available() > 0) {
            if ((encoding.getData().peekByte() & 0x1F) == 0x02) {
                der = encoding.getData().getDerValue();
                this.s2kparams = der.getData().getOctetString();
            }
        }

        if (encoding.getData().available() > 0)
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
    }

    /**
     * Encodes this object to an OutputStream.
     *
     * @return byte array of the encoded data.
     * @exception IOException if an I/O error occurs while reading encoded data.
     * @exception Asn1Exception on encoding errors.
     */
    public byte[] asn1Encode() throws Asn1Exception, IOException {

        DerOutputStream bytes = new DerOutputStream();
        DerOutputStream temp = new DerOutputStream();

        temp.putInteger(etype);
        bytes.write(DerValue.createTag(DerValue.TAG_CONTEXT, true,
                                        TAG_TYPE), temp);

        if (saltStr != null) {
            temp = new DerOutputStream();
            temp.putDerValue(new KerberosString(saltStr).toDerValue());
            bytes.write(DerValue.createTag(DerValue.TAG_CONTEXT, true,
                                        TAG_VALUE1), temp);
        }
        if (s2kparams != null) {
            temp = new DerOutputStream();
            temp.putOctetString(s2kparams);
            bytes.write(DerValue.createTag(DerValue.TAG_CONTEXT, true,
                                        TAG_VALUE2), temp);
        }

        temp = new DerOutputStream();
        temp.write(DerValue.tag_Sequence, bytes);
        return temp.toByteArray();
    }

    // accessor methods
    public int getEType() {
        return etype;
    }

    public String getSalt() {
        return saltStr;
    }

    public byte[] getParams() {
        return ((s2kparams == null) ? null : s2kparams.clone());
    }

}
