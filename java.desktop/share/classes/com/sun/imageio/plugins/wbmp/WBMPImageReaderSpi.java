package com.sun.imageio.plugins.wbmp;

import java.util.Locale;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.spi.ServiceRegistry;
import java.io.IOException;
import javax.imageio.ImageReader;
import javax.imageio.IIOException;

public class WBMPImageReaderSpi extends ImageReaderSpi {

    private static final int MAX_WBMP_WIDTH = 1024;
    private static final int MAX_WBMP_HEIGHT = 768;

    private static String [] writerSpiNames =
        {"com.sun.imageio.plugins.wbmp.WBMPImageWriterSpi"};
    private static String[] formatNames = {"wbmp", "WBMP"};
    private static String[] extensions = {"wbmp"};
    private static String[] mimeType = {"image/vnd.wap.wbmp"};

    private boolean registered = false;

    public WBMPImageReaderSpi() {
        super("Oracle Corporation",
              "1.0",
              formatNames,
              extensions,
              mimeType,
              "com.sun.imageio.plugins.wbmp.WBMPImageReader",
              new Class<?>[] { ImageInputStream.class },
              writerSpiNames,
              true,
              null, null, null, null,
              true,
              WBMPMetadata.nativeMetadataFormatName,
              "com.sun.imageio.plugins.wbmp.WBMPMetadataFormat",
              null, null);
    }

    public void onRegistration(ServiceRegistry registry,
                               Class<?> category) {
        if (registered) {
            return;
        }
        registered = true;
    }

    public String getDescription(Locale locale) {
        return "Standard WBMP Image Reader";
    }

    public boolean canDecodeInput(Object source) throws IOException {
        if (!(source instanceof ImageInputStream)) {
            return false;
        }

        ImageInputStream stream = (ImageInputStream)source;

        stream.mark();
        try {
            int type = stream.read();   // TypeField, or -1 if EOF
            int fixHeaderField = stream.read();
            // check WBMP "header"
            if (type != 0 || fixHeaderField != 0) {
                // while WBMP reader does not support ext WBMP headers
                return false;
            }

            int width = tryReadMultiByteInteger(stream);
            int height = tryReadMultiByteInteger(stream);
            // check image dimension
            if (width <= 0 || height <= 0) {
                return false;
            }

            long dataLength = stream.length();
            if (dataLength == -1) {
                // We can't verify that amount of data in the stream
                // corresponds to image dimension because we do not know
                // the length of the data stream.
                // Assuming that wbmp image are used for mobile devices,
                // let's introduce an upper limit for image dimension.
                // In case if exact amount of raster data is unknown,
                // let's reject images with dimension above the limit.
                return (width < MAX_WBMP_WIDTH) && (height < MAX_WBMP_HEIGHT);
            }

            dataLength -= stream.getStreamPosition();

            long scanSize = (width / 8) + ((width % 8) == 0 ? 0 : 1);

            return (dataLength == scanSize * height);
        } finally {
            stream.reset();
        }
    }

    /**
     * Reads a positive integer value encoded on a variable number of bytes,
     * but stops the reading on end-of-file (EOF) or on integer overflow.
     *
     * @param  stream  the image input stream to read.
     * @return the integer value, or -1 if EOF or integer overflow.
     */
    private static int tryReadMultiByteInteger(ImageInputStream stream)
        throws IOException {
        int value = stream.read();
        if (value < 0) {
            return -1;          // EOF
        }
        int result = value & 0x7f;
        while ((value & 0x80) == 0x80) {
            if ((result & 0xfe000000) != 0) {
                return -1;      // 7 highest bits already used
            }
            result <<= 7;
            value = stream.read();
            if (value < 0) {
                return -1;      // EOF
            }
            result |= (value & 0x7f);
        }
        return result;
    }

    public ImageReader createReaderInstance(Object extension)
        throws IIOException {
        return new WBMPImageReader(this);
    }
}
