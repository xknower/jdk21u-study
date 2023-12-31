package com.sun.imageio.plugins.tiff;

import javax.imageio.plugins.tiff.BaselineTIFFTagSet;
import java.io.IOException;
import java.util.zip.Deflater;
import javax.imageio.ImageWriteParam;

/**
 * Compressor superclass for Deflate and ZLib compression.
 */
public class TIFFDeflater extends TIFFCompressor {

    Deflater deflater;
    int predictor;

    public TIFFDeflater(String compressionType,
                        int compressionTagValue,
                        ImageWriteParam param,
                        int predictorValue) {
        super(compressionType, compressionTagValue, true);

        this.predictor = predictorValue;

        // Set the deflate level.
        int deflateLevel;
        if(param != null &&
           param.getCompressionMode() == ImageWriteParam.MODE_EXPLICIT) {
            float quality = param.getCompressionQuality();
            deflateLevel = (int)(1 + 8*quality);
        } else {
            deflateLevel = Deflater.DEFAULT_COMPRESSION;
        }

        this.deflater = new Deflater(deflateLevel);
    }

    public int encode(byte[] b, int off,
                      int width, int height,
                      int[] bitsPerSample,
                      int scanlineStride) throws IOException {

        int inputSize = height*scanlineStride;
        int blocks = (inputSize + 32767)/32768;

        // Worst case for Zlib deflate is input size + 5 bytes per 32k
        // block, plus 6 header bytes
        byte[] compData = new byte[inputSize + 5*blocks + 6];

        int numCompressedBytes = 0;
        if(predictor == BaselineTIFFTagSet.PREDICTOR_HORIZONTAL_DIFFERENCING) {
            int samplesPerPixel = bitsPerSample.length;
            int bitsPerPixel = 0;
            for (int i = 0; i < samplesPerPixel; i++) {
                bitsPerPixel += bitsPerSample[i];
            }
            int bytesPerRow = (bitsPerPixel*width + 7)/8;
            byte[] rowBuf = new byte[bytesPerRow];

            int maxRow = height - 1;
            for(int i = 0; i < height; i++) {
                // Cannot modify b[] in place as it might be a data
                // array from the image being written so make a copy.
                System.arraycopy(b, off, rowBuf, 0, bytesPerRow);
                for(int j = bytesPerRow - 1; j >= samplesPerPixel; j--) {
                    rowBuf[j] -= rowBuf[j - samplesPerPixel];
                }

                deflater.setInput(rowBuf);
                if(i == maxRow) {
                    deflater.finish();
                }

                int numBytes = 0;
                while((numBytes = deflater.deflate(compData,
                                                   numCompressedBytes,
                                                   compData.length -
                                                   numCompressedBytes)) != 0) {
                    numCompressedBytes += numBytes;
                }

                off += scanlineStride;
            }
        } else {
            deflater.setInput(b, off, height*scanlineStride);
            deflater.finish();

            numCompressedBytes = deflater.deflate(compData);
        }

        deflater.reset();

        stream.write(compData, 0, numCompressedBytes);

        return numCompressedBytes;
    }
}
