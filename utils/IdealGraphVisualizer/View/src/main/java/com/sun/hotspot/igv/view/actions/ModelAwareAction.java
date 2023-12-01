package com.sun.hotspot.igv.view.actions;

import com.sun.hotspot.igv.util.ContextAction;
import com.sun.hotspot.igv.view.DiagramViewModel;
import javax.swing.Action;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;


abstract public class ModelAwareAction extends ContextAction<DiagramViewModel> {

    public ModelAwareAction() {
        putValue(Action.SMALL_ICON , ImageUtilities.loadImageIcon(iconResource(), true));
        putValue(Action.SHORT_DESCRIPTION, getDescription());
    }

    protected abstract String iconResource();

    protected abstract String getDescription();

    public abstract String getName();

    public abstract void performAction(DiagramViewModel model);

    public abstract boolean isEnabled(DiagramViewModel model);

    @Override
    public Class<DiagramViewModel> contextClass() {
        return DiagramViewModel.class;
    }

    @Override
    public void addContextListener(DiagramViewModel model) {
        model.getSelectedNodesChangedEvent().addListener(this);
        model.getDiagramChangedEvent().addListener(this);
        model.getGraphChangedEvent().addListener(this);
        model.getHiddenNodesChangedEvent().addListener(this);
    }

    @Override
    public void removeContextListener(DiagramViewModel model) {
        model.getSelectedNodesChangedEvent().removeListener(this);
        model.getDiagramChangedEvent().removeListener(this);
        model.getGraphChangedEvent().removeListener(this);
        model.getHiddenNodesChangedEvent().removeListener(this);
    }

    @Override
    public Action createContextAwareInstance(Lookup actionContext) {
        return this;
    }
}
