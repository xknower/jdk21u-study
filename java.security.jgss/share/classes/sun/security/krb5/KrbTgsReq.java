package sun.security.krb5;

import sun.security.krb5.internal.*;
import sun.security.krb5.internal.crypto.*;
import java.io.IOException;
import java.net.UnknownHostException;
import java.time.Instant;
import java.util.Arrays;

/**
 * This class encapsulates a Kerberos TGS-REQ that is sent from the
 * client to the KDC.
 */
public class KrbTgsReq extends KrbKdcReq {

    private PrincipalName princName;
    private PrincipalName clientAlias;
    private PrincipalName servName;
    private PrincipalName serverAlias;
    private TGSReq tgsReqMessg;
    private KerberosTime ctime;
    private Credentials additionalCreds = null;
    private boolean useSubkey = false;
    EncryptionKey tgsReqKey;

    // Used in CredentialsUtil
    public KrbTgsReq(KDCOptions options, Credentials asCreds,
            PrincipalName cname, PrincipalName clientAlias,
            PrincipalName sname, PrincipalName serverAlias,
            Credentials additionalCreds, PAData[] extraPAs)
            throws KrbException, IOException {
        this(options,
                asCreds,
                cname,
                clientAlias,
                sname,
                serverAlias,
                null, // KerberosTime from
                null, // KerberosTime till
                null, // KerberosTime rtime
                null, // int[] eTypes
                null, // HostAddresses addresses
                null, // AuthorizationData authorizationData
                additionalCreds,
                null, // EncryptionKey subKey
                extraPAs);
    }

    // Called by Credentials, KrbCred
    KrbTgsReq(
            KDCOptions options,
            Credentials asCreds,
            PrincipalName sname,
            PrincipalName serverAlias,
            KerberosTime from,
            KerberosTime till,
            KerberosTime rtime,
            int[] eTypes,
            HostAddresses addresses,
            AuthorizationData authorizationData,
            Credentials additionalCreds,
            EncryptionKey subKey) throws KrbException, IOException {
        this(options, asCreds, asCreds.getClient(), asCreds.getClientAlias(),
                sname, serverAlias, from, till, rtime, eTypes,
                addresses, authorizationData, additionalCreds, subKey, null);
    }

    private KrbTgsReq(
            KDCOptions options,
            Credentials asCreds,
            PrincipalName cname,
            PrincipalName clientAlias,
            PrincipalName sname,
            PrincipalName serverAlias,
            KerberosTime from,
            KerberosTime till,
            KerberosTime rtime,
            int[] eTypes,
            HostAddresses addresses,
            AuthorizationData authorizationData,
            Credentials additionalCreds,
            EncryptionKey subKey,
            PAData[] extraPAs) throws KrbException, IOException {

        princName = cname;
        this.clientAlias = clientAlias;
        servName = sname;
        this.serverAlias = serverAlias;
        ctime = KerberosTime.now();

        // check if they are valid arguments. The optional fields
        // should be consistent with settings in KDCOptions.

        if (options.get(KDCOptions.FORWARDABLE) &&
                (!(asCreds.flags.get(Krb5.TKT_OPTS_FORWARDABLE)))) {
            options.set(KDCOptions.FORWARDABLE, false);
        }
        if (options.get(KDCOptions.FORWARDED)) {
            if (!(asCreds.flags.get(KDCOptions.FORWARDABLE)))
                throw new KrbException(Krb5.KRB_AP_ERR_REQ_OPTIONS);
        }
        if (options.get(KDCOptions.PROXIABLE) &&
                (!(asCreds.flags.get(Krb5.TKT_OPTS_PROXIABLE)))) {
            throw new KrbException(Krb5.KRB_AP_ERR_REQ_OPTIONS);
        }
        if (options.get(KDCOptions.PROXY)) {
            if (!(asCreds.flags.get(KDCOptions.PROXIABLE)))
                throw new KrbException(Krb5.KRB_AP_ERR_REQ_OPTIONS);
        }
        if (options.get(KDCOptions.ALLOW_POSTDATE) &&
                (!(asCreds.flags.get(Krb5.TKT_OPTS_MAY_POSTDATE)))) {
            throw new KrbException(Krb5.KRB_AP_ERR_REQ_OPTIONS);
        }
        if (options.get(KDCOptions.RENEWABLE) &&
                (!(asCreds.flags.get(Krb5.TKT_OPTS_RENEWABLE)))) {
            throw new KrbException(Krb5.KRB_AP_ERR_REQ_OPTIONS);
        }

        if (options.get(KDCOptions.POSTDATED)) {
            if (!(asCreds.flags.get(KDCOptions.POSTDATED)))
                throw new KrbException(Krb5.KRB_AP_ERR_REQ_OPTIONS);
        } else {
            if (from != null) from = null;
        }
        if (options.get(KDCOptions.RENEWABLE)) {
            if (!(asCreds.flags.get(KDCOptions.RENEWABLE)))
                throw new KrbException(Krb5.KRB_AP_ERR_REQ_OPTIONS);
        } else {
            if (rtime != null) rtime = null;
        }
        if (options.get(KDCOptions.ENC_TKT_IN_SKEY) || options.get(KDCOptions.CNAME_IN_ADDL_TKT)) {
            if (additionalCreds == null)
                throw new KrbException(Krb5.KRB_AP_ERR_REQ_OPTIONS);
            // in TGS_REQ there could be more than one additional
            // tickets,  but in file-based credential cache,
            // there is only one additional ticket field.
            this.additionalCreds = additionalCreds;
        } else {
            if (additionalCreds != null)
                additionalCreds = null;
        }

        tgsReqMessg = createRequest(
                options,
                asCreds.ticket,
                asCreds.key,
                ctime,
                princName,
                servName,
                from,
                till,
                rtime,
                eTypes,
                addresses,
                authorizationData,
                additionalCreds,
                subKey,
                extraPAs);
        obuf = tgsReqMessg.asn1Encode();

        // XXX We need to revisit this to see if can't move it
        // up such that FORWARDED flag set in the options
        // is included in the marshaled request.
        /*
         * If this is based on a forwarded ticket, record that in the
         * options, because the returned TgsRep will contain the
         * FORWARDED flag set.
         */
        if (asCreds.flags.get(KDCOptions.FORWARDED))
            options.set(KDCOptions.FORWARDED, true);


    }

    /**
     * Sends the request, waits for a reply, and returns the Credentials.
     * Used in Credentials, KrbCred, and internal/CredentialsUtil.
     */
    public Credentials sendAndGetCreds() throws IOException, KrbException {
        String realmStr = servName != null
                ? servName.getRealmString()
                : null;
        KdcComm comm = new KdcComm(realmStr);
        return new KrbTgsRep(comm.send(this), this).getCreds();
    }

    KerberosTime getCtime() {
        return ctime;
    }

    private TGSReq createRequest(
                         KDCOptions kdc_options,
                         Ticket ticket,
                         EncryptionKey key,
                         KerberosTime ctime,
                         PrincipalName cname,
                         PrincipalName sname,
                         KerberosTime from,
                         KerberosTime till,
                         KerberosTime rtime,
                         int[] eTypes,
                         HostAddresses addresses,
                         AuthorizationData authorizationData,
                         Credentials additionalCreds,
                         EncryptionKey subKey,
                         PAData[] extraPAs)
        throws IOException, KrbException, UnknownHostException {
        KerberosTime req_till = null;
        if (till == null) {
            String d = Config.getInstance().get("libdefaults", "ticket_lifetime");
            if (d != null) {
                req_till = new KerberosTime(Instant.now().plusSeconds(Config.duration(d)));
            } else {
                req_till = new KerberosTime(0); // Choose KDC maximum allowed
            }
        } else {
            req_till = till;
        }

        /*
         * RFC 4120, Section 5.4.2.
         * For KRB_TGS_REP, the ciphertext is encrypted in the
         * sub-session key from the Authenticator, or if absent,
         * the session key from the ticket-granting ticket used
         * in the request.
         *
         * To support this, use tgsReqKey to remember which key to use.
         */
        tgsReqKey = key;

        int[] req_eTypes = null;
        if (eTypes == null) {
            req_eTypes = EType.getDefaults("default_tgs_enctypes");
        } else {
            req_eTypes = eTypes;
        }

        EncryptionKey reqKey = null;
        EncryptedData encAuthorizationData = null;
        if (authorizationData != null) {
            byte[] ad = authorizationData.asn1Encode();
            if (subKey != null) {
                reqKey = subKey;
                tgsReqKey = subKey;    // Key to use to decrypt reply
                useSubkey = true;
                encAuthorizationData = new EncryptedData(reqKey, ad,
                    KeyUsage.KU_TGS_REQ_AUTH_DATA_SUBKEY);
            } else
                encAuthorizationData = new EncryptedData(key, ad,
                    KeyUsage.KU_TGS_REQ_AUTH_DATA_SESSKEY);
        }

        Ticket[] additionalTickets = additionalCreds == null ? null
                : new Ticket[] { additionalCreds.getTicket() };
        KDCReqBody reqBody = new KDCReqBody(
                                            kdc_options,
                                            cname,
                                            sname,
                                            from,
                                            req_till,
                                            rtime,
                                            Nonce.value(),
                                            req_eTypes,
                                            addresses,
                                            encAuthorizationData,
                                            additionalTickets);

        byte[] temp = reqBody.asn1Encode(Krb5.KRB_TGS_REQ);
        Checksum cksum  = new Checksum(-1, temp, key,
                KeyUsage.KU_PA_TGS_REQ_CKSUM);

        // Usage will be KeyUsage.KU_PA_TGS_REQ_AUTHENTICATOR

        byte[] tgs_ap_req = new KrbApReq(
                                         new APOptions(),
                                         ticket,
                                         key,
                                         cname,
                                         cksum,
                                         ctime,
                                         reqKey,
                                         null,
                                         null).getMessage();

        PAData tgsPAData = new PAData(Krb5.PA_TGS_REQ, tgs_ap_req);
        PAData[] pa;
        if (extraPAs != null) {
            pa = Arrays.copyOf(extraPAs, extraPAs.length + 1);
            pa[extraPAs.length] = tgsPAData;
        } else {
            pa = new PAData[] {tgsPAData};
        }
        return new TGSReq(pa, reqBody);
    }

    TGSReq getMessage() {
        return tgsReqMessg;
    }

    Credentials getAdditionalCreds() {
        return additionalCreds;
    }

    PrincipalName getClientAlias() {
        return clientAlias;
    }

    PrincipalName getServerAlias() {
        return serverAlias;
    }

    boolean usedSubkey() {
        return useSubkey;
    }
}
