package com.sun.hotspot.igv.util;

import org.netbeans.api.visual.action.WidgetAction.WidgetMouseEvent;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author Thomas Wuerthinger
 */
public interface DoubleClickHandler {

    void handleDoubleClick(Widget w, WidgetMouseEvent e);
}
