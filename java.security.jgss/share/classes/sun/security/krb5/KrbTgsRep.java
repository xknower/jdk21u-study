package sun.security.krb5;

import sun.security.krb5.internal.*;
import sun.security.krb5.internal.crypto.KeyUsage;
import sun.security.util.*;
import java.io.IOException;

/**
 * This class encapsulates a TGS-REP that is sent from the KDC to the
 * Kerberos client.
 */
final class KrbTgsRep extends KrbKdcRep {
    private TGSRep rep;
    private Credentials creds;
    private Credentials additionalCreds;

    KrbTgsRep(byte[] ibuf, KrbTgsReq tgsReq)
        throws KrbException, IOException {
        DerValue ref = new DerValue(ibuf);
        TGSReq req = tgsReq.getMessage();
        TGSRep rep = null;
        try {
            rep = new TGSRep(ref);
        } catch (Asn1Exception e) {
            rep = null;
            KRBError err = new KRBError(ref);
            String errStr = err.getErrorString();
            String eText = null; // pick up text sent by the server (if any)
            if (errStr != null && errStr.length() > 0) {
                if (errStr.charAt(errStr.length() - 1) == 0)
                    eText = errStr.substring(0, errStr.length() - 1);
                else
                    eText = errStr;
            }
            KrbException ke;
            if (eText == null) {
                // no text sent from server
                ke = new KrbException(err.getErrorCode());
            } else {
                // override default text with server text
                ke = new KrbException(err.getErrorCode(), eText);
            }
            ke.initCause(e);
            throw ke;
        }
        byte[] enc_tgs_rep_bytes = rep.encPart.decrypt(tgsReq.tgsReqKey,
            tgsReq.usedSubkey() ? KeyUsage.KU_ENC_TGS_REP_PART_SUBKEY :
            KeyUsage.KU_ENC_TGS_REP_PART_SESSKEY);

        byte[] enc_tgs_rep_part = rep.encPart.reset(enc_tgs_rep_bytes);
        ref = new DerValue(enc_tgs_rep_part);
        EncTGSRepPart enc_part = new EncTGSRepPart(ref);
        rep.encKDCRepPart = enc_part;

        check(false, req, rep, tgsReq.tgsReqKey);

        PrincipalName serverAlias = tgsReq.getServerAlias();
        if (serverAlias != null) {
            PrincipalName repSname = enc_part.sname;
            if (serverAlias.equals(repSname) ||
                    isReferralSname(repSname)) {
                serverAlias = null;
            }
        }

        PrincipalName clientAlias = null;
        if (rep.cname.equals(req.reqBody.cname)) {
            // Only propagate the client alias if it is not an
            // impersonation ticket (S4U2Self or S4U2Proxy).
            clientAlias = tgsReq.getClientAlias();
        }

        this.creds = new Credentials(rep.ticket,
                                rep.cname,
                                clientAlias,
                                enc_part.sname,
                                serverAlias,
                                enc_part.key,
                                enc_part.flags,
                                enc_part.authtime,
                                enc_part.starttime,
                                enc_part.endtime,
                                enc_part.renewTill,
                                enc_part.caddr
                                );
        this.rep = rep;
        this.additionalCreds = tgsReq.getAdditionalCreds();
    }

    /**
     * Return the credentials that were contained in this KRB-TGS-REP.
     */
    Credentials getCreds() {
        return creds;
    }

    sun.security.krb5.internal.ccache.Credentials setCredentials() {
        return new sun.security.krb5.internal.ccache.Credentials(
                rep, additionalCreds == null ? null : additionalCreds.ticket);
    }

    private static boolean isReferralSname(PrincipalName sname) {
        if (sname != null) {
            String[] snameStrings = sname.getNameStrings();
            if (snameStrings.length == 2 &&
                    snameStrings[0].equals(
                            PrincipalName.TGS_DEFAULT_SRV_NAME)) {
                return true;
            }
        }
        return false;
    }
}
