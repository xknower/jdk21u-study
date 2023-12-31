package com.sun.jndi.ldap.pool;

/**
 * Is a reference to Connections that is stored in Pool.
 * This is an intermediate object that is outside of the circular
 * reference loop of
 *  com.sun.jndi.ldap.Connection <-> com.sun.jndi.ldap.LdapClient
 *    <-> com.sun.jndi.ldap.pool.Connections
 *
 * Because Connection is a daemon thread, it will keep LdapClient
 * alive until LdapClient closes Connection. This will in turn
 * keep Connections alive. So even when Connections is removed
 * from (the WeakHashMap of) Pool, it won't be finalized.
 * ConnectionsRef acts as Connections's finalizer.
 *
 * Without connection pooling, com.sun.jndi.ldap.LdapCtx's finalize()
 * closes LdapClient, which in turn closes Connection.
 * With connection pooling, ConnectionsRef's finalize() calls
 * Connections.close(), which in turn will close all idle connections
 * and mark Connections such that in-use connections will be closed
 * when they are returned to the pool.
 */
final class ConnectionsRef {
    private final Connections conns;

    ConnectionsRef(Connections conns) {
        this.conns = conns;
    }

    Connections getConnections() {
        return conns;
    }
}
