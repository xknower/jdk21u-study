package sun.security.krb5.internal;

import sun.security.krb5.*;
import sun.security.util.*;
import java.util.ArrayList;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Implements the ASN.1 KDC-REQ-BODY type.
 *
 * <pre>{@code
 * KDC-REQ-BODY ::= SEQUENCE {
 *      kdc-options             [0] KDCOptions,
 *      cname                   [1] PrincipalName OPTIONAL
 *                                    -- Used only in AS-REQ --,
 *      realm                   [2] Realm
 *                                    -- Server's realm
 *                                    -- Also client's in AS-REQ --,
 *      sname                   [3] PrincipalName OPTIONAL,
 *      from                    [4] KerberosTime OPTIONAL,
 *      till                    [5] KerberosTime,
 *      rtime                   [6] KerberosTime OPTIONAL,
 *      nonce                   [7] UInt32,
 *      etype                   [8] SEQUENCE OF Int32 -- EncryptionType
 *                                    -- in preference order --,
 *      addresses               [9] HostAddresses OPTIONAL,
 *      enc-authorization-data  [10] EncryptedData OPTIONAL
 *                                    -- AuthorizationData --,
 *      additional-tickets      [11] SEQUENCE OF Ticket OPTIONAL
 *                                       -- NOTE: not empty
 * }
 * }</pre>
 *
 * <p>
 * This definition reflects the Network Working Group RFC 4120
 * specification available at
 * <a href="http://www.ietf.org/rfc/rfc4120.txt">
 * http://www.ietf.org/rfc/rfc4120.txt</a>.
 */

public class KDCReqBody {
    public KDCOptions kdcOptions;
    public PrincipalName cname; //optional in ASReq only
    public PrincipalName sname; //optional
    public KerberosTime from; //optional
    public KerberosTime till;
    public KerberosTime rtime; //optional
    public HostAddresses addresses; //optional

    private int nonce;
    private int[] eType = null; //a sequence; not optional
    private EncryptedData encAuthorizationData; //optional
    private Ticket[] additionalTickets; //optional

    public KDCReqBody(
            KDCOptions new_kdcOptions,
            PrincipalName new_cname, //optional in ASReq only
            PrincipalName new_sname, //optional
            KerberosTime new_from, //optional
            KerberosTime new_till,
            KerberosTime new_rtime, //optional
            int new_nonce,
            int[] new_eType, //a sequence; not optional
            HostAddresses new_addresses, //optional
            EncryptedData new_encAuthorizationData, //optional
            Ticket[] new_additionalTickets //optional
            ) throws IOException {
        kdcOptions = new_kdcOptions;
        cname = new_cname;
        sname = new_sname;
        from = new_from;
        till = new_till;
        rtime = new_rtime;
        nonce = new_nonce;
        if (new_eType != null) {
            eType = new_eType.clone();
        }
        addresses = new_addresses;
        encAuthorizationData = new_encAuthorizationData;
        if (new_additionalTickets != null) {
            additionalTickets = new Ticket[new_additionalTickets.length];
            for (int i = 0; i < new_additionalTickets.length; i++) {
                if (new_additionalTickets[i] == null) {
                    throw new IOException("Cannot create a KDCReqBody");
                } else {
                    additionalTickets[i] = (Ticket)new_additionalTickets[i].clone();
                }
            }
        }
    }

    /**
     * Constructs a KDCReqBody object.
     * @param encoding a DER-encoded data.
     * @param msgType an int indicating whether it's KRB_AS_REQ or KRB_TGS_REQ type.
     * @exception Asn1Exception if an error occurs while decoding an ASN1 encoded data.
     * @exception IOException if an I/O error occurs while reading encoded data.
     * @exception RealmException if an error occurs while constructing a Realm object from the encoded data.
     *
     */
    public KDCReqBody(DerValue encoding, int msgType)
            throws Asn1Exception, RealmException, KrbException, IOException {
        DerValue der, subDer;
        addresses = null;
        encAuthorizationData = null;
        additionalTickets = null;
        if (encoding.getTag() != DerValue.tag_Sequence) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        kdcOptions = KDCOptions.parse(encoding.getData(), (byte)0x00, false);

        // cname only appears in AS-REQ and it shares the realm field with
        // sname. This is the only place where realm comes after the name.
        // We first give cname a fake realm and reassign it the correct
        // realm after the realm field is read.
        cname = PrincipalName.parse(encoding.getData(), (byte)0x01, true,
                new Realm("PLACEHOLDER"));
        if ((msgType != Krb5.KRB_AS_REQ) && (cname != null)) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        Realm realm = Realm.parse(encoding.getData(), (byte)0x02, false);
        if (cname != null) {
            cname = new PrincipalName(
                    cname.getNameType(), cname.getNameStrings(), realm);
        }
        sname = PrincipalName.parse(encoding.getData(), (byte)0x03, true, realm);
        from = KerberosTime.parse(encoding.getData(), (byte)0x04, true);
        till = KerberosTime.parse(encoding.getData(), (byte)0x05, false);
        rtime = KerberosTime.parse(encoding.getData(), (byte)0x06, true);
        der = encoding.getData().getDerValue();
        if ((der.getTag() & (byte)0x1F) == (byte)0x07) {
            nonce = der.getData().getBigInteger().intValue();
        } else {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        der = encoding.getData().getDerValue();
        if ((der.getTag() & (byte)0x1F) == (byte)0x08) {
            subDer = der.getData().getDerValue();

            if (subDer.getTag() == DerValue.tag_SequenceOf) {
                ArrayList<Integer> v = new ArrayList<>();
                while(subDer.getData().available() > 0) {
                    v.add(subDer.getData().getBigInteger().intValue());
                }
                eType = new int[v.size()];
                for (int i = 0; i < v.size(); i++) {
                    eType[i] = v.get(i);
                }
            } else {
                throw new Asn1Exception(Krb5.ASN1_BAD_ID);
            }
        } else {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        if (encoding.getData().available() > 0) {
            addresses = HostAddresses.parse(encoding.getData(), (byte)0x09, true);
        }
        if (encoding.getData().available() > 0) {
            encAuthorizationData = EncryptedData.parse(encoding.getData(), (byte)0x0A, true);
        }
        if (encoding.getData().available() > 0) {
            der = encoding.getData().getDerValue();
            if ((der.getTag() & (byte)0x1F) == (byte)0x0B) {
                ArrayList<Ticket> tempTickets = new ArrayList<>();
                subDer = der.getData().getDerValue();
                if (subDer.getTag() == DerValue.tag_SequenceOf) {
                    while (subDer.getData().available() > 0) {
                        tempTickets.add(new Ticket(subDer.getData().getDerValue()));
                    }
                } else {
                    throw new Asn1Exception(Krb5.ASN1_BAD_ID);
                }
                if (tempTickets.size() > 0) {
                    additionalTickets = tempTickets.toArray(new Ticket[0]);
                }
            } else {
                throw new Asn1Exception(Krb5.ASN1_BAD_ID);
            }
        }
        if (encoding.getData().available() > 0) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
    }

    /**
     * Encodes this object to an OutputStream.
     *
     * @return an byte array of encoded data.
     * @exception Asn1Exception if an error occurs while decoding an ASN1 encoded data.
     * @exception IOException if an I/O error occurs while reading encoded data.
     *
     */
    public byte[] asn1Encode(int msgType) throws Asn1Exception, IOException {
        ArrayList<DerValue> v = new ArrayList<>();
        v.add(new DerValue(DerValue.createTag(DerValue.TAG_CONTEXT, true, (byte)0x00), kdcOptions.asn1Encode()));
        if (msgType == Krb5.KRB_AS_REQ) {
            if (cname != null) {
                v.add(new DerValue(DerValue.createTag(DerValue.TAG_CONTEXT, true, (byte)0x01), cname.asn1Encode()));
            }
        }
        if (sname != null) {
            v.add(new DerValue(DerValue.createTag(DerValue.TAG_CONTEXT, true, (byte)0x02), sname.getRealm().asn1Encode()));
            v.add(new DerValue(DerValue.createTag(DerValue.TAG_CONTEXT, true, (byte)0x03), sname.asn1Encode()));
        } else if (cname != null) {
            v.add(new DerValue(DerValue.createTag(DerValue.TAG_CONTEXT, true, (byte)0x02), cname.getRealm().asn1Encode()));
        }
        if (from != null) {
            v.add(new DerValue(DerValue.createTag(DerValue.TAG_CONTEXT, true, (byte)0x04), from.asn1Encode()));
        }
        v.add(new DerValue(DerValue.createTag(DerValue.TAG_CONTEXT, true, (byte)0x05), till.asn1Encode()));
        if (rtime != null) {
            v.add(new DerValue(DerValue.createTag(DerValue.TAG_CONTEXT, true, (byte)0x06), rtime.asn1Encode()));
        }
        DerOutputStream temp = new DerOutputStream();
        temp.putInteger(BigInteger.valueOf(nonce));
        v.add(new DerValue(DerValue.createTag(DerValue.TAG_CONTEXT, true, (byte)0x07), temp.toByteArray()));
        //revisit, if empty eType sequences are allowed
        temp = new DerOutputStream();
        for (int i = 0; i < eType.length; i++) {
            temp.putInteger(BigInteger.valueOf(eType[i]));
        }
        DerOutputStream eTypetemp = new DerOutputStream();
        eTypetemp.write(DerValue.tag_SequenceOf, temp);
        v.add(new DerValue(DerValue.createTag(DerValue.TAG_CONTEXT, true, (byte)0x08), eTypetemp.toByteArray()));
        if (addresses != null) {
            v.add(new DerValue(DerValue.createTag(DerValue.TAG_CONTEXT, true, (byte)0x09), addresses.asn1Encode()));
        }
        if (encAuthorizationData != null) {
            v.add(new DerValue(DerValue.createTag(DerValue.TAG_CONTEXT, true, (byte)0x0A), encAuthorizationData.asn1Encode()));
        }
        if (additionalTickets != null && additionalTickets.length > 0) {
            temp = new DerOutputStream();
            for (int i = 0; i < additionalTickets.length; i++) {
                temp.write(additionalTickets[i].asn1Encode());
            }
            DerOutputStream ticketsTemp = new DerOutputStream();
            ticketsTemp.write(DerValue.tag_SequenceOf, temp);
            v.add(new DerValue(DerValue.createTag(DerValue.TAG_CONTEXT, true, (byte)0x0B), ticketsTemp.toByteArray()));
        }
        DerValue[] der = v.toArray(new DerValue[0]);
        temp = new DerOutputStream();
        temp.putSequence(der);
        return temp.toByteArray();
    }

    public int getNonce() {
        return nonce;
    }
}
