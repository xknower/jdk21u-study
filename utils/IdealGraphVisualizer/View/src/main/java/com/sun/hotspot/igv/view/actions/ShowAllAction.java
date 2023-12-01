package com.sun.hotspot.igv.view.actions;

import com.sun.hotspot.igv.view.DiagramViewModel;
import java.util.HashSet;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;


/**
 * @author Thomas Wuerthinger
 */
@ActionID(category = "View", id = "com.sun.hotspot.igv.view.actions.ShowAllAction")
@ActionRegistration(displayName = "#CTL_ShowAllAction")
@ActionReferences({
        @ActionReference(path = "Menu/View", position = 300),
        @ActionReference(path = "Shortcuts", name = "D-A")
})
@Messages({
        "CTL_ShowAllAction=Show all",
        "HINT_ShowAllAction=Show all nodes"
})
public final class ShowAllAction extends ModelAwareAction {

    @Override
    protected String iconResource() {
        return "com/sun/hotspot/igv/view/images/expand.gif"; // NOI18N
    }

    @Override
    protected String getDescription() {
        return NbBundle.getMessage(ShowAllAction.class, "HINT_ShowAllAction");
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(ShowAllAction.class, "CTL_ShowAllAction");
    }

    @Override
    public void performAction(DiagramViewModel model) {
        model.setHiddenNodes(new HashSet<>());
    }

    @Override
    public boolean isEnabled(DiagramViewModel model) {
        return model != null && !model.getHiddenNodes().isEmpty();
    }
}
