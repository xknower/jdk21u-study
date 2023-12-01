package com.sun.hotspot.igv.filterwindow.actions;

import com.sun.hotspot.igv.filterwindow.FilterTopComponent;
import javax.swing.Action;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;

/**
 *
 * @author Thomas Wuerthinger
 */
public final class SaveFilterSettingsAction extends CallableSystemAction {

    @Override
    public void performAction() {
        FilterTopComponent.findInstance().addFilterSetting();
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(SaveFilterSettingsAction.class, "CTL_SaveFilterSettingsAction");
    }

    public SaveFilterSettingsAction() {
        putValue(Action.SHORT_DESCRIPTION, "Save filter configuration as profile...");
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
        return "com/sun/hotspot/igv/filterwindow/images/add.png";
    }
}
