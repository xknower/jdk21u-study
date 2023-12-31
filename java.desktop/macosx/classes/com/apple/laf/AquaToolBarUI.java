package com.apple.laf;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.BasicToolBarUI;

import com.apple.laf.AquaUtils.*;

public class AquaToolBarUI extends BasicToolBarUI implements SwingConstants {
    private static final RecyclableSingleton<ToolBarBorder> toolBarBorder = new RecyclableSingletonFromDefaultConstructor<ToolBarBorder>(ToolBarBorder.class);
    public static Border getToolBarBorder() {
        return toolBarBorder.get();
    }

    public static ComponentUI createUI(final JComponent c) {
        return new AquaToolBarUI();
    }

    protected void setBorderToNonRollover(final Component c) { }
    protected void setBorderToNormal(final Component c) { }
    protected void setBorderToRollover(final Component c) { }

    protected RootPaneContainer createFloatingWindow(final JToolBar toolbar) {
        final RootPaneContainer window = super.createFloatingWindow(toolbar);
        window.getRootPane().putClientProperty("Window.style", "small");
        return window;
    }

    /* ToolBarBorder and drag-off handle, based loosely on MetalBumps */
    @SuppressWarnings("serial") // Superclass is not serializable across versions
    static class ToolBarBorder extends AbstractBorder implements UIResource, javax.swing.SwingConstants {
        protected void fillHandle(final Graphics g, final int x1, final int y1, final int x2, final int y2, final boolean horizontal) {
            g.setColor(UIManager.getColor("ToolBar.borderHandleColor"));
            if (horizontal) {
                final int h = y2 - y1 - 2;
                g.fillRect(x1 + 2, y1 + 1, 1, h);
                g.fillRect(x1 + 5, y1 + 1, 1, h);
            } else {
                final int w = x2 - x1 - 2;
                g.fillRect(x1 + 1, y1 + 2, w, 1);
                g.fillRect(x1 + 1, y1 + 5, w, 1);
            }
        }

        public void paintBorder(final java.awt.Component c, final Graphics g, int x, int y, final int w, final int h) {
            g.translate(x, y);

            if (c.isOpaque()) {
                AquaUtils.fillRect(g, c, c.getBackground(), 0, 0, w - 1, h - 1);
            }

            final Color oldColor = g.getColor();

            final JToolBar jtb = (JToolBar)c;
            final ComponentOrientation orient = jtb.getComponentOrientation();
            final boolean horizontal = jtb.getOrientation() == SwingConstants.HORIZONTAL;

            if (jtb.isFloatable()) {
                if (horizontal) {
                    if (orient.isLeftToRight()) {
                        fillHandle(g, 2, 2, 10, h - 2, true);
                    } else {
                        fillHandle(g, w - 10, 2, w - 2, h - 2, true);
                    }
                } else {
                    fillHandle(g, 2, 2, w - 2, 10, false);
                }
            }

            g.setColor(oldColor);

            g.translate(-x, -y);
        }

        public Insets getBorderInsets(final java.awt.Component c) {
            final Insets borderInsets = new Insets(5, 5, 5, 5);
            return getBorderInsets(c, borderInsets);
        }

        public Insets getBorderInsets(final java.awt.Component c, final Insets borderInsets) {
            borderInsets.left = 4;
            borderInsets.right = 4;
            borderInsets.top = 2;
            borderInsets.bottom = 2;

            if (((JToolBar)c).isFloatable()) {
                if (((JToolBar)c).getOrientation() == HORIZONTAL) {
                    borderInsets.left = 12;
                    // We don't have to adjust for right-to-left
                } else { // vertical
                    borderInsets.top = 12;
                }
            }

            final Insets margin = ((JToolBar)c).getMargin();

            if (margin != null) {
                borderInsets.left += margin.left;
                borderInsets.top += margin.top;
                borderInsets.right += margin.right;
                borderInsets.bottom += margin.bottom;
            }

            return borderInsets;
        }

        public boolean isBorderOpaque() {
            return true;
        }
    }

    @Override
    public final void update(final Graphics g, final JComponent c) {
        if (c.isOpaque()) {
            AquaUtils.fillRect(g, c);
        }
        paint(g, c);
    }
}
