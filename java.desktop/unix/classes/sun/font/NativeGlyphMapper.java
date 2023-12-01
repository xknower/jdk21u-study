package sun.font;

import java.awt.FontFormatException;
import java.awt.font.FontRenderContext;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Locale;

/*
 * This needs work to distinguish between XMap's translation from unicode
 * to the encoding used to access the X font, and whether a particular
 * code point is in the font.
 * ie a GlyphMapper ought to be able to say if a code point maps to a glyph
 * IN THIS FONT, not just in this encoding.
 * Because of the current lack of distinction the NativeGlyphMapper and
 * XMap classes could be merged, however its cleaner to make them separate
 * classes so we can build caches for a particular font.
 */
public class NativeGlyphMapper extends CharToGlyphMapper {

    NativeFont font;
    XMap xmapper;
    int numGlyphs;

    NativeGlyphMapper(NativeFont f) {
        font = f;
        xmapper = XMap.getXMapper(font.encoding);
        numGlyphs = f.getNumGlyphs();
        missingGlyph = 0;
    }

    public int getNumGlyphs() {
        return numGlyphs;
    }

    public int charToGlyph(char unicode) {
        if (unicode >= xmapper.convertedGlyphs.length) {
            return 0;
        } else {
            return xmapper.convertedGlyphs[unicode];
        }
    }

    public int charToGlyph(int unicode) {
        if (unicode >= xmapper.convertedGlyphs.length) {
            return 0;
        } else {
            return xmapper.convertedGlyphs[unicode];
        }
    }

    public void charsToGlyphs(int count, char[] unicodes, int[] glyphs) {
        for (int i=0; i<count; i++) {
            char code = unicodes[i];
            if (code >= xmapper.convertedGlyphs.length) {
                glyphs[i] = 0;
            } else {
                glyphs[i] = xmapper.convertedGlyphs[code];
            }
        }
    }

    public boolean charsToGlyphsNS(int count, char[] unicodes, int[] glyphs) {
        charsToGlyphs(count, unicodes, glyphs);
        return false;
    }

    public void charsToGlyphs(int count, int[] unicodes, int[] glyphs) {
        for (int i=0; i<count; i++) {
            char code = (char)unicodes[i];
            if (code >= xmapper.convertedGlyphs.length) {
                glyphs[i] = 0;
            } else {
                glyphs[i] = xmapper.convertedGlyphs[code];
            }
        }
    }

}
