package com.sun.net.httpserver;

import java.net.InetSocketAddress;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;

/**
 * Represents the set of parameters for each https connection negotiated with
 * clients. One of these is created and passed to
 * {@link HttpsConfigurator#configure(HttpsParameters)} for every incoming https
 * connection, in order to determine the parameters to use.
 *
 * <p> The underlying SSL parameters may be established either via the set/get
 * methods of this class, or else via a {@link javax.net.ssl.SSLParameters}
 * object. {@code SSLParameters} is the preferred method, because in the future,
 * additional configuration capabilities may be added to that class, and it is
 * easier to determine the set of supported parameters and their default values
 * with SSLParameters. Also, if an {@code SSLParameters} object is provided via
 * {@link #setSSLParameters(SSLParameters)} then those parameter settings are
 * used, and any settings made in this object are ignored.
 *
 * @since 1.6
 */
public abstract class HttpsParameters {

    private String[] cipherSuites;
    private String[] protocols;
    private boolean wantClientAuth;
    private boolean needClientAuth;

    /**
     * Constructor for subclasses to call.
     */
    protected HttpsParameters() {}

    /**
     * Returns the {@link HttpsConfigurator} for this {@code HttpsParameters}.
     *
     * @return {@code HttpsConfigurator} for this instance of {@code HttpsParameters}
     */
    public abstract HttpsConfigurator getHttpsConfigurator();

    /**
     * Returns the address of the remote client initiating the connection.
     *
     * @return address of the remote client initiating the connection
     */
    public abstract InetSocketAddress getClientAddress();

    /**
     * Sets the {@link SSLParameters} to use for this {@code HttpsParameters}.
     * The parameters must be supported by the {@link SSLContext} contained
     * by the {@link HttpsConfigurator} associated with this {@code HttpsParameters}.
     * If no parameters are set, then the default behavior is to use
     * the default parameters from the associated {@link SSLContext}.
     *
     * @param params the {@code SSLParameters} to set. If {@code null} then the
     *               existing parameters (if any) remain unchanged
     * @throws IllegalArgumentException if any of the parameters are invalid or
     * unsupported
     */
    public abstract void setSSLParameters(SSLParameters params);

    /**
     * Returns a copy of the array of ciphersuites or {@code null} if none
     * have been set.
     *
     * @return a copy of the array of ciphersuites or {@code null} if none have
     * been set
     */
    public String[] getCipherSuites() {
        return cipherSuites != null ? cipherSuites.clone() : null;
    }

    /**
     * Sets the array of ciphersuites.
     *
     * @param cipherSuites the array of ciphersuites (or {@code null})
     */
    public void setCipherSuites(String[] cipherSuites) {
        this.cipherSuites = cipherSuites != null ? cipherSuites.clone() : null;
    }

    /**
     * Returns a copy of the array of protocols or {@code null} if none have been
     * set.
     *
     * @return a copy of the array of protocols or {@code null} if none have been
     * set
     */
    public String[] getProtocols() {
        return protocols != null ? protocols.clone() : null;
    }

    /**
     * Sets the array of protocols.
     *
     * @param protocols the array of protocols (or {@code null})
     */
    public void setProtocols(String[] protocols) {
        this.protocols = protocols != null ? protocols.clone() : null;
    }

    /**
     * Returns whether client authentication should be requested.
     *
     * @return whether client authentication should be requested
     */
    public boolean getWantClientAuth() {
        return wantClientAuth;
    }

    /**
     * Sets whether client authentication should be requested. Calling this
     * method clears the {@code needClientAuth} flag.
     *
     * @param wantClientAuth whether client authentication should be requested
     */
    public void setWantClientAuth(boolean wantClientAuth) {
        this.wantClientAuth = wantClientAuth;
    }

    /**
     * Returns whether client authentication should be required.
     *
     * @return whether client authentication should be required
     */
    public boolean getNeedClientAuth() {
        return needClientAuth;
    }

    /**
     * Sets whether client authentication should be required. Calling this method
     * clears the {@code wantClientAuth} flag.
     *
     * @param needClientAuth whether client authentication should be required
     */
    public void setNeedClientAuth(boolean needClientAuth) {
        this.needClientAuth = needClientAuth;
    }
}
