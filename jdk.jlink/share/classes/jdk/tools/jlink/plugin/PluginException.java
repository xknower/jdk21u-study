package jdk.tools.jlink.plugin;

/**
 * An unchecked exception thrown by jlink plugin API for unrecoverable
 * conditions.
 */
public final class PluginException extends RuntimeException {

    private static final long serialVersionUID = 7117982019443100395L;

    public PluginException() {

    }

    public PluginException(Throwable ex) {
        super(ex);
    }

    public PluginException(String msg) {
        super(msg);
    }

    public PluginException(String msg, Throwable thr) {
        super(msg, thr);
    }
}
