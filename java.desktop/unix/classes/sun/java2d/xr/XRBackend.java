package sun.java2d.xr;

/**
 * XRender pipeline backend interface.
 * Currently there are two different backends implemented:
 * - XRBackendJava: And experimental backend, generating protocol directly using java-code and xcb's socket handoff functionality.
 * - XRBackendNative: Native 1:1 binding with libX11.
 */

import java.awt.geom.*;
import java.util.*;

import sun.font.*;
import sun.java2d.pipe.*;

public interface XRBackend {

    public void freePicture(int picture);

    public void freePixmap(int pixmap);

    public int createPixmap(int drawable, int depth, int width, int height);

    public int createPicture(int drawable, int formatID);

    public long createGC(int drawable);

    public void freeGC(long gc); /* TODO: Use!! */

    public void copyArea(int src, int dst, long gc, int srcx, int srcy,
                         int width, int height, int dstx, int dsty);

    public void putMaskImage(int drawable, long gc, byte[] imageData,
                             int sx, int sy, int dx, int dy,
                             int width, int height, int maskOff,
                             int maskScan, float ea);

    public void setGCClipRectangles(long gc, Region clip);

    public void GCRectangles(int drawable, long gc, GrowableRectArray rects);

    public void setClipRectangles(int picture, Region clip);

    public void setGCExposures(long gc, boolean exposure);

    public void setGCForeground(long gc, int pixel);

    public void setPictureTransform(int picture, AffineTransform transform);

    public void setPictureRepeat(int picture, int repeat);

    public void setFilter(int picture, int filter);

    public void renderRectangle(int dst, byte op, XRColor color,
                                int x, int y, int width, int height);

    public void renderRectangles(int dst, byte op, XRColor color,
                                 GrowableRectArray rects);

    public void renderComposite(byte op, int src, int mask, int dst,
                                int srcX, int srcY, int maskX, int maskY,
                                int dstX, int dstY, int width, int height);

    public int XRenderCreateGlyphSet(int formatID);

    public void XRenderAddGlyphs(int glyphSet, GlyphList gl,
                                 List<XRGlyphCacheEntry> cacheEntries,
                                 byte[] pixelData);

    public void XRenderFreeGlyphs(int glyphSet, int[] gids);

    public void XRenderCompositeText(byte op, int src, int dst,
                                     int maskFormatID,
                                     int xSrc, int ySrc, int xDst, int yDst,
                                     int glyphset, GrowableEltArray elts);

    public int createRadialGradient(float centerX, float centerY,
                                    float innerRadius, float outerRadius,
                                    float[] fractions, int[] pixels,
                                    int repeat);

    public int createLinearGradient(Point2D p1, Point2D p2, float[] fractions,
                                    int[] pixels, int repeat);

    public void setGCMode(long gc, boolean copy);

}
