package com.sun.beans.editors;

/**
 * Abstract Property editor for a java builtin number types.
 *
 */

import java.beans.*;

public abstract class NumberEditor extends PropertyEditorSupport {

    public String getJavaInitializationString() {
        Object value = getValue();
        return (value != null)
                ? value.toString()
                : "null";
    }

}
