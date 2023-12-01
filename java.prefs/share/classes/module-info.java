/**
 * Defines the Preferences API.
 *
 * @uses java.util.prefs.PreferencesFactory
 *
 * @moduleGraph
 * @since 9
 */
module java.prefs {
    requires java.xml;

    exports java.util.prefs;

    uses java.util.prefs.PreferencesFactory;
}
