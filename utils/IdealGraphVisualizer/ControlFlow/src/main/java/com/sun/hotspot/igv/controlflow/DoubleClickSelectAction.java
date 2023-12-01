package com.sun.hotspot.igv.controlflow;

import java.awt.Point;
import java.awt.event.MouseEvent;
import org.netbeans.api.visual.action.SelectProvider;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.widget.Widget;
import org.openide.util.Utilities;

/**
 * Selection action that acts on double-click only. Does not support aiming.
 *
 * @author Peter Hofer
 */
public class DoubleClickSelectAction extends WidgetAction.LockedAdapter {

    private final SelectProvider provider;

    public DoubleClickSelectAction(SelectProvider provider) {
        this.provider = provider;
    }

    protected int getModifierMask () {
        return Utilities.isMac() ? MouseEvent.META_DOWN_MASK : MouseEvent.CTRL_DOWN_MASK;
    }

    protected boolean isLocked() {
        return false;
    }

    @Override
    public State mousePressed(Widget widget, WidgetMouseEvent event) {
        if (event.getClickCount() >= 2 && (event.getButton() == MouseEvent.BUTTON1 || event.getButton() == MouseEvent.BUTTON2)) {
            boolean invert = (event.getModifiersEx() & getModifierMask()) != 0;
            Point point = event.getPoint();
            if (provider.isSelectionAllowed(widget, point, invert)) {
                provider.select(widget, point, invert);
                return State.CHAIN_ONLY;
            }
        }
        return State.REJECTED;
    }
}
