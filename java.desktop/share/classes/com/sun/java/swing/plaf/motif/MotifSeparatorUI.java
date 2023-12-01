package com.sun.java.swing.plaf.motif;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSeparatorUI;

/**
 * A Motif {@literal L&F} implementation of SeparatorUI.
 * This implementation is a "combined" view/controller.
 *
 * @author Georges Saab
 * @author Jeff Shapiro
 */

public class MotifSeparatorUI extends BasicSeparatorUI
{
    public static ComponentUI createUI( JComponent c )
    {
        return new MotifSeparatorUI();
    }

}
