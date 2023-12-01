package com.sun.hotspot.igv.view.actions;

import com.sun.hotspot.igv.graph.Figure;
import com.sun.hotspot.igv.view.EditorTopComponent;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import org.openide.util.HelpCtx;
import org.openide.util.actions.CallableSystemAction;


abstract public class ExpandAdjacentAction extends CallableSystemAction {

    protected void expandFigures(Function<Figure, List<Figure>> getAdjacentFigures) {
        EditorTopComponent editor = EditorTopComponent.getActive();
        if (editor != null) {
            Set<Figure> selectedFigured = editor.getModel().getSelectedFigures();
            Set<Figure> expandedFigures = new HashSet<>(selectedFigured);
            for (Figure selectedFigure : selectedFigured) {
                expandedFigures.addAll(getAdjacentFigures.apply(selectedFigure));
            }
            editor.getModel().showFigures(expandedFigures);
        }
    }

    abstract public void performAction();

    abstract public String getName();

    @Override
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    protected boolean asynchronous() {
        return false;
    }
}
