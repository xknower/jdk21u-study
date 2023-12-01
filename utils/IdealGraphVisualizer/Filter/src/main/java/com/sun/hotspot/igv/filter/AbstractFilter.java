package com.sun.hotspot.igv.filter;

import com.sun.hotspot.igv.data.ChangedEvent;
import com.sun.hotspot.igv.data.Properties;
import com.sun.hotspot.igv.graph.Figure;
import org.openide.cookies.OpenCookie;

/**
 *
 * @author Thomas Wuerthinger
 */
public abstract class AbstractFilter implements Filter {

    private ChangedEvent<Filter> changedEvent;
    private Properties properties;

    public AbstractFilter() {
        changedEvent = new ChangedEvent<>(this);
        properties = new Properties();
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public OpenCookie getEditor() {
        return null;
    }

    @Override
    public ChangedEvent<Filter> getChangedEvent() {
        return changedEvent;
    }

    protected void fireChangedEvent() {
        changedEvent.fire();
    }

    protected static String getFirstMatchingProperty(Figure figure, String[] propertyNames) {
        for (String propertyName : propertyNames) {
            String s = figure.getProperties().resolveString(propertyName);
            if (s != null && !s.isEmpty()) {
                return s;
            }
        }
        return null;
    }
}
