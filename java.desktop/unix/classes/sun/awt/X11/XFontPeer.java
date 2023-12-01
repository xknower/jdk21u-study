package sun.awt.X11;

import sun.awt.PlatformFont;

final class XFontPeer extends PlatformFont {

    XFontPeer(final String name, final int style) {
        super(name, style);
    }

    protected char getMissingGlyphCharacter() {
        return '\u274F';
    }
}
