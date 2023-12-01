package com.sun.hotspot.igv.settings;

import java.util.prefs.Preferences;

/**
 *
 * @author Thomas Wuerthinger
 */
public class Settings {

    public static class DefaultView {
        public static final int SEA_OF_NODES = 0;
        public static final int CLUSTERED_SEA_OF_NODES = 1;
        public static final int CONTROL_FLOW_GRAPH = 2;
    }

    public static final String NODE_TEXT = "nodeText";
    public static final String NODE_TEXT_DEFAULT = "[idx] [name]";
    public static final String NODE_SHORT_TEXT = "nodeShortText";
    public static final String NODE_SHORT_TEXT_DEFAULT = "[idx] [name]";
    public static final String NODE_TINY_TEXT = "nodeTinyText";
    public static final String NODE_TINY_TEXT_DEFAULT = "[idx]";
    public static final String DEFAULT_VIEW = "defaultView";
    public static final int    DEFAULT_VIEW_DEFAULT = DefaultView.SEA_OF_NODES;
    public static final String PORT = "port";
    public static final String PORT_DEFAULT = "4444";
    public static final String DIRECTORY = "directory";
    public static final String DIRECTORY_DEFAULT = System.getProperty("user.dir");

    public static Preferences get() {
        return Preferences.userNodeForPackage(Settings.class);
    }
}
