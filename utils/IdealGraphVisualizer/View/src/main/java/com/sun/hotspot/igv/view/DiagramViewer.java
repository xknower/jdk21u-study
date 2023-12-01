package com.sun.hotspot.igv.view;

import com.sun.hotspot.igv.data.ChangedEvent;
import com.sun.hotspot.igv.data.InputNode;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Collection;
import javax.swing.JComponent;
import org.openide.awt.UndoRedo;
import org.openide.util.Lookup;

/**
 *
 * @author Thomas Wuerthinger
 */
public interface DiagramViewer {

    enum InteractionMode {
        SELECTION,
        PANNING,
    }

    DiagramViewModel getModel();

    void paint(Graphics2D generator);

    Lookup getLookup();

    JComponent createSatelliteView();

    Component getComponent();

    double getZoomMinFactor();

    double getZoomMaxFactor();

    void zoomOut(Point zoomCenter, double speed);

    void zoomIn(Point zoomCenter, double speed);

    void setZoomPercentage(int percentage);

    int getZoomPercentage();

    ChangedEvent<DiagramViewer> getZoomChangedEvent();

    void resetUndoRedoManager();

    UndoRedo getUndoRedo();

    void componentHidden();

    void componentShowing();

    void centerSelectedFigures();

    void addSelectedNodes(Collection<InputNode> nodes, boolean showIfHidden);

    void clearSelectedNodes();

    void setInteractionMode(InteractionMode mode);

    Rectangle getBounds();

    JComponent getView();
}
