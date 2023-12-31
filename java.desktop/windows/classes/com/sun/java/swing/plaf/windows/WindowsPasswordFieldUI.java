package com.sun.java.swing.plaf.windows;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPasswordFieldUI;
import javax.swing.text.Caret;

/**
 * Windows rendition of the component.
 */
public class WindowsPasswordFieldUI extends BasicPasswordFieldUI {

    /**
     * Creates a UI for a JPasswordField
     *
     * @param c the password field
     * @return the UI
     */
    public static ComponentUI createUI(JComponent c) {
        return new WindowsPasswordFieldUI();
    }


    /**
     * Creates the object to use for a caret.  By default an
     * instance of WindowsCaret is created.  This method
     * can be redefined to provide something else that implements
     * the InputPosition interface or a subclass of DefaultCaret.
     *
     * @return the caret object
     */
    protected Caret createCaret() {
        return new WindowsTextUI.WindowsCaret();
    }
}
