package com.sun.hotspot.igv.view.actions;

import com.sun.hotspot.igv.view.EditorTopComponent;
import java.beans.PropertyChangeEvent;

public class EnableSeaLayoutAction extends EnableLayoutAction {

    public EnableSeaLayoutAction(EditorTopComponent etc) {
        super(etc);
    }

    @Override
    protected String iconResource() {
        return "com/sun/hotspot/igv/view/images/sea.png";
    }

    @Override
    protected String getDescription() {
        return "Show sea of nodes";
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        editor.getModel().setShowSea(this.isSelected());
    }
}
