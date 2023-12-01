package com.sun.hotspot.igv.filterwindow.actions;

import com.sun.hotspot.igv.filter.Filter;
import com.sun.hotspot.igv.filterwindow.FilterTopComponent;
import javax.swing.Action;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CookieAction;

/**
 *
 * @author Thomas Wuerthinger
 */
public final class MoveFilterUpAction extends CookieAction {

    @Override
    protected void performAction(Node[] activatedNodes) {
        for (Node n : activatedNodes) {
            Filter c = n.getLookup().lookup(Filter.class);
            FilterTopComponent.findInstance().getAllFiltersOrdered().moveFilterUp(c);
        }
    }

    @Override
    protected int mode() {
        return CookieAction.MODE_EXACTLY_ONE;
    }

    public MoveFilterUpAction() {
        putValue(Action.SHORT_DESCRIPTION, "Move selected filter upwards");
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(MoveFilterUpAction.class, "CTL_MoveFilterUpAction");
    }

    @Override
    protected Class[] cookieClasses() {
        return new Class[]{
            Filter.class
        };
    }

    @Override
    protected String iconResource() {
        return "com/sun/hotspot/igv/filterwindow/images/up.png";
    }

    @Override
    protected void initialize() {
        super.initialize();
        putValue("noIconInMenu", Boolean.TRUE);
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
