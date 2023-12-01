package sun.font;

import sun.awt.X11FontManager;

final class PlatformFontInfo {

    /**
     * The method is only to be called via the
     * {@code FontManagerFactory.getInstance()} factory method.
     */
    static FontManager createFontManager() {
        return new X11FontManager();
    }
}
