package sun.security.jgss.spi;

import org.ietf.jgss.*;
import java.security.Provider;

/**
 * This interface is implemented by a mechanism specific credential
 * element. A GSSCredential is conceptually a container class of several
 * credential elements from different mechanisms.
 *
 * @author Mayank Upadhyay
 */
public interface GSSCredentialSpi {

    Provider getProvider();

    /**
     * Called to invalidate this credential element and release
     * any system recourses and cryptographic information owned
     * by the credential.
     *
     * @exception GSSException with major codes NO_CRED and FAILURE
     */
    void dispose() throws GSSException;

    /**
     * Returns the principal name for this credential. The name
     * is in mechanism specific format.
     *
     * @return GSSNameSpi representing principal name of this credential
     * @exception GSSException may be thrown
     */
    GSSNameSpi getName() throws GSSException;

    /**
     * Returns the init lifetime remaining.
     *
     * @return the init lifetime remaining in seconds
     * @exception GSSException may be thrown
     */
    int getInitLifetime() throws GSSException;


    /**
     * Returns the accept lifetime remaining.
     *
     * @return the accept lifetime remaining in seconds
     * @exception GSSException may be thrown
     */
    int getAcceptLifetime() throws GSSException;

    /**
     * Determines if this credential element can be used by a context
     * initiator.
     * @return true if it can be used for initiating contexts
     */
    boolean isInitiatorCredential() throws GSSException;

    /**
     * Determines if this credential element can be used by a context
     * acceptor.
     * @return true if it can be used for accepting contexts
     */
    boolean isAcceptorCredential() throws GSSException;

    /**
     * Returns the oid representing the underlying credential
     * mechanism oid.
     *
     * @return the Oid for this credential mechanism
     * @exception GSSException may be thrown
     */
    Oid getMechanism();

    /**
     * Impersonates another client.
     *
     * @param name the client to impersonate
     * @return the new credential
     * @exception GSSException may be thrown
     */
    GSSCredentialSpi impersonate(GSSNameSpi name) throws GSSException;
}
