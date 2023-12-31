package com.sun.security.sasl.ntlm;

import java.util.Map;

import javax.security.sasl.*;
import javax.security.auth.callback.CallbackHandler;

import com.sun.security.sasl.util.PolicyUtils;


/**
 * Client and server factory for NTLM SASL client/server mechanisms.
 * See NTLMClient and NTLMServer for input requirements.
 *
 * @since 1.7
 */

public final class FactoryImpl implements SaslClientFactory,
SaslServerFactory{

    private static final String[] myMechs = { "NTLM" };
    private static final int[] mechPolicies = {
            PolicyUtils.NOPLAINTEXT|PolicyUtils.NOANONYMOUS
    };

    /**
     * Empty constructor.
     */
    public FactoryImpl() {
    }

    /**
     * Returns a new instance of the NTLM SASL client mechanism.
     * Argument checks are performed in SaslClient's constructor.
     * @return a new SaslClient; otherwise null if unsuccessful.
     * @throws SaslException If there is an error creating the NTLM
     * SASL client.
     */
    public SaslClient createSaslClient(String[] mechs,
         String authorizationId, String protocol, String serverName,
         Map<String,?> props, CallbackHandler cbh)
         throws SaslException {

         for (int i=0; i<mechs.length; i++) {
            if (mechs[i].equals("NTLM") &&
                    PolicyUtils.checkPolicy(mechPolicies[0], props)) {

                if (cbh == null) {
                    throw new SaslException(
                        "Callback handler with support for " +
                        "RealmCallback, NameCallback, and PasswordCallback " +
                        "required");
                }
                return new NTLMClient(mechs[i], authorizationId,
                    protocol, serverName, props, cbh);
            }
        }
        return null;
    }

    /**
     * Returns a new instance of the NTLM SASL server mechanism.
     * Argument checks are performed in SaslServer's constructor.
     * @return a new SaslServer; otherwise null if unsuccessful.
     * @throws SaslException If there is an error creating the NTLM
     * SASL server.
     */
    public SaslServer createSaslServer(String mech,
         String protocol, String serverName, Map<String,?> props, CallbackHandler cbh)
         throws SaslException {

         if (mech.equals("NTLM") &&
                 PolicyUtils.checkPolicy(mechPolicies[0], props)) {
             if (props != null) {
                 String qop = (String)props.get(Sasl.QOP);
                 if (qop != null && !qop.equals("auth")) {
                     throw new SaslException("NTLM only support auth");
                 }
             }
             if (cbh == null) {
                 throw new SaslException(
                     "Callback handler with support for " +
                     "RealmCallback, NameCallback, and PasswordCallback " +
                     "required");
             }
             return new NTLMServer(mech, protocol, serverName, props, cbh);
         }
         return null;
    }

    /**
     * Returns the authentication mechanisms that this factory can produce.
     *
     * @return String[] {"NTLM"} if policies in env match those of this
     * factory.
     */
    public String[] getMechanismNames(Map<String,?> env) {
        return PolicyUtils.filterMechs(myMechs, mechPolicies, env);
    }
}
