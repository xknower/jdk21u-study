package com.sun.hotspot.igv.view.actions;

import com.sun.hotspot.igv.view.EditorTopComponent;
import java.awt.Container;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

/**
 * @author David Kaspar
 * @author Peter Hofer
 */
public class CustomizablePanAction extends WidgetAction.LockedAdapter {
    private boolean enabled = true;
    private boolean active = true;

    private Scene scene;
    private JScrollPane scrollPane;
    private Point lastLocation;
    private Rectangle rectangle;
    private final int modifiersEx;

    public CustomizablePanAction(int modifiersEx) {
        this.modifiersEx = modifiersEx;
    }

    @Override
    protected boolean isLocked() {
        return scrollPane != null;
    }

    private void lock() {
        scrollPane = findScrollPane(scene.getView());
    }

    private void unlock() {
        scrollPane = null;
    }

    public void setEnabled(boolean enabled) {
        if (this.enabled != enabled) {
            if (this.isLocked()) {
                throw new IllegalStateException();
            }
            this.enabled = enabled;
        }
    }

    @Override
    public State mouseEntered(Widget widget, WidgetMouseEvent event) {
        active = true;
        return super.mouseEntered(widget, event);
    }

    @Override
    public State mouseExited(Widget widget, WidgetMouseEvent event) {
        active = false;
        return super.mouseExited(widget, event);
    }

    @Override
    public State mousePressed(Widget widget, WidgetMouseEvent event) {
        EditorTopComponent editor = EditorTopComponent.getActive();
        if (editor != null) {
            editor.requestActive();
        }
        if (!this.isLocked() && active && enabled && (event.getModifiersEx() == modifiersEx)) {
            scene = widget.getScene();
            this.lock();
            if (this.isLocked()) {
                lastLocation = scene.convertSceneToView(widget.convertLocalToScene(event.getPoint()));
                SwingUtilities.convertPointToScreen(lastLocation, scene.getView());
                rectangle = scene.getView().getVisibleRect();
            }
        }
        return super.mousePressed(widget, event);
    }

    private JScrollPane findScrollPane(JComponent component) {
        for (;;) {
            if (component == null) {
                return null;
            }
            if (component instanceof JScrollPane) {
                return ((JScrollPane) component);
            }
            Container parent = component.getParent();
            if (!(parent instanceof JComponent)) {
                return null;
            }
            component = (JComponent) parent;
        }
    }

    @Override
    public State mouseReleased(Widget widget, WidgetMouseEvent event) {
        if (this.isLocked() && scene == widget.getScene()) {
            this.unlock();
        }
        return super.mouseReleased(widget, event);
    }

    @Override
    public State mouseDragged(Widget widget, WidgetMouseEvent event) {
        if (active && this.isLocked() && scene == widget.getScene()) {
            Point newLocation = event.getPoint();
            newLocation = scene.convertSceneToView(widget.convertLocalToScene(newLocation));
            SwingUtilities.convertPointToScreen(newLocation, scene.getView());
            rectangle.x += lastLocation.x - newLocation.x;
            rectangle.y += lastLocation.y - newLocation.y;
            scene.getView().scrollRectToVisible(rectangle);
            lastLocation = newLocation;
            return State.createLocked(widget, this);
        }
        return State.REJECTED;
    }
}
