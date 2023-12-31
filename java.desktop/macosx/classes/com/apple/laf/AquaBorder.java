package com.apple.laf;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.UIResource;
import javax.swing.text.JTextComponent;

import apple.laf.*;
import apple.laf.JRSUIConstants.*;

import com.apple.laf.AquaUtilControlSize.*;

public abstract class AquaBorder implements Border, UIResource {
    protected final AquaPainter<? extends JRSUIState> painter;
    protected final SizeDescriptor sizeDescriptor;
    protected SizeVariant sizeVariant;

    protected AquaBorder(final SizeDescriptor sizeDescriptor) {
        this.sizeDescriptor = sizeDescriptor;
        this.sizeVariant = sizeDescriptor.get(Size.REGULAR);
        this.painter = createPainter();
    }

    protected AquaPainter<? extends JRSUIState> createPainter() {
        final AquaPainter<JRSUIState> painter = AquaPainter.create(JRSUIState.getInstance());
        painter.state.set(AlignmentVertical.CENTER);
        painter.state.set(AlignmentHorizontal.CENTER);
        return painter;
    }

    protected AquaBorder(final AquaBorder other) {
        this.sizeDescriptor = other.sizeDescriptor;
        this.sizeVariant = other.sizeVariant;
        this.painter = AquaPainter.create(other.painter.state.derive());
        painter.state.set(AlignmentVertical.CENTER);
        painter.state.set(AlignmentHorizontal.CENTER);
    }

    protected void setSize(final Size size) {
        sizeVariant = sizeDescriptor.get(size);
        painter.state.set(size);
    }

    @Override
    public Insets getBorderInsets(final Component c) {
        return (Insets) sizeVariant.margins.clone();
    }

    protected AquaBorder deriveBorderForSize(final Size size) {
        try {
            final Class<? extends AquaBorder> clazz = getClass();
            final AquaBorder border = clazz.getConstructor(new Class<?>[] { clazz }).newInstance(new Object[] { this });
            border.setSize(size);
            return border;
        } catch (final Throwable e) {
            return null;
        }
    }

    public static void repaintBorder(final JComponent c) {
        JComponent borderedComponent = c;
        Border border = c.getBorder();
        if (border == null) {
            // See if it's inside a JScrollpane or something
            final Container p = c.getParent();
            if (p instanceof JViewport) {
                borderedComponent = (JComponent)p.getParent();
                if (borderedComponent != null) border = borderedComponent.getBorder();
            }
        }

        // If we really don't have a border, then bail
        // It might be a compound border with a ThemeBorder inside
        // The check for that case is tricky, so we just go ahead and repaint any border
        if (border == null || borderedComponent == null) return;

        final int width = borderedComponent.getWidth();
        final int height = borderedComponent.getHeight();
        final Insets i = borderedComponent.getInsets();

        borderedComponent.repaint(0, 0, width, i.top); // Top edge
        borderedComponent.repaint(0, 0, i.left, height); // Left edge
        borderedComponent.repaint(0, height - i.bottom, width, i.bottom); // Bottom edge
        borderedComponent.repaint(width - i.right, 0, i.right, height); // Right edge
    }

    // The JScrollPane doesn't let us know if its viewport view has focus
    protected boolean isFocused(final Component c) {
        // Being really paranoid in case this Component isn't a Swing component
        Component focusable = c;

        if (c instanceof JScrollPane) {
            final JViewport vp = ((JScrollPane)c).getViewport();
            if (vp != null) {
                focusable = vp.getView();
                // Lists, Tables & Trees get focus rings, TextAreas don't (JBuilder puts TextField border on TextAreas)
                if (focusable instanceof JTextComponent) return false;
            }
        } else if (focusable instanceof JTextComponent) {
            // non-editable text areas don't draw the focus ring
            if (!((javax.swing.text.JTextComponent)focusable).isEditable()) return false;
        }

        return (focusable instanceof JComponent jComponent) && jComponent.hasFocus();
    }

    @Override
    public boolean isBorderOpaque() { return false; }

    @Override
    public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int w, final int h) {
        painter.paint(g, c, x, y, w, h);
    }

    static class Default extends AquaBorder {
        Default() { super(new SizeDescriptor(new SizeVariant())); }
    }
}
