package sun.security.krb5.internal;

import sun.security.krb5.*;
import sun.security.util.*;
import java.util.ArrayList;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Implements the ASN.1 EncAPRepPart type.
 *
 * <pre>{@code
 * EncAPRepPart ::= [APPLICATION 27] SEQUENCE {
 *      ctime           [0] KerberosTime,
 *      cusec           [1] Microseconds,
 *      subkey          [2] EncryptionKey OPTIONAL,
 *      seq-number      [3] UInt32 OPTIONAL
 * }
 * }</pre>
 *
 * <p>
 * This definition reflects the Network Working Group RFC 4120
 * specification available at
 * <a href="http://www.ietf.org/rfc/rfc4120.txt">
 * http://www.ietf.org/rfc/rfc4120.txt</a>.
 */
public class EncAPRepPart {

    public KerberosTime ctime;
    public int cusec;
    EncryptionKey subKey; //optional
    Integer seqNumber; //optional

    public EncAPRepPart(
            KerberosTime new_ctime,
            int new_cusec,
            EncryptionKey new_subKey,
            Integer new_seqNumber) {
        ctime = new_ctime;
        cusec = new_cusec;
        subKey = new_subKey;
        seqNumber = new_seqNumber;
    }

    public EncAPRepPart(byte[] data)
            throws Asn1Exception, IOException {
        init(new DerValue(data));
    }

    public EncAPRepPart(DerValue encoding)
            throws Asn1Exception, IOException {
        init(encoding);
    }

    /**
     * Initializes an EncaPRepPart object.
     * @param encoding a single DER-encoded value.
     * @exception Asn1Exception if an error occurs while decoding an ASN1 encoded data.
     * @exception IOException if an I/O error occurs while reading encoded data.
     */
    private void init(DerValue encoding) throws Asn1Exception, IOException {
        DerValue der, subDer;
        if (((encoding.getTag() & (byte) 0x1F) != (byte) 0x1B)
                || (encoding.isApplication() != true)
                || (encoding.isConstructed() != true)) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        der = encoding.getData().getDerValue();
        if (der.getTag() != DerValue.tag_Sequence) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        ctime = KerberosTime.parse(der.getData(), (byte) 0x00, true);
        subDer = der.getData().getDerValue();
        if ((subDer.getTag() & (byte) 0x1F) == (byte) 0x01) {
            cusec = subDer.getData().getBigInteger().intValue();
        } else {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        if (der.getData().available() > 0) {
            subKey = EncryptionKey.parse(der.getData(), (byte) 0x02, true);
        } else {
            subKey = null;
            seqNumber = null;
        }
        if (der.getData().available() > 0) {
            subDer = der.getData().getDerValue();
            if ((subDer.getTag() & 0x1F) != 0x03) {
                throw new Asn1Exception(Krb5.ASN1_BAD_ID);
            }
            seqNumber = subDer.getData().getBigInteger().intValue();
        } else {
            seqNumber = null;
        }
        if (der.getData().available() > 0) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
    }

    /**
     * Encodes an EncAPRepPart object.
     * @return byte array of encoded EncAPRepPart object.
     * @exception Asn1Exception if an error occurs while decoding an ASN1 encoded data.
     * @exception IOException if an I/O error occurs while reading encoded data.
     */
    public byte[] asn1Encode() throws Asn1Exception, IOException {
        ArrayList<DerValue> v = new ArrayList<>();
        DerOutputStream temp = new DerOutputStream();
        v.add(new DerValue(DerValue.createTag(DerValue.TAG_CONTEXT,
                true, (byte) 0x00), ctime.asn1Encode()));
        temp.putInteger(BigInteger.valueOf(cusec));
        v.add(new DerValue(DerValue.createTag(DerValue.TAG_CONTEXT,
                true, (byte) 0x01), temp.toByteArray()));
        if (subKey != null) {
            v.add(new DerValue(DerValue.createTag(DerValue.TAG_CONTEXT,
                    true, (byte) 0x02), subKey.asn1Encode()));
        }
        if (seqNumber != null) {
            temp = new DerOutputStream();
            // encode as an unsigned integer (UInt32)
            temp.putInteger(BigInteger.valueOf(seqNumber.longValue()));
            v.add(new DerValue(DerValue.createTag(DerValue.TAG_CONTEXT,
                    true, (byte) 0x03), temp.toByteArray()));
        }
        DerValue[] der = v.toArray(new DerValue[0]);
        temp = new DerOutputStream();
        temp.putSequence(der);
        DerOutputStream out = new DerOutputStream();
        out.write(DerValue.createTag(DerValue.TAG_APPLICATION,
                true, (byte) 0x1B), temp);
        return out.toByteArray();
    }

    public final EncryptionKey getSubKey() {
        return subKey;
    }

    public final Integer getSeqNumber() {
        return seqNumber;
    }
}
