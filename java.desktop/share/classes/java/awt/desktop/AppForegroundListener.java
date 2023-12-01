package java.awt.desktop;

/**
 * Implementors are notified when the app becomes the foreground app and when it
 * is no longer the foreground app. This notification is useful for hiding and
 * showing transient UI like palette windows which should be hidden when the app
 * is in the background.
 *
 * @since 9
 */
public interface AppForegroundListener extends SystemEventListener {

    /**
     * Called when the app becomes the foreground app.
     *
     * @param  e event
     */
    public void appRaisedToForeground(AppForegroundEvent e);

    /**
     * Called when the app becomes the background app and another app becomes
     * the foreground app.
     *
     * @param  e event
     */
    public void appMovedToBackground(AppForegroundEvent e);
}
