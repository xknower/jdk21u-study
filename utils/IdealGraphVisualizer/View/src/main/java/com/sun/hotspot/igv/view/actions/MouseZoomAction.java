package com.sun.hotspot.igv.view.actions;

import com.sun.hotspot.igv.view.DiagramScene;
import com.sun.hotspot.igv.view.DiagramViewer;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JComponent;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.widget.Widget;
import org.openide.util.Utilities;


public class MouseZoomAction extends WidgetAction.Adapter implements MouseWheelListener {

    public static final int PAN_INCREMENT = 32;

    private static final int MODIFIER = Utilities.isMac() ? InputEvent.META_DOWN_MASK : InputEvent.CTRL_DOWN_MASK;
    private final DiagramViewer scene;
    private int prev_n;

    public MouseZoomAction(DiagramScene scene) {
        this.prev_n = 0;
        this.scene = scene;
    }

    private boolean performPanning(int modifiersEx, int wheelRotation) {
        // If modifier key is not pressed, use wheel for panning
        JComponent view = scene.getView();
        Rectangle visibleRect = view.getVisibleRect();
        int amount = wheelRotation * PAN_INCREMENT;
        if (modifiersEx == 0) {
            visibleRect.y += amount;
        } else if (modifiersEx == InputEvent.SHIFT_DOWN_MASK) {
            visibleRect.x += amount;
        } else {
            return false;
        }
        view.scrollRectToVisible(visibleRect);
        return true;
    }

    private boolean performZooming(Point sceneMouseLocation, int n) {
        if (n > 0) {
            if (prev_n > 0) {
                scene.zoomOut(sceneMouseLocation, Math.pow(1.1, Math.abs(n)));
                return true;
            } else {
                prev_n = 1;
            }
        } else if (n < 0) {
            if (prev_n < 0) {
                scene.zoomIn(sceneMouseLocation, Math.pow(1.1, Math.abs(n)));
                return true;
            } else {
                prev_n = -1;
            }
        }
        return false;
    }

    /**
     * Perform mouse centered zooming
     */
    @Override
    public State mouseWheelMoved(Widget widget, WidgetMouseWheelEvent event) {
        if ((event.getModifiersEx() & MODIFIER) != MODIFIER) {
            // If modifier key is not pressed, use wheel for panning
            if (performPanning(event.getModifiersEx(), event.getWheelRotation())) {
                return State.CONSUMED;
            } else {
                return State.REJECTED;
            }
        }

        if (performZooming(widget.convertLocalToScene(event.getPoint()), event.getWheelRotation())) {
            return State.CONSUMED;
        } else {
            return State.REJECTED;
        }
    }

    /**
     * Perform scene centered zooming
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent event) {
        if ((event.getModifiersEx() & MODIFIER) != MODIFIER) {
            // If modifier key is not pressed, use wheel for panning
            performPanning(event.getModifiersEx(), event.getWheelRotation());
        } else {
            performZooming(null, event.getWheelRotation());
        }
    }
}
