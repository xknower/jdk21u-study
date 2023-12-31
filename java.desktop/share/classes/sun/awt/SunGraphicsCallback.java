package sun.awt;

import java.awt.*;

import sun.util.logging.PlatformLogger;

public abstract class SunGraphicsCallback {
    public static final int HEAVYWEIGHTS = 0x1;
    public static final int LIGHTWEIGHTS = 0x2;
    public static final int TWO_PASSES = 0x4;

    private static final PlatformLogger log = PlatformLogger.getLogger("sun.awt.SunGraphicsCallback");

    public abstract void run(Component comp, Graphics cg);

    protected void constrainGraphics(Graphics g, Rectangle bounds) {
        if (g instanceof ConstrainableGraphics) {
            ((ConstrainableGraphics)g).constrain(bounds.x, bounds.y, bounds.width, bounds.height);
        } else {
            g.translate(bounds.x, bounds.y);
        }
        g.clipRect(0, 0, bounds.width, bounds.height);
    }

    public final void runOneComponent(Component comp, Rectangle bounds,
                                      Graphics g, Shape clip,
                                      int weightFlags) {
        if (comp == null || !comp.isDisplayable() || !comp.isVisible()) {
            return;
        }
        boolean lightweight = comp.isLightweight();
        if ((lightweight && (weightFlags & LIGHTWEIGHTS) == 0) ||
            (!lightweight && (weightFlags & HEAVYWEIGHTS) == 0)) {
            return;
        }

        if (bounds == null) {
            bounds = comp.getBounds();
        }

        if (clip == null || clip.intersects(bounds)) {
            Graphics cg = g.create();
            try {
                constrainGraphics(cg, bounds);
                cg.setFont(comp.getFont());
                cg.setColor(comp.getForeground());
                if (cg instanceof Graphics2D) {
                    ((Graphics2D)cg).setBackground(comp.getBackground());
                }
                run(comp, cg);
            } finally {
                cg.dispose();
            }
        }
    }

    public final void runComponents(Component[] comps, Graphics g,
                                    int weightFlags) {
        int ncomponents = comps.length;
        Shape clip = g.getClip();

        if (log.isLoggable(PlatformLogger.Level.FINER) && (clip != null)) {
            Rectangle newrect = clip.getBounds();
            log.finer("x = " + newrect.x + ", y = " + newrect.y +
                      ", width = " + newrect.width +
                      ", height = " + newrect.height);
        }

        // A seriously sad hack--
        // Lightweight components always paint behind peered components,
        // even if they are at the top of the Z order. We emulate this
        // behavior by making two printing passes: the first for lightweights;
        // the second for heavyweights.
        //
        // ToDo(dpm): Either build a list of heavyweights during the
        // lightweight pass, or redesign the components array to keep
        // lightweights and heavyweights separate.
        if ((weightFlags & TWO_PASSES) != 0) {
            for (int i = ncomponents - 1; i >= 0; i--) {
                runOneComponent(comps[i], null, g, clip, LIGHTWEIGHTS);
            }
            for (int i = ncomponents - 1; i >= 0; i--) {
                runOneComponent(comps[i], null, g, clip, HEAVYWEIGHTS);
            }
        } else {
            for (int i = ncomponents - 1; i >= 0; i--) {
                runOneComponent(comps[i], null, g, clip, weightFlags);
            }
        }
    }

    public static final class PaintHeavyweightComponentsCallback
        extends SunGraphicsCallback
    {
        private static PaintHeavyweightComponentsCallback instance =
            new PaintHeavyweightComponentsCallback();

        private PaintHeavyweightComponentsCallback() {}
        public void run(Component comp, Graphics cg) {
            if (!comp.isLightweight()) {
                comp.paintAll(cg);
            } else if (comp instanceof Container) {
                runComponents(((Container)comp).getComponents(), cg,
                              LIGHTWEIGHTS | HEAVYWEIGHTS);
            }
        }
        public static PaintHeavyweightComponentsCallback getInstance() {
            return instance;
        }
    }
    public static final class PrintHeavyweightComponentsCallback
        extends SunGraphicsCallback
    {
        private static PrintHeavyweightComponentsCallback instance =
            new PrintHeavyweightComponentsCallback();

        private PrintHeavyweightComponentsCallback() {}
        public void run(Component comp, Graphics cg) {
            if (!comp.isLightweight()) {
                comp.printAll(cg);
            } else if (comp instanceof Container) {
                runComponents(((Container)comp).getComponents(), cg,
                              LIGHTWEIGHTS | HEAVYWEIGHTS);
            }
        }
        public static PrintHeavyweightComponentsCallback getInstance() {
            return instance;
        }
    }
}
