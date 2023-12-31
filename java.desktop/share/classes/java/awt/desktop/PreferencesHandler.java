package java.awt.desktop;

/**
 * An implementor is notified when the app is asked to show its preferences UI.
 *
 * @see java.awt.Desktop#setPreferencesHandler(PreferencesHandler)
 * @since 9
 */
public interface PreferencesHandler {

    /**
     * Called when the app is asked to show its preferences UI.
     *
     * @param  e the request to show preferences
     */
    public void handlePreferences(PreferencesEvent e);
}
