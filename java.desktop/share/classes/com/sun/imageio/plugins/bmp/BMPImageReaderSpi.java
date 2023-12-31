package com.sun.imageio.plugins.bmp;

import java.util.Locale;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.spi.ServiceRegistry;
import java.io.IOException;
import javax.imageio.ImageReader;
import javax.imageio.IIOException;
import com.sun.imageio.plugins.common.ReaderUtil;

public class BMPImageReaderSpi extends ImageReaderSpi {

    private static String [] writerSpiNames =
        {"com.sun.imageio.plugins.bmp.BMPImageWriterSpi"};
    private static String[] formatNames = {"bmp", "BMP"};
    private static String[] extensions = {"bmp"};
    private static String[] mimeType = {"image/bmp"};

    private boolean registered = false;

    public BMPImageReaderSpi() {
        super("Oracle Corporation",
              "1.0",
              formatNames,
              extensions,
              mimeType,
              "com.sun.imageio.plugins.bmp.BMPImageReader",
              new Class<?>[] { ImageInputStream.class },
              writerSpiNames,
              false,
              null, null, null, null,
              true,
              BMPMetadata.nativeMetadataFormatName,
              "com.sun.imageio.plugins.bmp.BMPMetadataFormat",
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
        return "Standard BMP Image Reader";
    }

    public boolean canDecodeInput(Object source) throws IOException {
        if (!(source instanceof ImageInputStream)) {
            return false;
        }

        ImageInputStream stream = (ImageInputStream)source;
        byte[] b = new byte[2];
        stream.mark();
        boolean full = ReaderUtil.tryReadFully(stream, b);
        stream.reset();

        return full && (b[0] == 0x42) && (b[1] == 0x4d);
    }

    public ImageReader createReaderInstance(Object extension)
        throws IIOException {
        return new BMPImageReader(this);
    }
}
