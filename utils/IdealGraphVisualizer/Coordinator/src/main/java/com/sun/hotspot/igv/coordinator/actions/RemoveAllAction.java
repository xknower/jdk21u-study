package com.sun.hotspot.igv.coordinator.actions;

import com.sun.hotspot.igv.coordinator.OutlineTopComponent;
import javax.swing.Action;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;

/**
 *
 * @author Thomas Wuerthinger
 */
public final class RemoveAllAction extends CallableSystemAction {

    @Override
    public String getName() {
        return NbBundle.getMessage(RemoveAllAction.class, "CTL_RemoveAllAction");
    }

    public RemoveAllAction() {
        putValue(Action.SHORT_DESCRIPTION, "Remove all graphs and groups");
    }

    @Override
    protected String iconResource() {
        return "com/sun/hotspot/igv/coordinator/images/removeall.png";
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
    public void performAction() {
        OutlineTopComponent.findInstance().clear();
    }
}
