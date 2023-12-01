package com.sun.org.apache.xml.internal.serializer.dom3;

import org.w3c.dom.DOMError;
import org.w3c.dom.DOMErrorHandler;

/**
 * This is the default implementation of the ErrorHandler interface and is
 * used if one is not provided.  The default implementation simply reports
 * DOMErrors to System.err.
 *
 * @xsl.usage internal
 */
final class DOMErrorHandlerImpl implements DOMErrorHandler {

    /**
     * Default Constructor
     */
    DOMErrorHandlerImpl() {
    }

    /**
     * Implementation of DOMErrorHandler.handleError that
     * adds copy of error to list for later retrieval.
     *
     */
    public boolean handleError(DOMError error) {
        boolean fail = true;
        String severity = null;
        if (error.getSeverity() == DOMError.SEVERITY_WARNING) {
            fail = false;
            severity = "[Warning]";
        } else if (error.getSeverity() == DOMError.SEVERITY_ERROR) {
            severity = "[Error]";
        } else if (error.getSeverity() == DOMError.SEVERITY_FATAL_ERROR) {
            severity = "[Fatal Error]";
        }

        System.err.println(severity + ": " + error.getMessage() + "\t");
        System.err.println("Type : " + error.getType() + "\t" + "Related Data: "
                + error.getRelatedData() + "\t" + "Related Exception: "
                + error.getRelatedException() );

        return fail;
    }
}
