package sun.java2d.loops;

import sun.font.GlyphList;
import sun.java2d.SunGraphics2D;
import sun.java2d.SurfaceData;
import sun.java2d.pipe.Region;
import sun.java2d.loops.GraphicsPrimitiveMgr.GeneralPrimitives;

/**
 *   DrawGlyphList - loops for SolidTextRenderer pipe.
 *   1) draw solid color text onto destination surface
 *   2) must accept output area [x, y, dx, dy]
 *      from within the surface description data for clip rect
 */
public class DrawGlyphList extends GraphicsPrimitive {

    public static final String methodSignature = "DrawGlyphList(...)".toString();

    public static final int primTypeID = makePrimTypeID();

    public static DrawGlyphList locate(SurfaceType srctype,
                                   CompositeType comptype,
                                   SurfaceType dsttype)
    {
        return (DrawGlyphList)
            GraphicsPrimitiveMgr.locate(primTypeID,
                                        srctype, comptype, dsttype);
    }

    protected DrawGlyphList(SurfaceType srctype,
                         CompositeType comptype,
                         SurfaceType dsttype)
    {
        super(methodSignature, primTypeID, srctype, comptype, dsttype);
    }

    public DrawGlyphList(long pNativePrim,
                         SurfaceType srctype,
                         CompositeType comptype,
                         SurfaceType dsttype)
    {
        super(pNativePrim, methodSignature, primTypeID, srctype, comptype, dsttype);
    }


    public native void DrawGlyphList(SunGraphics2D sg2d, SurfaceData dest,
                                     GlyphList srcData,
                                     int fromGlyph, int toGlyph);

    // This instance is used only for lookup.
    static {
        GeneralPrimitives.register(
                                new DrawGlyphList(null, null, null));
    }

    protected GraphicsPrimitive makePrimitive(SurfaceType srctype,
                                              CompositeType comptype,
                                              SurfaceType dsttype) {
        return new General(srctype, comptype, dsttype);
    }

    private static class General extends DrawGlyphList {
        MaskFill maskop;

        public General(SurfaceType srctype,
                       CompositeType comptype,
                       SurfaceType dsttype)
        {
            super(srctype, comptype, dsttype);
            maskop = MaskFill.locate(srctype, comptype, dsttype);
        }

        public void DrawGlyphList(SunGraphics2D sg2d, SurfaceData dest,
                                  GlyphList gl, int fromGlyph, int toGlyph) {

            Region clip = sg2d.getCompClip();
            int cx1 = clip.getLoX();
            int cy1 = clip.getLoY();
            int cx2 = clip.getHiX();
            int cy2 = clip.getHiY();
            for (int i = fromGlyph; i < toGlyph; i++) {
                gl.setGlyphIndex(i);
                int[] metrics = gl.getMetrics();
                int gx1 = metrics[0];
                int gy1 = metrics[1];
                int w = metrics[2];
                int gx2 = gx1 + w;
                int gy2 = gy1 + metrics[3];
                int off = 0;
                if (gx1 < cx1) {
                    off = cx1 - gx1;
                    gx1 = cx1;
                }
                if (gy1 < cy1) {
                    off += (cy1 - gy1) * w;
                    gy1 = cy1;
                }
                if (gx2 > cx2) gx2 = cx2;
                if (gy2 > cy2) gy2 = cy2;
                if (gx2 > gx1 && gy2 > gy1) {
                    byte[] alpha = gl.getGrayBits();
                    maskop.MaskFill(sg2d, dest, sg2d.composite,
                                    gx1, gy1, gx2 - gx1, gy2 - gy1,
                                    alpha, off, w);
                }
            }
        }
    }

    public GraphicsPrimitive traceWrap() {
        return new TraceDrawGlyphList(this);
    }

    private static class TraceDrawGlyphList extends DrawGlyphList {
        DrawGlyphList target;

        public TraceDrawGlyphList(DrawGlyphList target) {
            super(target.getSourceType(),
                  target.getCompositeType(),
                  target.getDestType());
            this.target = target;
        }

        public GraphicsPrimitive traceWrap() {
            return this;
        }

        public void DrawGlyphList(SunGraphics2D sg2d, SurfaceData dest,
                                  GlyphList glyphs, int fromGlyph, int toGlyph)
        {
            tracePrimitive(target);
            target.DrawGlyphList(sg2d, dest, glyphs, fromGlyph, toGlyph);
        }
    }
}
