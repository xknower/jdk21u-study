package com.sun.org.apache.xml.internal.security.transforms.implementations;

import com.sun.org.apache.xml.internal.security.c14n.implementations.Canonicalizer20010315;
import com.sun.org.apache.xml.internal.security.c14n.implementations.Canonicalizer20010315WithComments;
import com.sun.org.apache.xml.internal.security.transforms.Transforms;

/**
 * Implements the {@code http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments}
 * transform.
 */
public class TransformC14NWithComments extends TransformC14N {

    /**
     * {@inheritDoc}
     */
    @Override
    protected String engineGetURI() {
        return Transforms.TRANSFORM_C14N_WITH_COMMENTS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Canonicalizer20010315 getCanonicalizer() {
        return new Canonicalizer20010315WithComments();
    }

}
