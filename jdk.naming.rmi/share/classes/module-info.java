/**
 * Provides the implementation of the RMI Java Naming provider.
 *
 * @implNote
 * The following implementation specific system properties are supported by the
 * default RMI Naming Service Provider implementation in the JDK:
 * <ul>
 *     <li>{@systemProperty jdk.jndi.object.factoriesFilter}:
 *          <br>The value of this system property defines a filter used by
 *          the JNDI runtime implementation to control the set of object factory classes which will
 *          be allowed to instantiate objects from object references returned by naming/directory systems.
 *          The factory class named by the reference instance will be matched against this filter.
 *          The filter property supports pattern-based filter syntax with the same format as
 *          {@link java.io.ObjectInputFilter.Config#createFilter(String) jdk.serialFilter}. Limit patterns
 *          specified in the filter property are unused.
 *          This property can also be specified as a {@linkplain java.security.Security security property}.
 *          This property is also supported by the <a href="{@docRoot}/java.naming/module-summary.html">default
 *          LDAP Naming Service Provider</a>.
 *          <br>The default value allows any object factory class specified by the reference
 *          instance to recreate the referenced object.
 *     </li>
 *     <li>{@systemProperty jdk.jndi.rmi.object.factoriesFilter}:
 *          <br>The value of this system property defines a filter used by
 *          the JDK RMI provider implementation to further restrict the set of object factory classes which will
 *          be allowed to instantiate objects from object references returned by RMI systems.
 *          The factory class named by the {@linkplain javax.naming.Reference reference instance} first will be
 *          matched against this specific filter and then against the global filter. The factory class is rejected
 *          if any of these two filters reject it, or if none of them allow it.
 *          The filter property supports pattern-based filter syntax with the same format as
 *          {@link java.io.ObjectInputFilter.Config#createFilter(String) jdk.serialFilter}. Limit patterns
 *          specified in the filter property are unused.
 *          <br>The default value allows any object factory class provided by the JDK RMI provider
 *          implementation.
 *     </li>
 * </ul>
 * @provides javax.naming.spi.InitialContextFactory
 * @moduleGraph
 * @since 9
 */
module jdk.naming.rmi {
    requires java.naming;
    requires java.rmi;

    // temporary export until NamingManager.getURLContext uses services
    exports com.sun.jndi.url.rmi to java.naming;
    exports com.sun.jndi.rmi.registry to java.rmi;

    provides javax.naming.spi.InitialContextFactory with
        com.sun.jndi.rmi.registry.RegistryContextFactory;

}
