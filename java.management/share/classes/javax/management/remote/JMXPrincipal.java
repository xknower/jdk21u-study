package javax.management.remote;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.security.Principal;

/**
 * <p>The identity of a remote client of the JMX Remote API.</p>
 *
 * <p>Principals such as this <code>JMXPrincipal</code>
 * may be associated with a particular <code>Subject</code>
 * to augment that <code>Subject</code> with an additional
 * identity.  Refer to the {@link javax.security.auth.Subject}
 * class for more information on how to achieve this.
 * Authorization decisions can then be based upon
 * the Principals associated with a <code>Subject</code>.
 *
 * @see java.security.Principal
 * @see javax.security.auth.Subject
 * @since 1.5
 */
public class JMXPrincipal implements Principal, Serializable {

    private static final long serialVersionUID = -4184480100214577411L;

    /**
     * @serial The JMX Remote API name for the identity represented by
     * this <code>JMXPrincipal</code> object.
     * @see #getName()
     */
    private String name;

    /**
     * Creates a JMXPrincipal for a given identity.
     *
     * @param name the JMX Remote API name for this identity.
     *
     * @exception NullPointerException if the <code>name</code> is
     * <code>null</code>.
     */
    public JMXPrincipal(String name) {
        validate(name);
        this.name = name;
    }

    /**
     * Returns the name of this principal.
     *
     * @return the name of this <code>JMXPrincipal</code>.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a string representation of this <code>JMXPrincipal</code>.
     *
     * @return a string representation of this <code>JMXPrincipal</code>.
     */
    public String toString() {
        return("JMXPrincipal:  " + name);
    }

    /**
     * Compares the specified Object with this <code>JMXPrincipal</code>
     * for equality.  Returns true if the given object is also a
     * <code>JMXPrincipal</code> and the two JMXPrincipals
     * have the same name.
     *
     * @param o Object to be compared for equality with this
     * <code>JMXPrincipal</code>.
     *
     * @return true if the specified Object is equal to this
     * <code>JMXPrincipal</code>.
     */
    public boolean equals(Object o) {
        if (o == null)
            return false;

        if (this == o)
            return true;

        if (!(o instanceof JMXPrincipal))
            return false;
        JMXPrincipal that = (JMXPrincipal)o;

        return (this.getName().equals(that.getName()));
    }

    /**
     * Returns a hash code for this <code>JMXPrincipal</code>.
     *
     * @return a hash code for this <code>JMXPrincipal</code>.
     */
    public int hashCode() {
        return name.hashCode();
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ObjectInputStream.GetField gf = ois.readFields();
        String principalName = (String)gf.get("name", null);
        try {
            validate(principalName);
            this.name = principalName;
        } catch (NullPointerException e) {
            throw new InvalidObjectException(e.getMessage());
        }
    }

    private static void validate(String name) throws NullPointerException {
        if (name == null)
            throw new NullPointerException("illegal null input");
    }
}
