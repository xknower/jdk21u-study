package javax.imageio.plugins.bmp;

import java.util.Locale;
import javax.imageio.ImageWriteParam;

import com.sun.imageio.plugins.bmp.BMPConstants;
import com.sun.imageio.plugins.bmp.BMPCompressionTypes;

/**
 * A subclass of {@code ImageWriteParam} for encoding images in
 * the BMP format.
 *
 * <p> This class allows for the specification of various parameters
 * while writing a BMP format image file.  By default, the data layout
 * is bottom-up, such that the pixels are stored in bottom-up order,
 * the first scanline being stored last.
 *
 * <p>The particular compression scheme to be used can be specified by using
 * the {@code setCompressionType()} method with the appropriate type
 * string.  The compression scheme specified will be honored if and only if it
 * is compatible with the type of image being written. If the specified
 * compression scheme is not compatible with the type of image being written
 * then the {@code IOException} will be thrown by the BMP image writer.
 * If the compression type is not set explicitly then {@code getCompressionType()}
 * will return {@code null}. In this case the BMP image writer will select
 * a compression type that supports encoding of the given image without loss
 * of the color resolution.
 * <p>The compression type strings and the image type(s) each supports are
 * listed in the following
 * table:
 *
 * <table class="striped">
 * <caption>Compression Types</caption>
 * <thead>
 *   <tr>
 *     <th scope="col">Type String
 *     <th scope="col">Description
 *     <th scope="col">Image Types
 * </thead>
 * <tbody>
 *   <tr>
 *     <th scope="row">BI_RGB
 *     <td>Uncompressed RLE
 *     <td>{@literal <= } 8-bits/sample
 *   <tr>
 *     <th scope="row">BI_RLE8
 *     <td>8-bit Run Length Encoding
 *     <td>{@literal <=} 8-bits/sample
 *   <tr>
 *     <th scope="row">BI_RLE4
 *     <td>4-bit Run Length Encoding
 *     <td>{@literal <=} 4-bits/sample
 *   <tr>
 *     <th scope="row">BI_BITFIELDS
 *     <td>Packed data
 *     <td>16 or 32 bits/sample
 * </tbody>
 * </table>
 */
public class BMPImageWriteParam extends ImageWriteParam {

    private boolean topDown = false;

    /**
     * Constructs a {@code BMPImageWriteParam} set to use a given
     * {@code Locale} and with default values for all parameters.
     *
     * @param locale a {@code Locale} to be used to localize
     * compression type names and quality descriptions, or
     * {@code null}.
     */
    public BMPImageWriteParam(Locale locale) {
        super(locale);

        // Set compression types ("BI_RGB" denotes uncompressed).
        compressionTypes = BMPCompressionTypes.getCompressionTypes();

        // Set compression flag.
        canWriteCompressed = true;
        compressionMode = MODE_COPY_FROM_METADATA;
        compressionType = compressionTypes[BMPConstants.BI_RGB];
    }

    /**
     * Constructs an {@code BMPImageWriteParam} object with default
     * values for all parameters and a {@code null Locale}.
     */
    public BMPImageWriteParam() {
        this(null);
    }

    /**
     * If set, the data will be written out in a top-down manner, the first
     * scanline being written first.
     *
     * @param topDown whether the data are written in top-down order.
     */
    public void setTopDown(boolean topDown) {
        this.topDown = topDown;
    }

    /**
     * Returns the value of the {@code topDown} parameter.
     * The default is {@code false}.
     *
     * @return whether the data are written in top-down order.
     */
    public boolean isTopDown() {
        return topDown;
    }
}
