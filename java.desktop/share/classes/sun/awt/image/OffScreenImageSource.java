package sun.awt.image;

import java.util.Hashtable;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageProducer;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;

public class OffScreenImageSource implements ImageProducer {
    BufferedImage image;
    int width;
    int height;
    Hashtable<?, ?> properties;

    public OffScreenImageSource(BufferedImage image,
                                Hashtable<?, ?> properties) {
        this.image = image;
        if (properties != null) {
            this.properties = properties;
        } else {
            this.properties = new Hashtable<String, Object>();
        }
        width  = image.getWidth();
        height = image.getHeight();
    }

    public OffScreenImageSource(BufferedImage image) {
        this(image, null);
    }

    // We can only have one consumer since we immediately return the data...
    private ImageConsumer theConsumer;

    public synchronized void addConsumer(ImageConsumer ic) {
        theConsumer = ic;
        produce();
    }

    public synchronized boolean isConsumer(ImageConsumer ic) {
        return (ic == theConsumer);
    }

    public synchronized void removeConsumer(ImageConsumer ic) {
        if (theConsumer == ic) {
            theConsumer = null;
        }
    }

    public void startProduction(ImageConsumer ic) {
        addConsumer(ic);
    }

    public void requestTopDownLeftRightResend(ImageConsumer ic) {
    }

    private void sendPixels() {
        ColorModel cm = image.getColorModel();
        WritableRaster raster = image.getRaster();
        int numDataElements = raster.getNumDataElements();
        int dataType = raster.getDataBuffer().getDataType();
        int[] scanline = new int[width*numDataElements];
        boolean needToCvt = true;

        if (cm instanceof IndexColorModel) {
            byte[] pixels = new byte[width];
            theConsumer.setColorModel(cm);

            if (raster instanceof ByteComponentRaster) {
                needToCvt = false;
                for (int y=0; y < height; y++) {
                    raster.getDataElements(0, y, width, 1, pixels);
                    theConsumer.setPixels(0, y, width, 1, cm, pixels, 0,
                                          width);
                }
            }
            else if (raster instanceof BytePackedRaster) {
                needToCvt = false;
                // Binary image.  Need to unpack it
                for (int y=0; y < height; y++) {
                    raster.getPixels(0, y, width, 1, scanline);
                    for (int x=0; x < width; x++) {
                        pixels[x] = (byte) scanline[x];
                    }
                    theConsumer.setPixels(0, y, width, 1, cm, pixels, 0,
                                          width);
                }
            }
            else if (dataType == DataBuffer.TYPE_SHORT ||
                     dataType == DataBuffer.TYPE_INT)
            {
                // Probably a short or int "GRAY" image
                needToCvt = false;
                for (int y=0; y < height; y++) {
                    raster.getPixels(0, y, width, 1, scanline);
                    theConsumer.setPixels(0, y, width, 1, cm, scanline, 0,
                                          width);
                }
            }
        }
        else if (cm instanceof DirectColorModel) {
            theConsumer.setColorModel(cm);
            needToCvt = false;
            switch (dataType) {
            case DataBuffer.TYPE_INT:
                for (int y=0; y < height; y++) {
                    raster.getDataElements(0, y, width, 1, scanline);
                    theConsumer.setPixels(0, y, width, 1, cm, scanline, 0,
                                          width);
                }
                break;
            case DataBuffer.TYPE_BYTE:
                byte[] bscanline = new byte[width];
                for (int y=0; y < height; y++) {
                    raster.getDataElements(0, y, width, 1, bscanline);
                    for (int x=0; x < width; x++) {
                        scanline[x] = bscanline[x]&0xff;
                    }
                    theConsumer.setPixels(0, y, width, 1, cm, scanline, 0,
                                          width);
                }
                break;
            case DataBuffer.TYPE_USHORT:
                short[] sscanline = new short[width];
                for (int y=0; y < height; y++) {
                    raster.getDataElements(0, y, width, 1, sscanline);
                    for (int x=0; x < width; x++) {
                        scanline[x] = sscanline[x]&0xffff;
                    }
                    theConsumer.setPixels(0, y, width, 1, cm, scanline, 0,
                                          width);
                }
                break;
            default:
                needToCvt = true;
            }
        }

        if (needToCvt) {
            // REMIND: Need to add other types of CMs here
            ColorModel newcm = ColorModel.getRGBdefault();
            theConsumer.setColorModel(newcm);

            for (int y=0; y < height; y++) {
                for (int x=0; x < width; x++) {
                    scanline[x] = image.getRGB(x, y);
                }
                theConsumer.setPixels(0, y, width, 1, newcm, scanline, 0,
                                      width);
            }
        }
    }

    private void produce() {
        try {
            theConsumer.setDimensions(image.getWidth(), image.getHeight());
            theConsumer.setProperties(properties);
            sendPixels();
            theConsumer.imageComplete(ImageConsumer.SINGLEFRAMEDONE);

            // If 'theconsumer' has not unregistered itself after previous call
            if (theConsumer != null) {
                try {
                    theConsumer.imageComplete(ImageConsumer.STATICIMAGEDONE);
                } catch (RuntimeException e) {
                    // We did not previously call this method here and
                    // some image consumer filters were not prepared for it to be
                    // called at this time. We allow them to have runtime issues
                    // for this one call only without triggering the IMAGEERROR
                    // condition below.
                    e.printStackTrace();
                }
            }
        } catch (NullPointerException e) {
            // If theConsumer is null and we throw a NPE when interacting with it:
            // That's OK. That is an expected use case that can happen when an
            // ImageConsumer detaches itself from this ImageProducer mid-production.

            if (theConsumer != null) {
                e.printStackTrace();
                theConsumer.imageComplete(ImageConsumer.IMAGEERROR);
            }
        }
    }
}
