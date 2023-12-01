package com.apple.eawt;

import java.awt.*;
import java.beans.SimpleBeanInfo;

/**
 * This class is used by JavaBeans tools and should not be used directly by applications.
 */
public class ApplicationBeanInfo extends SimpleBeanInfo {
    public Image getIcon(final int iconKind) {
        return Toolkit.getDefaultToolkit().getImage("NSImage://NSGenericApplication");
    }
}
