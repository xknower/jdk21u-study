package com.sun.hotspot.igv.view.actions;

import com.sun.hotspot.igv.graph.Figure;

/**
 *
 * @author Thomas Wuerthinger
 */
public final class ExpandSuccessorsAction extends ExpandAdjacentAction {

    @Override
    public void performAction() {
        expandFigures(Figure::getSuccessors);
    }

    @Override
    public String getName() {
        return "Expand Below";
    }
}
