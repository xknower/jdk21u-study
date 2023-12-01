package jdk.internal.event;


/**
 * Event recording details of X.509 Certificate.
 */

public final class X509CertificateEvent extends Event {
    private static final X509CertificateEvent EVENT = new X509CertificateEvent();

    /**
     * Returns {@code true} if event is enabled, {@code false} otherwise.
     */
    public static boolean isTurnedOn() {
        return EVENT.isEnabled();
    }

    public String algorithm;
    public String serialNumber;
    public String subject;
    public String issuer;
    public String keyType;
    public int keyLength;
    public long certificateId;
    public long validFrom;
    public long validUntil;
}
