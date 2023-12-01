package com.sun.hotspot.igv.coordinator.actions;

import com.sun.hotspot.igv.coordinator.OutlineTopComponent;
import javax.swing.Action;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;
import org.openide.util.actions.CallableSystemAction;

/**
 *
 * @author Thomas Wuerthinger
 */

public final class SaveAllAction extends CallableSystemAction {

    @Override
    public void performAction() {
        final OutlineTopComponent component = OutlineTopComponent.findInstance();
        SaveAsAction.save(component.getDocument());
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(SaveAllAction.class, "CTL_SaveAllAction");
    }

    public SaveAllAction() {
        putValue(Action.SHORT_DESCRIPTION, "Save all groups to XML file...");
        // D is the Control key on most platforms, the Command (meta) key on Macintosh
        putValue(Action.ACCELERATOR_KEY, Utilities.stringToKey("D-S"));
    }

    @Override
    protected String iconResource() {
        return "com/sun/hotspot/igv/coordinator/images/saveall.gif";
    }

    @Override
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    protected boolean asynchronous() {
        return false;
    }
}
