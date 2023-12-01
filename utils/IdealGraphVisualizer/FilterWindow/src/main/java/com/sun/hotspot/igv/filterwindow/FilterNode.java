package com.sun.hotspot.igv.filterwindow;

import com.sun.hotspot.igv.data.ChangedListener;
import com.sun.hotspot.igv.filter.Filter;
import com.sun.hotspot.igv.filter.FilterChain;
import com.sun.hotspot.igv.filterwindow.actions.MoveFilterDownAction;
import com.sun.hotspot.igv.filterwindow.actions.MoveFilterUpAction;
import com.sun.hotspot.igv.filterwindow.actions.RemoveFilterAction;
import com.sun.hotspot.igv.util.PropertiesSheet;
import javax.swing.Action;
import org.openide.actions.OpenAction;
import org.openide.nodes.Children;
import org.openide.nodes.Sheet;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.Utilities;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 *
 * @author Thomas Wuerthinger
 */
public class FilterNode extends CheckNode implements ChangedListener<FilterTopComponent> {

    private final Filter filter;

    public FilterNode(Filter filter) {
        this(filter, new InstanceContent());
    }

    private FilterNode(Filter filter, InstanceContent content) {
        super(Children.LEAF, new AbstractLookup(content));
        content.add(filter);

        content.add(filter.getEditor());
        this.filter = filter;
        filter.getChangedEvent().addListener(source -> update());

        update();

        FilterTopComponent.findInstance().getFilterSettingsChangedEvent().addListener(this);
        changed(FilterTopComponent.findInstance());

        setShortDescription("Double-click to open filter");
    }

    private void update() {
        this.setDisplayName(filter.getName());
    }

    public Filter getFilter() {
        return filter;
    }

    @Override
    protected Sheet createSheet() {
        Sheet s = super.createSheet();
        PropertiesSheet.initializeSheet(getFilter().getProperties(), s);
        return s;
    }

    @Override
    public Action[] getActions(boolean b) {
        return new Action[]{(Action) OpenAction.findObject(OpenAction.class, true), (Action) MoveFilterUpAction.findObject(MoveFilterUpAction.class, true), (Action) MoveFilterDownAction.findObject(MoveFilterDownAction.class, true), (Action) RemoveFilterAction.findObject(RemoveFilterAction.class, true)};
    }

    @Override
    public Action getPreferredAction() {
        return OpenAction.get(OpenAction.class).createContextAwareInstance(Utilities.actionsGlobalContext());
    }

    @Override
    public void changed(FilterTopComponent source) {
        setSelected(source.getCurrentChain().containsFilter(filter));
    }
}
