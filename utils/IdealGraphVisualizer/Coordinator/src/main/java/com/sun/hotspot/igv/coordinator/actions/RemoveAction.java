package com.sun.hotspot.igv.coordinator.actions;

import com.sun.hotspot.igv.coordinator.FolderNode;
import javax.swing.Action;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.NodeAction;

/**
 *
 * @author Thomas Wuerthinger
 */
public final class RemoveAction extends NodeAction {

    @Override
    protected void performAction(Node[] activatedNodes) {
        for (Node n : activatedNodes) {
            RemoveCookie removeCookie = n.getCookie(RemoveCookie.class);
            if (removeCookie != null) {
                removeCookie.remove();
            }
        }
    }

    public RemoveAction() {
        putValue(Action.SHORT_DESCRIPTION, getName());
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(RemoveAction.class, "CTL_RemoveAction");
    }

    @Override
    protected String iconResource() {
        return "com/sun/hotspot/igv/coordinator/images/remove.png";
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
    protected boolean enable(Node[] nodes) {
        if (nodes.length > 0) {
            for (Node n : nodes) {
                if ((n instanceof FolderNode) && ((FolderNode) n).isRootNode()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
