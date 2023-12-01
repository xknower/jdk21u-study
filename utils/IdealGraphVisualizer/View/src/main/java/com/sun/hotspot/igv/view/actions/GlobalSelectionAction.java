package com.sun.hotspot.igv.view.actions;

import com.sun.hotspot.igv.view.EditorTopComponent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.HelpCtx;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;

@ActionID(category = "View", id = "com.sun.hotspot.igv.view.actions.GlobalSelectionAction")
@ActionRegistration(displayName = "#CTL_GlobalSelectionAction")
@ActionReferences({
        @ActionReference(path = "Menu/Options", position = 300),
        @ActionReference(path = "Shortcuts", name = "D-L")
})
@NbBundle.Messages({
        "CTL_GlobalSelectionAction=Link selection globally",
        "HINT_GlobalSelectionAction=Link node selection globally"
})
public final class GlobalSelectionAction extends CallableSystemAction {

    private boolean isSelected;

    public GlobalSelectionAction() {
        putValue(AbstractAction.SMALL_ICON, new ImageIcon(ImageUtilities.loadImage(iconResource())));
        putValue(Action.SHORT_DESCRIPTION, getDescription());
        putValue(SELECTED_KEY, false);
        isSelected = false;
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(GlobalSelectionAction.class, "CTL_GlobalSelectionAction");
    }

    @Override
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    public void performAction() {
        isSelected = !isSelected;
        putValue(SELECTED_KEY, isSelected);
        EditorTopComponent editor = EditorTopComponent.getActive();
        if (editor != null) {
            SwingUtilities.invokeLater(() -> editor.getModel().setGlobalSelection(isSelected, true));
        }
    }

    public boolean isSelected() {
        return isSelected;
    }

    private String getDescription() {
        return NbBundle.getMessage(GlobalSelectionAction.class, "HINT_GlobalSelectionAction");
    }

    @Override
    protected boolean asynchronous() {
        return false;
    }

    @Override
    public String iconResource() {
        return "com/sun/hotspot/igv/view/images/chain.png";
    }
}
