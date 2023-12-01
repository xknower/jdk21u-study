package java.util.prefs;

class MacOSXPreferencesFactory implements PreferencesFactory {
    @Override
    public Preferences userRoot() {
        return MacOSXPreferences.getUserRoot();
    }

    @Override
    public Preferences systemRoot() {
        return MacOSXPreferences.getSystemRoot();
    }
}
