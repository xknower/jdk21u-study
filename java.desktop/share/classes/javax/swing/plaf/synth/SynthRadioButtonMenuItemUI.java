package javax.swing.plaf.synth;

import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.*;

/**
 * Provides the Synth L&amp;F UI delegate for
 * {@link javax.swing.JRadioButtonMenuItem}.
 *
 * @author Georges Saab
 * @author David Karlton
 * @since 1.7
 */
public class SynthRadioButtonMenuItemUI extends SynthMenuItemUI {

    /**
     *
     * Constructs a {@code SynthRadioButtonMenuItemUI}.
     */
    public SynthRadioButtonMenuItemUI() {}

    /**
     * Creates a new UI object for the given component.
     *
     * @param b component to create UI object for
     * @return the UI object
     */
    public static ComponentUI createUI(JComponent b) {
        return new SynthRadioButtonMenuItemUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPropertyPrefix() {
        return "RadioButtonMenuItem";
    }

    @Override
    void paintBackground(SynthContext context, Graphics g, JComponent c) {
        context.getPainter().paintRadioButtonMenuItemBackground(context, g, 0,
                             0, c.getWidth(), c.getHeight());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintBorder(SynthContext context, Graphics g, int x,
                            int y, int w, int h) {
        context.getPainter().paintRadioButtonMenuItemBorder(context, g, x,
                                                            y, w, h);
    }
}
