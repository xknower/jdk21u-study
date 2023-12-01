package sun.java2d.xr;

import java.awt.*;
import java.awt.geom.*;

/**
 *  Management of mask used for some blit-types.
 *
 * @author Clemens Eisserer
 */

public class XRMaskImage {

    private static final int MASK_SCALE_FACTOR = 8;

    private static final int BLIT_MASK_SIZE = 8;

    Dimension blitMaskDimensions = new Dimension(BLIT_MASK_SIZE, BLIT_MASK_SIZE);
    int blitMaskPixmap;
    int blitMaskPicture;
    int lastMaskWidth = 0;
    int lastMaskHeight = 0;
    int lastEA = -1;
    AffineTransform lastMaskTransform;

    XRCompositeManager xrMgr;
    XRBackend con;

    public XRMaskImage(XRCompositeManager xrMgr, int parentDrawable) {
        this.xrMgr = xrMgr;
        this.con = xrMgr.getBackend();

        initBlitMask(parentDrawable, BLIT_MASK_SIZE, BLIT_MASK_SIZE);
    }


    /**
     * Prepares a mask used by a TransformedBlit, fills mask-contents and applies
     * transformation.
     */
    public int prepareBlitMask(XRSurfaceData dst, AffineTransform maskTX, int width,
            int height) {

        int maskWidth = Math.max(width / MASK_SCALE_FACTOR, 1);
        int maskHeight = Math.max(height / MASK_SCALE_FACTOR, 1);
        maskTX.scale(((double) width) / maskWidth, ((double) height) / maskHeight);

        try {
            maskTX.invert();
        } catch (NoninvertibleTransformException ex) {
            maskTX.setToIdentity();
        }

        ensureBlitMaskSize(maskWidth, maskHeight);

        if (lastMaskTransform == null || !lastMaskTransform.equals(maskTX)) {
                con.setPictureTransform(blitMaskPicture, maskTX);
                lastMaskTransform = maskTX;
        }

        int currentEA = xrMgr.getAlphaColor().getAlpha();
        if (lastMaskWidth != maskWidth || lastMaskHeight != maskHeight || lastEA != currentEA)  {
            //Only clear mask, if previous mask area is larger than new one, otherwise simple overpaint it
            if (lastMaskWidth > maskWidth || lastMaskHeight > maskHeight)  {
                con.renderRectangle(blitMaskPicture, XRUtils.PictOpClear, XRColor.NO_ALPHA, 0, 0, lastMaskWidth, lastMaskHeight);
            }

            con.renderRectangle(blitMaskPicture, XRUtils.PictOpSrc, xrMgr.getAlphaColor(), 0, 0, maskWidth, maskHeight);
            lastEA = currentEA;
        }

        lastMaskWidth = maskWidth;
        lastMaskHeight = maskHeight;

        return blitMaskPicture;
    }

    private void initBlitMask(int parentDrawable, int width, int height) {
        int newPM = con.createPixmap(parentDrawable, 8, width, height);
        int newPict = con.createPicture(newPM, XRUtils.PictStandardA8);

        /*Free old mask*/
        if (blitMaskPixmap != 0) {
            con.freePixmap(blitMaskPixmap);
            con.freePicture(blitMaskPicture);
        }

        blitMaskPixmap = newPM;
        blitMaskPicture = newPict;

        con.renderRectangle(blitMaskPicture, XRUtils.PictOpClear, XRColor.NO_ALPHA, 0, 0, width, height);

        blitMaskDimensions.width = width;
        blitMaskDimensions.height = height;
        lastMaskWidth = 0;
        lastMaskHeight = 0;
        lastMaskTransform = null;
    }

    private void ensureBlitMaskSize(int minSizeX, int minSizeY) {
        if (minSizeX > blitMaskDimensions.width || minSizeY > blitMaskDimensions.height) {
            int newWidth = Math.max(minSizeX, blitMaskDimensions.width);
            int newHeight = Math.max(minSizeY, blitMaskDimensions.height);
            initBlitMask(blitMaskPixmap, newWidth, newHeight);
        }
    }
}
