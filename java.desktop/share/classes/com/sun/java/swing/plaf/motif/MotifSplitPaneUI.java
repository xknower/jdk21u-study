package com.sun.java.swing.plaf.motif;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

/**
 * Motif rendition of a split pane.
 *
 * @author Jeff Dinkins
 */
public class MotifSplitPaneUI extends BasicSplitPaneUI
{
    public MotifSplitPaneUI() {
        super();
    }

    /**
      * Creates a new MotifSplitPaneUI instance
      */
    public static ComponentUI createUI(JComponent x) {
        return new MotifSplitPaneUI();
    }

    /**
      * Creates the default divider.
      */
    public BasicSplitPaneDivider createDefaultDivider() {
        return new MotifSplitPaneDivider(this);
    }

}
