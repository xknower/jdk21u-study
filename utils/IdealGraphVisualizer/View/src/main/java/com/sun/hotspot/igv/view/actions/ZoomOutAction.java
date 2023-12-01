package com.sun.hotspot.igv.view.actions;

import com.sun.hotspot.igv.view.EditorTopComponent;
import javax.swing.Action;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.HelpCtx;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;
import org.openide.util.actions.CallableSystemAction;

/**
 * @author Thomas Wuerthinger
 */
@ActionID(category = "View", id = "com.sun.hotspot.igv.view.actions.ZoomOutAction")
@ActionRegistration(displayName = "#CTL_ZoomOutAction")
@ActionReferences({
        @ActionReference(path = "Menu/View", position = 500),
        @ActionReference(path = "Shortcuts", name = "D-MINUS")
})
@Messages({
        "CTL_ZoomOutAction=Zoom out",
        "HINT_ZoomOutAction=Zoom out of the graph"
})
public final class ZoomOutAction extends CallableSystemAction {

    public ZoomOutAction() {
        putValue(Action.SHORT_DESCRIPTION, getDescription());
        putValue(Action.SMALL_ICON , ImageUtilities.loadImageIcon(iconResource(), true));
    }

    @Override
    public void performAction() {
        EditorTopComponent editor = EditorTopComponent.getActive();
        if (editor != null) {
            editor.zoomOut();
        }
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(ZoomOutAction.class, "CTL_ZoomOutAction");
    }

    private String getDescription() {
        return NbBundle.getMessage(ZoomOutAction.class, "HINT_ZoomOutAction");
    }

    @Override
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    protected boolean asynchronous() {
        return false;
    }

    @Override
    protected String iconResource() {
        return "com/sun/hotspot/igv/view/images/zoomOut.svg"; // NOI18N
    }
}
