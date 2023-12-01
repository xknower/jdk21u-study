package com.sun.hotspot.igv.view.actions;

import com.sun.hotspot.igv.view.EditorTopComponent;
import java.beans.PropertyChangeEvent;

public class EnableCFGLayoutAction extends EnableLayoutAction {

    public EnableCFGLayoutAction(EditorTopComponent etc) {
        super(etc);
    }

    @Override
    protected String iconResource() {
        return "com/sun/hotspot/igv/view/images/cfg.png";
    }

    @Override
    protected String getDescription() {
        return "Show control-flow graph";
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        editor.getModel().setShowCFG(this.isSelected());
    }
}
