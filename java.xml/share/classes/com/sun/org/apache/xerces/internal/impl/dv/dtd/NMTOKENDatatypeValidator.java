package com.sun.org.apache.xerces.internal.impl.dv.dtd;

import com.sun.org.apache.xerces.internal.impl.dv.*;
import com.sun.org.apache.xerces.internal.util.XMLChar;

/**
 * NMTOKEN datatype validator.
 *
 * @xerces.internal
 *
 * @author Jeffrey Rodriguez, IBM
 * @author Sandy Gao, IBM
 *
 */
public class NMTOKENDatatypeValidator implements DatatypeValidator {

    // construct a NMTOKEN datatype validator
    public NMTOKENDatatypeValidator() {
    }

    /**
     * Checks that "content" string is valid NMTOKEN value.
     * If invalid a Datatype validation exception is thrown.
     *
     * @param content       the string value that needs to be validated
     * @param context       the validation context
     * @throws InvalidDatatypeException if the content is
     *         invalid according to the rules for the validators
     * @see InvalidDatatypeValueException
     */
    public void validate(String content, ValidationContext context) throws InvalidDatatypeValueException {
        if (!XMLChar.isValidNmtoken(content)) {
           throw new InvalidDatatypeValueException("NMTOKENInvalid", new Object[]{content});
       }
   }

} // class NMTOKENDatatypeValidator
