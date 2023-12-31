package sun.java2d.pipe;

import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.font.TextLayout;

import sun.java2d.SunGraphics2D;
import sun.java2d.SurfaceData;
import sun.font.GlyphList;
import sun.java2d.loops.FontInfo;

/**
 * A delegate pipe of SG2D for drawing text.
 */

public abstract class GlyphListPipe implements TextPipe {

    public void drawString(SunGraphics2D sg2d, String s,
                           double x, double y)
    {
        FontInfo info = sg2d.getFontInfo();
        if (info.nonInvertibleTx) {
            return;
        }
        if (info.pixelHeight > OutlineTextRenderer.THRESHHOLD) {
            SurfaceData.outlineTextRenderer.drawString(sg2d, s, x, y);
            return;
        }

        float devx, devy;
        if (sg2d.transformState >= SunGraphics2D.TRANSFORM_TRANSLATESCALE) {
            double[] origin = {x + info.originX, y + info.originY};
            sg2d.transform.transform(origin, 0, origin, 0, 1);
            devx = (float)origin[0];
            devy = (float)origin[1];
        } else {
            devx = (float)(x + info.originX + sg2d.transX);
            devy = (float)(y + info.originY + sg2d.transY);
        }
        /* setFromString returns false if shaping is needed, and we then back
         * off to a TextLayout. Such text may benefit slightly from a lower
         * overhead in this approach over the approach in previous releases.
         */
        GlyphList gl = GlyphList.getInstance();
        if (gl.setFromString(info, s, devx, devy)) {
            drawGlyphList(sg2d, gl);
            gl.dispose();
        } else {
            gl.dispose(); // release this asap.
            TextLayout tl = new TextLayout(s, sg2d.getFont(),
                                           sg2d.getFontRenderContext());
            tl.draw(sg2d, (float)x, (float)y);
        }
    }

    public void drawChars(SunGraphics2D sg2d,
                          char[] data, int offset, int length,
                          int ix, int iy)
    {
        FontInfo info = sg2d.getFontInfo();
        if (info.nonInvertibleTx) {
            return;
        }
        float x, y;
        if (info.pixelHeight > OutlineTextRenderer.THRESHHOLD) {
            SurfaceData.outlineTextRenderer.drawChars(
                                        sg2d, data, offset, length, ix, iy);
            return;
        }
        if (sg2d.transformState >= SunGraphics2D.TRANSFORM_TRANSLATESCALE) {
            double[] origin = {ix + info.originX, iy + info.originY};
            sg2d.transform.transform(origin, 0, origin, 0, 1);
            x = (float) origin[0];
            y = (float) origin[1];
        } else {
            x = ix + info.originX + sg2d.transX;
            y = iy + info.originY + sg2d.transY;
        }
        GlyphList gl = GlyphList.getInstance();
        if (gl.setFromChars(info, data, offset, length, x, y)) {
            drawGlyphList(sg2d, gl);
            gl.dispose();
        } else {
            gl.dispose(); // release this asap.
            TextLayout tl = new TextLayout(new String(data, offset, length),
                                           sg2d.getFont(),
                                           sg2d.getFontRenderContext());
            tl.draw(sg2d, ix, iy);

        }
    }

    public void drawGlyphVector(SunGraphics2D sg2d, GlyphVector gv,
                                float x, float y)
    {
        FontRenderContext frc = gv.getFontRenderContext();
        FontInfo info = sg2d.getGVFontInfo(gv.getFont(), frc);
        if (info.nonInvertibleTx) {
            return;
        }
        if (info.pixelHeight > OutlineTextRenderer.THRESHHOLD) {
            SurfaceData.outlineTextRenderer.drawGlyphVector(sg2d, gv, x, y);
            return;
        }
        if (sg2d.transformState >= SunGraphics2D.TRANSFORM_TRANSLATESCALE) {
            double[] origin = {x, y};
            sg2d.transform.transform(origin, 0, origin, 0, 1);
            x = (float) origin[0];
            y = (float) origin[1];
        } else {
            x += sg2d.transX; // don't use the glyph info origin, already in gv.
            y += sg2d.transY;
        }

        GlyphList gl = GlyphList.getInstance();
        gl.setFromGlyphVector(info, gv, x, y);
        drawGlyphList(sg2d, gl, info.aaHint);
        gl.dispose();
    }

    protected abstract void drawGlyphList(SunGraphics2D sg2d, GlyphList gl);

    protected void drawGlyphList(SunGraphics2D sg2d, GlyphList gl,
                                 int aaHint) {
        drawGlyphList(sg2d, gl);
    }
}
