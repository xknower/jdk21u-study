package com.sun.security.sasl;

import javax.security.sasl.*;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Implements the EXTERNAL SASL client mechanism.
 * (<A HREF="http://www.ietf.org/rfc/rfc2222.txt">RFC 2222</A>).
 * The EXTERNAL mechanism returns the optional authorization ID as
 * the initial response. It processes no challenges.
 *
 * @author Rosanna Lee
 */
final class ExternalClient implements SaslClient {
    private byte[] username;
    private boolean completed = false;

    /**
     * Constructs an External mechanism with optional authorization ID.
     *
     * @param authorizationID If non-null, used to specify authorization ID.
     */
    ExternalClient(String authorizationID) {
        if (authorizationID != null) {
            username = authorizationID.getBytes(UTF_8);
        } else {
            username = new byte[0];
        }
    }

    /**
     * Retrieves this mechanism's name for initiating the "EXTERNAL" protocol
     * exchange.
     *
     * @return  The string "EXTERNAL".
     */
    public String getMechanismName() {
        return "EXTERNAL";
    }

    /**
     * This mechanism has an initial response.
     */
    public boolean hasInitialResponse() {
        return true;
    }

    public void dispose() throws SaslException {
    }

    /**
     * Processes the challenge data.
     * It returns the EXTERNAL mechanism's initial response,
     * which is the authorization id encoded in UTF-8.
     * This is the optional information that is sent along with the SASL command.
     * After this method is called, isComplete() returns true.
     *
     * @param challengeData Ignored.
     * @return The possible empty initial response.
     * @throws IllegalStateException If authentication has already been called.
     */
    public byte[] evaluateChallenge(byte[] challengeData) {
        if (completed) {
            throw new IllegalStateException(
                "EXTERNAL authentication already completed");
        }
        completed = true;
        return username;
    }

    /**
     * Returns whether this mechanism is complete.
     * @return true if initial response has been sent; false otherwise.
     */
    public boolean isComplete() {
        return completed;
    }

    /**
     * Unwraps the incoming buffer.
     *
     * @throws SaslException Not applicable to this mechanism.
     */
    public byte[] unwrap(byte[] incoming, int offset, int len)
        throws SaslException {
        if (completed) {
            throw new SaslException("EXTERNAL has no supported QOP");
        } else {
            throw new IllegalStateException(
                "EXTERNAL authentication Not completed");
        }
    }

    /**
     * Wraps the outgoing buffer.
     *
     * @throws SaslException Not applicable to this mechanism.
     */
    public byte[] wrap(byte[] outgoing, int offset, int len)
        throws SaslException {
        if (completed) {
            throw new SaslException("EXTERNAL has no supported QOP");
        } else {
            throw new IllegalStateException(
                "EXTERNAL authentication not completed");
        }
    }

    /**
     * Retrieves the negotiated property.
     * This method can be called only after the authentication exchange has
     * completed (i.e., when {@code isComplete()} returns true);
     * otherwise, an {@code IllegalStateException} is thrown.
     *
     * @return null No property is applicable to this mechanism.
     * @exception IllegalStateException if this authentication exchange
     * has not completed
     */
    public Object getNegotiatedProperty(String propName) {
        if (completed) {
            return null;
        } else {
            throw new IllegalStateException(
                "EXTERNAL authentication not completed");
        }
    }
}
