package sun.security.krb5;

import jdk.internal.util.OperatingSystem;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;


public class SCDynamicStoreConfig {
    private static native void installNotificationCallback();

    /**
     * Returns the dynamic store setting for kerberos in a string array.
     * (realm kdc* null) null (mapping-domain mapping-realm)*
     */
    private static native List<String> getKerberosConfig();
    private static boolean DEBUG = sun.security.krb5.internal.Krb5.DEBUG;

    static {
        @SuppressWarnings("removal")
        boolean isMac = java.security.AccessController.doPrivileged(
            new java.security.PrivilegedAction<Boolean>() {
                public Boolean run() {
                    if (OperatingSystem.isMacOS()) {
                        System.loadLibrary("osxkrb5");
                        return true;
                    }
                    return false;
                }
            });
        if (isMac) installNotificationCallback();
    }

    /**
     * Calls down to JNI to get the raw Kerberos Config and maps the object
     * graph to the one that Kerberos Config in Java expects
     *
     * @return
     * @throws IOException
     */
    public static Hashtable<String, Object> getConfig() throws IOException {
        List<String> list = getKerberosConfig();
        if (list == null) {
            throw new IOException(
                    "Could not load configuration from SCDynamicStore");
        }
        if (DEBUG) System.out.println("Raw map from JNI: " + list);

        Hashtable<String,Object> v = new Hashtable<>();
        Hashtable<String,Object> realms = new Hashtable<>();
        Iterator<String> iterator = list.iterator();
        String defaultRealm = null;

        while (true) {
            String nextRealm = iterator.next();
            if (nextRealm == null) {
                break;
            }
            if (defaultRealm == null) {
                defaultRealm = nextRealm;
                Hashtable<String,Object> dr = new Hashtable<>();
                dr.put("default_realm", v1(defaultRealm));
                v.put("libdefaults", dr);
            }
            Vector<String> kdcs = new Vector<>();
            while (true) {
                String nextKdc = iterator.next();
                if (nextKdc == null) {
                    break;
                }
                kdcs.add(nextKdc);
            }
            if (!kdcs.isEmpty()) {
                Hashtable<String,Object> ri = new Hashtable<>();
                ri.put("kdc", kdcs);
                realms.put(nextRealm, ri);
            }
        }
        if (!realms.isEmpty()) {
            v.put("realms", realms);
        }
        Hashtable<String,Object> mapping = new Hashtable<>();
        while (true) {
            if (!iterator.hasNext()) {
                break;
            }
            mapping.put(iterator.next(), v1(iterator.next()));
        }
        if (!mapping.isEmpty()) {
            v.put("domain_realm", mapping);
        }
        return v;
    }

    // Make a single value Vector. Config's stanzaTable always
    // use Vector as end values.
    private static Vector<String> v1(String s) {
        Vector<String> out = new Vector<>();
        out.add(s);
        return out;
    }
}
