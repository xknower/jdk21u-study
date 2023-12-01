package sun.font;

final class PlatformFontInfo {

    /**
     * The method is only to be called via the
     * {@code FontManagerFactory.getInstance()} factory method.
     */
    static FontManager createFontManager() {
        return new CFontManager();
    }
}
