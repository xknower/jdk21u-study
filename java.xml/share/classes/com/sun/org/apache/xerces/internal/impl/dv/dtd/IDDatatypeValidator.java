package com.sun.org.apache.xerces.internal.impl.dv.dtd;

import com.sun.org.apache.xerces.internal.impl.dv.*;
import com.sun.org.apache.xerces.internal.util.XMLChar;

/**
 * <P>IDDatatypeValidator - ID represents the ID attribute
 * type from XML 1.0 Recommendation. The value space
 * od ID is the set of all strings that match the
 * NCName production and have been used in an XML
 * document. The lexical space of ID is the set of all
 * strings that match the NCName production.</P>
 * <P>The value space of ID is scoped to a specific
 * instance document.</P>
 * <P>The following constraint applies:
 * An ID must not appear more than once in an XML
 * document as a value of this type; i.e., ID values
 * must uniquely identify the elements which bear
 * them.</P>
 *
 * @xerces.internal
 *
 * @author Jeffrey Rodriguez, IBM
 * @author Sandy Gao, IBM
 *
 */
public class IDDatatypeValidator implements DatatypeValidator {

    // construct an ID datatype validator
    public IDDatatypeValidator() {
    }

    /**
     * Checks that "content" string is valid ID value.
     * If invalid a Datatype validation exception is thrown.
     *
     * @param content       the string value that needs to be validated
     * @param context       the validation context
     * @throws InvalidDatatypeException if the content is
     *         invalid according to the rules for the validators
     * @see InvalidDatatypeValueException
     */
    public void validate(String content, ValidationContext context) throws InvalidDatatypeValueException {

        //Check if is valid key-[81] EncName ::= [A-Za-z] ([A-Za-z0-9._] | '-')*
        if(context.useNamespaces()) {
            if (!XMLChar.isValidNCName(content)) {
                throw new InvalidDatatypeValueException("IDInvalidWithNamespaces", new Object[]{content});
            }
        }
        else {
            if (!XMLChar.isValidName(content)) {
                throw new InvalidDatatypeValueException("IDInvalid", new Object[]{content});
            }
        }

        if (context.isIdDeclared(content)) {
            throw new InvalidDatatypeValueException("IDNotUnique", new Object[]{content});
        }

        context.addId(content);
    }

}
