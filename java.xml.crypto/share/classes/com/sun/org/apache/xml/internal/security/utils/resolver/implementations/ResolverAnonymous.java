package com.sun.org.apache.xml.internal.security.utils.resolver.implementations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.sun.org.apache.xml.internal.security.signature.XMLSignatureInput;
import com.sun.org.apache.xml.internal.security.utils.resolver.ResourceResolverContext;
import com.sun.org.apache.xml.internal.security.utils.resolver.ResourceResolverException;
import com.sun.org.apache.xml.internal.security.utils.resolver.ResourceResolverSpi;

/**
 */
public class ResolverAnonymous extends ResourceResolverSpi {

    private final Path resourcePath;

    /**
     * @param filename
     * @throws IOException
     */
    public ResolverAnonymous(String filename) throws IOException {
        this(Paths.get(filename));
    }

    /**
     * @param resourcePath
     */
    public ResolverAnonymous(Path resourcePath) {
        this.resourcePath = resourcePath;
    }

    /** {@inheritDoc} */
    @Override
    public XMLSignatureInput engineResolveURI(ResourceResolverContext context) throws ResourceResolverException {
        try {
            XMLSignatureInput input = new XMLSignatureInput(Files.newInputStream(resourcePath));
            input.setSecureValidation(context.secureValidation);
            return input;
        } catch (IOException e) {
            throw new ResourceResolverException(e, context.uriToResolve, context.baseUri, "generic.EmptyMessage");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean engineCanResolveURI(ResourceResolverContext context) {
        return context.uriToResolve == null;
    }

}
