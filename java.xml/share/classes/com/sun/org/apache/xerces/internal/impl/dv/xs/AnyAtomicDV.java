package com.sun.org.apache.xerces.internal.impl.dv.xs;

import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;

/**
 * Represent the schema type "anyAtomicType"
 *
 * @xerces.experimental
 *
 * @author Ankit Pasricha, IBM
 *
 */
class AnyAtomicDV extends TypeValidator {

    public short getAllowedFacets() {
        return 0;
    }

    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException {
        return content;
    }

} // class AnyAtomicDV
