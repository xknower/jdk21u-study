package jdk.tools.jlink.internal;

import java.security.BasicPermission;

/**
 * The permission required to use jlink API. The permission target_name is
 * "jlink". e.g.: permission jdk.tools.jlink.plugins.JlinkPermission "jlink";
 *
 */
public final class JlinkPermission extends BasicPermission {

    private static final long serialVersionUID = -3687912306077727801L;

    public JlinkPermission(String name) {
        super(name);
    }

}
