package com.sun.hotspot.igv.view.actions;

import com.sun.hotspot.igv.view.DiagramViewModel;
import java.util.HashSet;
import java.util.Set;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;


/**
 * @author Thomas Wuerthinger
 */
@ActionID(category = "View", id = "com.sun.hotspot.igv.view.actions.HideAction")
@ActionRegistration(displayName = "#CTL_HideAction")
@ActionReferences({
        @ActionReference(path = "Menu/View", position = 400),
        @ActionReference(path = "Shortcuts", name = "D-H")
})
@Messages({
        "CTL_HideAction=Hide nodes",
        "HINT_HideAction=Hide selected nodes"
})
public final class HideAction extends ModelAwareAction {

    @Override
    protected String iconResource() {
        return "com/sun/hotspot/igv/view/images/hide.gif"; // NOI18N
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(HideAction.class, "CTL_HideAction");
    }

    @Override
    protected String getDescription() {
        return NbBundle.getMessage(HideAction.class, "HINT_HideAction");
    }

    @Override
    public void performAction(DiagramViewModel model) {
        Set<Integer> selectedNodes = model.getSelectedNodes();
        HashSet<Integer> nodes = new HashSet<>(model.getHiddenNodes());
        nodes.addAll(selectedNodes);
        model.setHiddenNodes(nodes);
    }

    @Override
    public boolean isEnabled(DiagramViewModel model) {
        return model != null && !model.getSelectedNodes().isEmpty();
    }
}
