package com.sun.imageio.plugins.tiff;

import javax.imageio.plugins.tiff.BaselineTIFFTagSet;
import javax.imageio.ImageWriteParam;

/**
 * Compressor for ZLib compression.
 */
public class TIFFZLibCompressor extends TIFFDeflater {
    public TIFFZLibCompressor(ImageWriteParam param, int predictor) {
        super("ZLib", BaselineTIFFTagSet.COMPRESSION_ZLIB, param, predictor);
    }
}
