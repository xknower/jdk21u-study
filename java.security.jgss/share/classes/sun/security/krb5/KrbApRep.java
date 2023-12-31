package sun.security.krb5;

import sun.security.krb5.internal.*;
import sun.security.krb5.internal.crypto.KeyUsage;
import sun.security.util.*;
import java.io.IOException;

/**
 * This class encapsulates a KRB-AP-REP sent from the service to the
 * client.
 */
public class KrbApRep {
    private byte[] obuf;
    private byte[] ibuf;
    private EncAPRepPart encPart; // although in plain text
    private APRep apRepMessg;

    /**
     * Constructs a KRB-AP-REP to send to a client.
     * @throws KrbException
     * @throws IOException
     */
     // Used in AcceptSecContextToken
    public KrbApRep(KrbApReq incomingReq,
                     boolean useSeqNumber,
                     EncryptionKey subKey)
            throws KrbException, IOException {

        SeqNumber seqNum = new LocalSeqNumber();

        init(incomingReq, subKey, seqNum);
    }

    /**
     * Constructs a KRB-AP-REQ from the bytes received from a service.
     * @throws KrbException
     * @throws IOException
     */
     // Used in AcceptSecContextToken
    public KrbApRep(byte[] message, Credentials tgtCreds,
                    KrbApReq outgoingReq) throws KrbException, IOException {
        this(message, tgtCreds);
        authenticate(outgoingReq);
    }

    private void init(KrbApReq apReq,
              EncryptionKey subKey,
        SeqNumber seqNumber)
        throws KrbException, IOException {
        createMessage(
                      apReq.getCreds().key,
                      apReq.getCtime(),
                      apReq.cusec(),
                      subKey,
                      seqNumber);
        obuf = apRepMessg.asn1Encode();
    }


    /**
     * Constructs a KrbApRep object.
     * @param msg a byte array of reply message.
     * @param tgs_creds client's credential.
     * @exception KrbException
     * @exception IOException
     */
    private KrbApRep(byte[] msg, Credentials tgs_creds)
        throws KrbException, IOException {
        this(new DerValue(msg), tgs_creds);
    }

    /**
     * Constructs a KrbApRep object.
     * @param msg a byte array of reply message.
     * @param tgs_creds client's credential.
     * @exception KrbException
     * @exception IOException
     */
    private KrbApRep(DerValue encoding, Credentials tgs_creds)
        throws KrbException, IOException {
        APRep rep = null;
        try {
            rep = new APRep(encoding);
        } catch (Asn1Exception e) {
            rep = null;
            KRBError err = new KRBError(encoding);
            String errStr = err.getErrorString();
            String eText;
            if (errStr.charAt(errStr.length() - 1) == 0)
                eText = errStr.substring(0, errStr.length() - 1);
            else
                eText = errStr;
            KrbException ke = new KrbException(err.getErrorCode(), eText);
            ke.initCause(e);
            throw ke;
        }

        byte[] temp = rep.encPart.decrypt(tgs_creds.key,
            KeyUsage.KU_ENC_AP_REP_PART);
        byte[] enc_ap_rep_part = rep.encPart.reset(temp);

        encoding = new DerValue(enc_ap_rep_part);
        encPart = new EncAPRepPart(encoding);
    }

    private void authenticate(KrbApReq apReq)
        throws KrbException, IOException {
        if (encPart.ctime.getSeconds() != apReq.getCtime().getSeconds() ||
            encPart.cusec != apReq.getCtime().getMicroSeconds())
            throw new KrbApErrException(Krb5.KRB_AP_ERR_MUT_FAIL);
    }


    /**
     * Returns the optional subkey stored in
     * this message. Returns null if none is stored.
     */
    public EncryptionKey getSubKey() {
        // XXX Can encPart be null
        return encPart.getSubKey();

    }

    /**
     * Returns the optional sequence number stored in the
     * this message. Returns null if none is stored.
     */
    public Integer getSeqNumber() {
        // XXX Can encPart be null
        return encPart.getSeqNumber();
    }

    /**
     * Returns the ASN.1 encoding that should be sent to the peer.
     */
    public byte[] getMessage() {
        return obuf;
    }

    private void createMessage(
                               EncryptionKey key,
                               KerberosTime ctime,
                               int cusec,
                               EncryptionKey subKey,
                               SeqNumber seqNumber)
        throws Asn1Exception, IOException,
               KdcErrException, KrbCryptoException {

        Integer seqno = null;

        if (seqNumber != null)
            seqno = seqNumber.current();

        encPart = new EncAPRepPart(ctime,
                                   cusec,
                                   subKey,
                                   seqno);

        byte[] encPartEncoding = encPart.asn1Encode();

        EncryptedData encEncPart = new EncryptedData(key, encPartEncoding,
            KeyUsage.KU_ENC_AP_REP_PART);

        apRepMessg = new APRep(encEncPart);
    }

}
