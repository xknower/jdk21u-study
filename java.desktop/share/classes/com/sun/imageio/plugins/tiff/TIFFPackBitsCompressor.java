package com.sun.imageio.plugins.tiff;

import javax.imageio.plugins.tiff.BaselineTIFFTagSet;
import java.io.IOException;

public class TIFFPackBitsCompressor extends TIFFCompressor {

    public TIFFPackBitsCompressor() {
        super("PackBits", BaselineTIFFTagSet.COMPRESSION_PACKBITS, true);
    }

    /**
     * Performs PackBits compression for a single buffer of data.
     * This should be called for each row of each tile. The returned
     * value is the offset into the output buffer after compression.
     */
    private static int packBits(byte[] input, int inOffset, int inCount,
                                byte[] output, int outOffset) {
        int inMax = inOffset + inCount - 1;
        int inMaxMinus1 = inMax - 1;

        while(inOffset <= inMax) {
            int run = 1;
            byte replicate = input[inOffset];
            while(run < 127 && inOffset < inMax &&
                  input[inOffset] == input[inOffset+1]) {
                run++;
                inOffset++;
            }
            if(run > 1) {
                inOffset++;
                output[outOffset++] = (byte)(-(run - 1));
                output[outOffset++] = replicate;
            }

            run = 0;
            int saveOffset = outOffset;
            while(run < 128 &&
                  ((inOffset < inMax &&
                    input[inOffset] != input[inOffset+1]) ||
                   (inOffset < inMaxMinus1 &&
                    input[inOffset] != input[inOffset+2]))) {
                run++;
                output[++outOffset] = input[inOffset++];
            }
            if(run > 0) {
                output[saveOffset] = (byte)(run - 1);
                outOffset++;
            }

            if(inOffset == inMax) {
                if(run > 0 && run < 128) {
                    output[saveOffset]++;
                    output[outOffset++] = input[inOffset++];
                } else {
                    output[outOffset++] = (byte)0;
                    output[outOffset++] = input[inOffset++];
                }
            }
        }

        return outOffset;
    }

    public int encode(byte[] b, int off,
                      int width, int height,
                      int[] bitsPerSample,
                      int scanlineStride) throws IOException {
        int bitsPerPixel = 0;
        for (int i = 0; i < bitsPerSample.length; i++) {
            bitsPerPixel += bitsPerSample[i];
        }
        int bytesPerRow = (bitsPerPixel*width + 7)/8;
        int bufSize = (bytesPerRow + (bytesPerRow + 127)/128);
        byte[] compData = new byte[bufSize];

        int bytesWritten = 0;

        for(int i = 0; i < height; i++) {
            int bytes = packBits(b, off, scanlineStride, compData, 0);
            off += scanlineStride;
            bytesWritten += bytes;
            stream.write(compData, 0, bytes);
        }

        return bytesWritten;
    }
}
