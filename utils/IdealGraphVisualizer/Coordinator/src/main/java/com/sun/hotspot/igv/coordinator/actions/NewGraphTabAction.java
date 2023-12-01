package com.sun.hotspot.igv.coordinator.actions;

import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.actions.CookieAction;

/**
 *
 * @author Thomas Wuerthinger
 */
public final class NewGraphTabAction extends CookieAction {

    @Override
    protected void performAction(Node[] activatedNodes) {
        NewGraphTabCookie c = activatedNodes[0].getCookie(NewGraphTabCookie.class);
        assert c != null;
        c.openNewTab();
    }

    @Override
    protected int mode() {
        return CookieAction.MODE_EXACTLY_ONE;
    }

    @Override
    protected boolean enable(Node[] activatedNodes) {
        boolean b = super.enable(activatedNodes);
        if (b) {
            assert activatedNodes.length == 1;
            NewGraphTabCookie c = activatedNodes[0].getCookie(NewGraphTabCookie.class);
            assert c != null;
            return c.isActive();
        }

        return false;
    }

    @Override
    public String getName() {
        return "Open in new tab";
    }

    @Override
    protected Class<?>[] cookieClasses() {
        return new Class<?>[]{
                NewGraphTabCookie.class
        };
    }

    @Override
    protected String iconResource() {
        return "com/sun/hotspot/igv/coordinator/images/graph.png";
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

