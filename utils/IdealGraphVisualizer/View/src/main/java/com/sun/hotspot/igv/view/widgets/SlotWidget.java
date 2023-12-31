package com.sun.hotspot.igv.view.widgets;

import com.sun.hotspot.igv.graph.Diagram;
import com.sun.hotspot.igv.graph.Figure;
import com.sun.hotspot.igv.graph.OutputSlot;
import com.sun.hotspot.igv.graph.Slot;
import com.sun.hotspot.igv.util.DoubleClickHandler;
import com.sun.hotspot.igv.view.DiagramScene;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.model.ObjectState;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author Thomas Wuerthinger
 */
public abstract class SlotWidget extends Widget implements DoubleClickHandler {

    private Slot slot;
    private FigureWidget figureWidget;
    protected static double TEXT_ZOOM_FACTOR = 0.9;
    protected static double ZOOM_FACTOR = 0.6;
    private DiagramScene diagramScene;

    public SlotWidget(Slot slot, DiagramScene scene, Widget parent, FigureWidget fw) {
        super(scene);
        this.diagramScene = scene;
        this.slot = slot;
        figureWidget = fw;
        if (slot.hasSourceNodes()) {
            this.setToolTipText("<HTML>" + slot.getToolTipText() + "</HTML>");
        }
        // No clipping, to let input slots draw gap markers outside their bounds.
        this.setCheckClipping(false);
        parent.addChild(this);

        Point p = slot.getRelativePosition();
        p.x -= this.calculateClientArea().width / 2;
        p.y += yOffset();
        this.setPreferredLocation(p);
    }

    @Override
    protected void notifyStateChanged(ObjectState previousState, ObjectState state) {
        super.notifyStateChanged(previousState, state);
        repaint();
    }

    public Slot getSlot() {
        return slot;
    }

    public FigureWidget getFigureWidget() {
        return figureWidget;
    }

    @Override
    protected void paintWidget() {

        if (getScene().getZoomFactor() < ZOOM_FACTOR) {
            return;
        }

        Graphics2D g = this.getGraphics();
        // g.setColor(Color.DARK_GRAY);
        int w = this.getBounds().width;
        int h = this.getBounds().height;

        if (getSlot().hasSourceNodes()) {
            final int SMALLER = 0;
            g.setColor(getSlot().getColor());

            int FONT_OFFSET = 2;

            int s = h - SMALLER;
            int rectW = s;

            Font font = Diagram.SLOT_FONT;
            if (this.getState().isSelected()) {
                font = font.deriveFont(Font.BOLD);
                g.setStroke(new BasicStroke(1.5f));
            } else {
                g.setStroke(new BasicStroke(1f));
            }

            if (getSlot().shouldShowName()) {
                g.setFont(font);
                Rectangle2D r1 = g.getFontMetrics().getStringBounds(getSlot().getShortName(), g);
                rectW = (int) r1.getWidth() + FONT_OFFSET * 2;
            }
            g.fillRect(w / 2 - rectW / 2, 0, rectW - 1, s - 1);

            if (this.getState().isHighlighted()) {
                g.setColor(Color.BLUE);
            } else {
                g.setColor(Color.BLACK);
            }
            g.drawRect(w / 2 - rectW / 2, 0, rectW - 1, s - 1);

            if (getSlot().shouldShowName() && getScene().getZoomFactor() >= TEXT_ZOOM_FACTOR) {
                Rectangle2D r1 = g.getFontMetrics().getStringBounds(getSlot().getShortName(), g);
                g.drawString(getSlot().getShortName(), (int) (w - r1.getWidth()) / 2, g.getFontMetrics().getAscent() - 1);//(int) (r1.getHeight()));
            }

        } else {

            if (this.getSlot().getConnections().isEmpty() &&
                !getFigureWidget().getFigure().getDiagram().isCFG()) {
                if (this.getState().isHighlighted()) {
                    g.setColor(Color.BLUE);
                } else {
                    g.setColor(Color.BLACK);
                }
                int r = 2;
                if (slot instanceof OutputSlot) {
                    g.fillOval(w / 2 - r, Figure.SLOT_WIDTH - Figure.SLOT_START - r, 2 * r, 2 * r);
                } else {
                    g.fillOval(w / 2 - r, Figure.SLOT_START - r, 2 * r, 2 * r);
                }
            } else {
                // Do not paint a slot with connections.
            }
        }
    }

    @Override
    protected Rectangle calculateClientArea() {
        return new Rectangle(0, 0, slot.getWidth(), Figure.SLOT_WIDTH);
    }

    protected abstract int calculateSlotWidth();

    protected abstract int yOffset();

    protected int calculateWidth(int count) {
        return getFigureWidget().getFigure().getWidth() / count;
    }

    @Override
    public void handleDoubleClick(Widget w, WidgetAction.WidgetMouseEvent e) {
        Set<Integer> hiddenNodes = new HashSet<>(diagramScene.getModel().getHiddenNodes());
        if (diagramScene.isAllVisible()) {
            hiddenNodes = new HashSet<>(diagramScene.getModel().getGroup().getAllNodes());
        }

        boolean progress = false;
        for (Figure f : diagramScene.getModel().getDiagram().getFigures()) {
            for (Slot s : f.getSlots()) {
                if (!Collections.disjoint(s.getSource().getSourceNodesAsSet(), slot.getSource().getSourceNodesAsSet())) {
                    progress = true;
                    hiddenNodes.remove(f.getInputNode().getId());
                }
            }
        }

        if (progress) {
            this.diagramScene.getModel().setHiddenNodes(hiddenNodes);
        }
    }
}
