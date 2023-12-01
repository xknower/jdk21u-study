package com.sun.org.apache.xml.internal.security.utils.resolver;

import com.sun.org.apache.xml.internal.security.signature.XMLSignatureInput;

/**
 * During reference validation, we have to retrieve resources from somewhere.
 *
 * Extensions of this class must be thread-safe.
 */
public abstract class ResourceResolverSpi {

    /**
     * This is the workhorse method used to resolve resources.
     * @param context Context to use to resolve resources.
     *
     * @return the resource wrapped around a XMLSignatureInput
     *
     * @throws ResourceResolverException
     */
    public abstract XMLSignatureInput engineResolveURI(ResourceResolverContext context)
        throws ResourceResolverException;

    /**
     * This method helps the {@link ResourceResolver} to decide whether a
     * {@link ResourceResolverSpi} is able to perform the requested action.
     *
     * @param context Context in which to do resolution.
     * @return true if the engine can resolve the uri
     */
    public abstract boolean engineCanResolveURI(ResourceResolverContext context);

}
