package com.sun.hotspot.igv.view.actions;

import com.sun.hotspot.igv.view.DiagramViewModel;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;


/**
 * @author Thomas Wuerthinger
 */
@ActionID(category = "View", id = "com.sun.hotspot.igv.view.actions.ExtractAction")
@ActionRegistration(displayName = "#CTL_ExtractAction")
@ActionReferences({
        @ActionReference(path = "Menu/View", position = 350),
        @ActionReference(path = "Shortcuts", name = "D-X")
})
@Messages({
        "CTL_ExtractAction=Extract action",
        "HINT_ExtractAction=Extract current set of selected nodes"
})
public final class ExtractAction extends ModelAwareAction {

    @Override
    protected String iconResource() {
        return "com/sun/hotspot/igv/view/images/extract.gif"; // NOI18N
    }

    @Override
    protected String getDescription() {
        return NbBundle.getMessage(ExtractAction.class, "HINT_ExtractAction");
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(ExtractAction.class, "CTL_ExtractAction");
    }

    @Override
    public void performAction(DiagramViewModel model) {
        model.showOnly(model.getSelectedNodes());
    }

    @Override
    public boolean isEnabled(DiagramViewModel model) {
        return model != null && !model.getSelectedNodes().isEmpty();
    }
}
