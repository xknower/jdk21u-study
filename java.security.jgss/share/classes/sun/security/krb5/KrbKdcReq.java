package sun.security.krb5;

/**
 * Parent class for KrbAsReq and KrbTgsReq.
 */
abstract class KrbKdcReq {

    protected byte[] obuf;

    public byte[] encoding() {
        return obuf;
    }
}
