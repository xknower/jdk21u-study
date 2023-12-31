package sun.security.jgss;

import java.security.PrivilegedAction;
import java.util.HashMap;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import org.ietf.jgss.Oid;
import sun.security.action.GetPropertyAction;

/**
 * A Configuration implementation especially designed for JGSS.
 *
 * @author weijun.wang
 * @since 1.6
 */
public class LoginConfigImpl extends Configuration {

    private final Configuration config;
    private final GSSCaller caller;
    private final String mechName;
    private static final sun.security.util.Debug debug =
        sun.security.util.Debug.getInstance("gssloginconfig", "\t[GSS LoginConfigImpl]");

    public static final boolean HTTP_USE_GLOBAL_CREDS;

    static {
        String prop = GetPropertyAction
                .privilegedGetProperty("http.use.global.creds");
        //HTTP_USE_GLOBAL_CREDS = "true".equalsIgnoreCase(prop); // default false
        HTTP_USE_GLOBAL_CREDS = !"false".equalsIgnoreCase(prop); // default true
    }


    /**
     * A new instance of LoginConfigImpl must be created for each login request
     * since it's only used by a single (caller, mech) pair
     * @param caller defined in GSSUtil as CALLER_XXX final fields
     * @param mech defined in GSSUtil as XXX_MECH_OID final fields
     */
    @SuppressWarnings("removal")
    public LoginConfigImpl(GSSCaller caller, Oid mech) {

        this.caller = caller;

        if (mech.equals(GSSUtil.GSS_KRB5_MECH_OID)) {
            mechName = "krb5";
        } else {
            throw new IllegalArgumentException(mech.toString() + " not supported");
        }
        config = java.security.AccessController.doPrivileged
                ((PrivilegedAction<Configuration>) Configuration::getConfiguration);
    }

    /**
     * @param name Almost useless, since the (caller, mech) is already passed
     *             into constructor. The only use will be detecting OTHER which
     *             is called in LoginContext
     */
    public AppConfigurationEntry[] getAppConfigurationEntry(String name) {

        AppConfigurationEntry[] entries = null;

        // This is the second call from LoginContext, which we will just ignore
        if ("OTHER".equalsIgnoreCase(name)) {
            return null;
        }

        String[] alts = null;

        // Compatibility:
        // For the 4 old callers, old entry names will be used if the new
        // entry name is not provided.

        if ("krb5".equals(mechName)) {
            if (caller == GSSCaller.CALLER_INITIATE) {
                alts = new String[] {
                    "com.sun.security.jgss.krb5.initiate",
                    "com.sun.security.jgss.initiate",
                };
            } else if (caller == GSSCaller.CALLER_ACCEPT) {
                alts = new String[] {
                    "com.sun.security.jgss.krb5.accept",
                    "com.sun.security.jgss.accept",
                };
            } else if (caller instanceof HttpCaller) {
                alts = new String[] {
                    "com.sun.security.jgss.krb5.initiate",
                };
            } else if (caller == GSSCaller.CALLER_UNKNOWN) {
                throw new AssertionError("caller not defined");
            }
        } else {
            throw new IllegalArgumentException(mechName + " not supported");
            // No other mech at the moment, maybe --
            /*
            switch (caller) {
            case GSSUtil.CALLER_INITIATE:
            case GSSUtil.CALLER_HTTP_NEGOTIATE:
                alts = new String[] {
                    "com.sun.security.jgss." + mechName + ".initiate",
                };
                break;
            case GSSUtil.CALLER_ACCEPT:
                alts = new String[] {
                    "com.sun.security.jgss." + mechName + ".accept",
                };
                break;
            case GSSUtil.CALLER_UNKNOWN:
                // should never use
                throw new AssertionError("caller cannot be unknown");
            default:
                throw new AssertionError("caller not defined");
            }
             */
        }
        for (String alt: alts) {
            entries = config.getAppConfigurationEntry(alt);
            if (debug != null) {
                debug.println("Trying " + alt +
                        ((entries == null)?": does not exist.":": Found!"));
            }
            if (entries != null) {
                break;
            }
        }

        if (entries == null) {
            if (debug != null) {
                debug.println("Cannot read JGSS entry, use default values instead.");
            }
            entries = getDefaultConfigurationEntry();
        }
        return entries;
    }

    /**
     * Default value for a caller-mech pair when no entry is defined in
     * the system-wide Configuration object.
     */
    private AppConfigurationEntry[] getDefaultConfigurationEntry() {
        HashMap <String, String> options = new HashMap<>(2);

        if (mechName == null || mechName.equals("krb5")) {
            if (isServerSide(caller)) {
                // Assuming the keytab file can be found through
                // krb5 config file or under user home directory
                options.put("useKeyTab", "true");
                options.put("storeKey", "true");
                options.put("doNotPrompt", "true");
                options.put("principal", "*");
                options.put("isInitiator", "false");
            } else {
                if (caller instanceof HttpCaller && !HTTP_USE_GLOBAL_CREDS) {
                    options.put("useTicketCache", "false");
                } else {
                    options.put("useTicketCache", "true");
                }
                options.put("doNotPrompt", "false");
            }
            return new AppConfigurationEntry[] {
                new AppConfigurationEntry(
                        "com.sun.security.auth.module.Krb5LoginModule",
                        AppConfigurationEntry.LoginModuleControlFlag.REQUIRED,
                        options)
            };
        }
        return null;
    }

    private static boolean isServerSide (GSSCaller caller) {
        return GSSCaller.CALLER_ACCEPT == caller;
    }
}
