package com.sun.java.swing.plaf.windows;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicEditorPaneUI;
import javax.swing.text.Caret;

/**
 * Windows rendition of the component.
 */
public class WindowsEditorPaneUI extends BasicEditorPaneUI
{

    /**
     * Creates a UI for a JEditorPane.
     *
     * @param c the configurable text component
     * @return the UI
     */
    public static ComponentUI createUI(JComponent c) {
        return new WindowsEditorPaneUI();
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
