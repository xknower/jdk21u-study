package java.awt.image;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;
import java.awt.RenderingHints;

/**
 * This interface describes single-input/single-output
 * operations performed on Raster objects.  It is implemented by such
 * classes as AffineTransformOp, ConvolveOp, and LookupOp.  The Source
 * and Destination objects must contain the appropriate number
 * of bands for the particular classes implementing this interface.
 * Otherwise, an exception is thrown.  This interface cannot be used to
 * describe more sophisticated Ops such as ones that take multiple sources.
 * Each class implementing this interface will specify whether or not it
 * will allow an in-place filtering operation (i.e. source object equal
 * to the destination object).  Note that the restriction to single-input
 * operations means that the values of destination pixels prior to the
 * operation are not used as input to the filter operation.
 * @see AffineTransformOp
 * @see BandCombineOp
 * @see ColorConvertOp
 * @see ConvolveOp
 * @see LookupOp
 * @see RescaleOp
 */
public interface RasterOp {
    /**
     * Performs a single-input/single-output operation from a source Raster
     * to a destination Raster.  If the destination Raster is null, a
     * new Raster will be created.  The IllegalArgumentException may be thrown
     * if the source and/or destination Raster is incompatible with the types
     * of Rasters allowed by the class implementing this filter.
     * @param src the source {@code Raster}
     * @param dest the destination {@code WritableRaster}
     * @return a {@code WritableRaster} that represents the result of
     *         the filtering operation.
     */
    public WritableRaster filter(Raster src, WritableRaster dest);

    /**
     * Returns the bounding box of the filtered destination Raster.
     * The IllegalArgumentException may be thrown if the source Raster
     * is incompatible with the types of Rasters allowed
     * by the class implementing this filter.
     * @param src the source {@code Raster}
     * @return a {@code Rectangle2D} that is the bounding box of
     *         the {@code Raster} resulting from the filtering
     *         operation.
     */
    public Rectangle2D getBounds2D(Raster src);

    /**
     * Creates a zeroed destination Raster with the correct size and number of
     * bands.
     * The IllegalArgumentException may be thrown if the source Raster
     * is incompatible with the types of Rasters allowed
     * by the class implementing this filter.
     * @param src the source {@code Raster}
     * @return a {@code WritableRaster} that is compatible with
     *         {@code src}
     */
    public WritableRaster createCompatibleDestRaster(Raster src);

    /**
     * Returns the location of the destination point given a
     * point in the source Raster.  If dstPt is non-null, it
     * will be used to hold the return value.
     * @param srcPt the source {@code Point2D}
     * @param dstPt the destination {@code Point2D}
     * @return the location of the destination point.
     */
    public Point2D getPoint2D(Point2D srcPt, Point2D dstPt);

    /**
     * Returns the rendering hints for this RasterOp.  Returns
     * null if no hints have been set.
     * @return the {@code RenderingHints} object of this
     *         {@code RasterOp}.
     */
    public RenderingHints getRenderingHints();
}
