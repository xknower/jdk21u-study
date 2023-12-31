package sun.security.krb5.internal;

import sun.security.action.GetPropertyAction;
import sun.security.krb5.internal.rcache.AuthTimeWithHash;
import sun.security.krb5.internal.rcache.MemoryCache;
import sun.security.krb5.internal.rcache.DflCache;

/**
 * Models the replay cache of an acceptor as described in
 * RFC 4120 3.2.3.
 * @since 1.8
 */
public abstract class ReplayCache {
    public static ReplayCache getInstance(String type) {
        if (type == null) {
            return new MemoryCache();
        } else if (type.equals("dfl") || type.startsWith("dfl:")) {
            return new DflCache(type);
        } else if (type.equals("none")) {
            return new ReplayCache() {
                @Override
                public void checkAndStore(KerberosTime currTime, AuthTimeWithHash time)
                        throws KrbApErrException {
                    // no check at all
                }
            };
        } else {
            throw new IllegalArgumentException("Unknown type: " + type);
        }
    }
    public static ReplayCache getInstance() {
        String type = GetPropertyAction
                .privilegedGetProperty("sun.security.krb5.rcache");
        return getInstance(type);
    }

    /**
     * Accepts or rejects an AuthTime.
     * @param currTime the current time
     * @param time AuthTimeWithHash object calculated from authenticator
     * @throws KrbApErrException if the authenticator is a replay
     */
    public abstract void checkAndStore(KerberosTime currTime, AuthTimeWithHash time)
            throws KrbApErrException;
}
