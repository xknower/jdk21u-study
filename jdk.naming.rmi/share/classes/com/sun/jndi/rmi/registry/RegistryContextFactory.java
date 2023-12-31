package com.sun.jndi.rmi.registry;


import java.util.Enumeration;
import java.util.Hashtable;

import javax.naming.*;
import javax.naming.spi.*;

import com.sun.jndi.url.rmi.rmiURLContextFactory;

/**
 * A RegistryContextFactory takes an RMI registry reference, and
 * creates the corresponding RMI object or registry context.  In
 * addition, it serves as the initial context factory when using an
 * RMI registry as an initial context.
 *<p>
 * When an initial context is being created, the environment
 * property "java.naming.provider.url" should contain the RMI URL of
 * the appropriate registry.  Otherwise, the default URL "rmi:" is used.
 *<p>
 * An RMI registry reference contains one or more StringRefAddrs of
 * type "URL", each containing a single RMI URL.  Other addresses
 * are ignored.  Multiple URLs represent alternative addresses for the
 * same logical resource.  The order of the addresses is not significant.
 *
 * @author Scott Seligman
 */


public class RegistryContextFactory
        implements ObjectFactory, InitialContextFactory
{
    /**
     * The type of each address in an RMI registry reference.
     */
    public static final String ADDRESS_TYPE = "URL";

    public Context getInitialContext(Hashtable<?,?> env) throws NamingException {

        if (env != null) {
            env = (Hashtable) env.clone();
        }
        return URLToContext(getInitCtxURL(env), env);
    }

    public Object getObjectInstance(Object ref, Name name, Context nameCtx,
                                    Hashtable<?,?> env)
            throws NamingException
    {
        if (!isRegistryRef(ref)) {
            return null;
        }
        /*
         * No need to clone env here.  If getObjectInstance()
         * returns something other than a RegistryContext (which
         * happens if you're looking up an object bound in the
         * registry, as opposed to looking up the registry itself),
         * then the context is GCed right away and there's no need to
         * clone the environment.  If getObjectInstance() returns a
         * RegistryContext, then it still goes through
         * GenericURLContext, which calls RegistryContext.lookup()
         * with an empty name, which clones the environment.
         */
        Object obj = URLsToObject(getURLs((Reference)ref), env);
        if (obj instanceof RegistryContext) {
            RegistryContext ctx = (RegistryContext)obj;
            ctx.reference = (Reference)ref;
        }
        return obj;
    }

    private static Context URLToContext(String url, Hashtable<?,?> env)
            throws NamingException
    {
        rmiURLContextFactory factory = new rmiURLContextFactory();
        Object obj = factory.getObjectInstance(url, null, null, env);

        if (obj instanceof Context) {
            return (Context)obj;
        } else {
            throw (new NotContextException(url));
        }
    }

    private static Object URLsToObject(String[] urls, Hashtable<?,?> env)
            throws NamingException
    {
        rmiURLContextFactory factory = new rmiURLContextFactory();
        return factory.getObjectInstance(urls, null, null, env);
    }

    /**
     * Reads environment to find URL of initial context.
     * The default URL is "rmi:".
     */
    private static String getInitCtxURL(Hashtable<?,?> env) {

        final String defaultURL = "rmi:";

        String url = null;
        if (env != null) {
            url = (String)env.get(Context.PROVIDER_URL);
        }
        return ((url != null) ? url : defaultURL);
    }

    /**
     * Returns true if argument is an RMI registry reference.
     */
    private static boolean isRegistryRef(Object obj) {

        if (!(obj instanceof Reference)) {
            return false;
        }
        String thisClassName = RegistryContextFactory.class.getName();
        Reference ref = (Reference)obj;

        return thisClassName.equals(ref.getFactoryClassName());
    }

    /**
     * Returns the URLs contained within an RMI registry reference.
     */
    private static String[] getURLs(Reference ref) throws NamingException {

        int size = 0;   // number of URLs
        String[] urls = new String[ref.size()];

        Enumeration<RefAddr> addrs = ref.getAll();
        while (addrs.hasMoreElements()) {
            RefAddr addr = addrs.nextElement();

            if ((addr instanceof StringRefAddr) &&
                addr.getType().equals(ADDRESS_TYPE)) {

                urls[size++] = (String)addr.getContent();
            }
        }
        if (size == 0) {
            throw (new ConfigurationException(
                    "Reference contains no valid addresses"));
        }

        // Trim URL array down to size.
        if (size == ref.size()) {
            return urls;
        }
        String[] urls2 = new String[size];
        System.arraycopy(urls, 0, urls2, 0, size);
        return urls2;
    }
}
