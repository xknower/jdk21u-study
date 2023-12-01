package com.sun.org.apache.xml.internal.security.transforms.implementations;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.sun.org.apache.xml.internal.security.c14n.CanonicalizationException;
import com.sun.org.apache.xml.internal.security.c14n.implementations.Canonicalizer20010315;
import com.sun.org.apache.xml.internal.security.c14n.implementations.Canonicalizer20010315OmitComments;
import com.sun.org.apache.xml.internal.security.signature.XMLSignatureInput;
import com.sun.org.apache.xml.internal.security.transforms.TransformSpi;
import com.sun.org.apache.xml.internal.security.transforms.Transforms;
import org.w3c.dom.Element;

/**
 * Implements the {@code http://www.w3.org/TR/2001/REC-xml-c14n-20010315}
 * transform.
 *
 */
public class TransformC14N extends TransformSpi {

    /**
     * {@inheritDoc}
     */
    @Override
    protected String engineGetURI() {
        return Transforms.TRANSFORM_C14N_OMIT_COMMENTS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected XMLSignatureInput enginePerformTransform(
        XMLSignatureInput input, OutputStream os, Element transformElement,
        String baseURI, boolean secureValidation
    ) throws CanonicalizationException {

        Canonicalizer20010315 c14n = getCanonicalizer();

        if (os == null && (input.isOctetStream() || input.isElement() || input.isNodeSet())) {
            try (ByteArrayOutputStream writer = new ByteArrayOutputStream()) {
                c14n.engineCanonicalize(input, writer, secureValidation);
                writer.flush();
                XMLSignatureInput output = new XMLSignatureInput(writer.toByteArray());
                output.setSecureValidation(secureValidation);
                return output;
            } catch (IOException ex) {
                throw new CanonicalizationException("empty", new Object[] {ex.getMessage()});
            }
        } else {
            c14n.engineCanonicalize(input, os, secureValidation);
            XMLSignatureInput output = new XMLSignatureInput((byte[])null);
            output.setSecureValidation(secureValidation);
            output.setOutputStream(os);
            return output;
        }
    }

    protected Canonicalizer20010315 getCanonicalizer() {
        return new Canonicalizer20010315OmitComments();
    }
}
