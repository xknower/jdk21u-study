package com.sun.beans.editors;

/**
 * Property editor for a java builtin "short" type.
 *
 */

import java.beans.*;

public class ShortEditor extends NumberEditor {

    public String getJavaInitializationString() {
        Object value = getValue();
        return (value != null)
                ? "((short)" + value + ")"
                : "null";
    }

    public void setAsText(String text) throws IllegalArgumentException {
        setValue((text == null) ? null : Short.decode(text));
    }

}
