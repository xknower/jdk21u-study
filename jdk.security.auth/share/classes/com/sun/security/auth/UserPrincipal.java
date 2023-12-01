package com.sun.security.auth;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.security.Principal;

/**
 * A user principal identified by a username or account name.
 *
 * <p>
 * After successful authentication, a user {@link java.security.Principal}
 * can be associated with a particular {@link javax.security.auth.Subject}
 * to augment that <code>Subject</code> with an additional identity.
 * Authorization decisions can then be based upon the
 * <code>Principal</code>s that are associated with a <code>Subject</code>.
 *
 * <p>
 * This class is immutable.
 *
 * @since 1.6
 */
public final class UserPrincipal implements Principal, java.io.Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 892106070870210969L;

    /**
     * The principal's name
     *
     * @serial
     */
    private final String name;

    /**
     * Creates a principal.
     *
     * @param name The principal's string name.
     * @exception NullPointerException If the <code>name</code> is
     * <code>null</code>.
     */
    public UserPrincipal(String name) {
        if (name == null) {
            throw new NullPointerException("null name is illegal");
        }
        this.name = name;
    }

    /**
     * Compares this principal to the specified object.
     *
     * @param object The object to compare this principal against.
     * @return true if they are equal; false otherwise.
     */
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof UserPrincipal) {
            return name.equals(((UserPrincipal)object).getName());
        }
        return false;
    }

    /**
     * Returns a hash code for this principal.
     *
     * @return The principal's hash code.
     */
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Returns the name of this principal.
     *
     * @return The principal's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a string representation of this principal.
     *
     * @return The principal's name.
     */
    public String toString() {
        return name;
    }

    /**
     * Restores the state of this object from the stream.
     *
     * @param  stream the {@code ObjectInputStream} from which data is read
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException if a serialized class cannot be loaded
     */
    @java.io.Serial
    private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        if (name == null) {
            throw new InvalidObjectException("null name is illegal");
        }
    }
}
