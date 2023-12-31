package com.sun.org.apache.xerces.internal.impl.dv.xs;

import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;

/**
 * Represent the schema type "anySimpleType"
 *
 * @xerces.internal
 *
 * @author Neeraj Bajaj, Sun Microsystems, inc.
 * @author Sandy Gao, IBM
 *
 */
public class AnySimpleDV extends TypeValidator {

    public short getAllowedFacets() {
        // anySimpleType doesn't allow any facet, not even whiteSpace
        return 0;
    }

    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException {
        return content;
    }

} // class AnySimpleDV
