package sun.java2d;

import java.awt.Composite;
import java.awt.CompositeContext;
import java.awt.AlphaComposite;
import java.awt.image.ColorModel;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import sun.awt.image.BufImgSurfaceData;
import sun.java2d.loops.XORComposite;
import sun.java2d.loops.CompositeType;
import sun.java2d.loops.Blit;

public class SunCompositeContext implements CompositeContext {
    ColorModel srcCM;
    ColorModel dstCM;
    Composite composite;
    CompositeType comptype;

    public SunCompositeContext(AlphaComposite ac,
                               ColorModel s, ColorModel d)
    {
        if (s == null) {
            throw new NullPointerException("Source color model cannot be null");
        }
        if (d == null) {
            throw new NullPointerException("Destination color model cannot be null");
        }
        srcCM = s;
        dstCM = d;
        this.composite = ac;
        this.comptype = CompositeType.forAlphaComposite(ac);
    }

    public SunCompositeContext(XORComposite xc,
                               ColorModel s, ColorModel d)
    {
        if (s == null) {
            throw new NullPointerException("Source color model cannot be null");
        }
        if (d == null) {
            throw new NullPointerException("Destination color model cannot be null");
        }
        srcCM = s;
        dstCM = d;
        this.composite = xc;
        this.comptype = CompositeType.Xor;
    }

    /**
     * Release resources allocated for context.
     */
    public void dispose() {
    }

    /**
     * This method composes the two source tiles
     * and places the result in the destination tile. Note that
     * the destination can be the same object as either
     * the first or second source.
     * @param src1 The first source tile for the compositing operation.
     * @param src2 The second source tile for the compositing operation.
     * @param dst The tile where the result of the operation is stored.
     */
    public void compose(Raster src1, Raster src2, WritableRaster dst) {
        WritableRaster src;
        int w;
        int h;

        if (src2 != dst) {
            dst.setDataElements(0, 0, src2);
        }

        // REMIND: We should be able to create a SurfaceData from just
        // a non-writable Raster and a ColorModel.  Since we need to
        // create a SurfaceData from a BufferedImage then we need to
        // make a WritableRaster since it is needed to construct a
        // BufferedImage.
        if (src1 instanceof WritableRaster) {
            src = (WritableRaster) src1;
        } else {
            src = src1.createCompatibleWritableRaster();
            src.setDataElements(0, 0, src1);
        }

        w = Math.min(src.getWidth(), src2.getWidth());
        h = Math.min(src.getHeight(), src2.getHeight());

        BufferedImage srcImg = new BufferedImage(srcCM, src,
                                                 srcCM.isAlphaPremultiplied(),
                                                 null);
        BufferedImage dstImg = new BufferedImage(dstCM, dst,
                                                 dstCM.isAlphaPremultiplied(),
                                                 null);

        SurfaceData srcData = BufImgSurfaceData.createData(srcImg);
        SurfaceData dstData = BufImgSurfaceData.createData(dstImg);
        Blit blit = Blit.getFromCache(srcData.getSurfaceType(),
                                      comptype,
                                      dstData.getSurfaceType());
        blit.Blit(srcData, dstData, composite, null, 0, 0, 0, 0, w, h);
    }
}
