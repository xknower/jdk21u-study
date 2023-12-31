package com.sun.imageio.plugins.tiff;

import java.io.EOFException;
import java.io.IOException;

public class TIFFNullDecompressor extends TIFFDecompressor {

    /**
     * Whether to read the active source region only.
     */
    private boolean isReadActiveOnly = false;

    /** The original value of {@code srcMinX}. */
    private int originalSrcMinX;

    /** The original value of {@code srcMinY}. */
    private int originalSrcMinY;

    /** The original value of {@code srcWidth}. */
    private int originalSrcWidth;

    /** The original value of {@code srcHeight}. */
    private int originalSrcHeight;

    public TIFFNullDecompressor() {}

    //
    // This approach to reading the active region is a not the best
    // as the original values of the entire source region are stored,
    // overwritten, and then restored. It would probably be better to
    // revise TIFFDecompressor such that this were not necessary, i.e.,
    // change beginDecoding() and decode() to use the active region values
    // when random access is easy and the entire region values otherwise.
    //
    public void beginDecoding() {
        // Determine number of bits per pixel.
        int bitsPerPixel = 0;
        for(int i = 0; i < bitsPerSample.length; i++) {
            bitsPerPixel += bitsPerSample[i];
        }

        // Can read active region only if row starts on a byte boundary.
        if((activeSrcMinX != srcMinX || activeSrcMinY != srcMinY ||
            activeSrcWidth != srcWidth || activeSrcHeight != srcHeight) &&
           ((activeSrcMinX - srcMinX)*bitsPerPixel) % 8 == 0) {
            // Set flag.
            isReadActiveOnly = true;

            // Cache original region.
            originalSrcMinX = srcMinX;
            originalSrcMinY = srcMinY;
            originalSrcWidth = srcWidth;
            originalSrcHeight = srcHeight;

            // Replace region with active region.
            srcMinX = activeSrcMinX;
            srcMinY = activeSrcMinY;
            srcWidth = activeSrcWidth;
            srcHeight = activeSrcHeight;
        } else {
            // Clear flag.
            isReadActiveOnly = false;
        }

        super.beginDecoding();
    }

    public void decode() throws IOException {
        super.decode();

        // Reset state.
        if(isReadActiveOnly) {
            // Restore original source region values.
            srcMinX = originalSrcMinX;
            srcMinY = originalSrcMinY;
            srcWidth = originalSrcWidth;
            srcHeight = originalSrcHeight;

            // Unset flag.
            isReadActiveOnly = false;
        }
    }

    public void decodeRaw(byte[] b,
                          int dstOffset,
                          int bitsPerPixel,
                          int scanlineStride) throws IOException {
        if(isReadActiveOnly) {
            // Read the active source region only.

            int activeBytesPerRow = (activeSrcWidth*bitsPerPixel + 7)/8;
            int totalBytesPerRow = (originalSrcWidth*bitsPerPixel + 7)/8;
            int bytesToSkipPerRow = totalBytesPerRow - activeBytesPerRow;

            //
            // Seek to the start of the active region:
            //
            // active offset = original offset +
            //                 number of bytes to start of first active row +
            //                 number of bytes to first active pixel within row
            //
            // Since the condition for reading from the active region only is
            //
            //     ((activeSrcMinX - srcMinX)*bitsPerPixel) % 8 == 0
            //
            // the bit offset to the first active pixel within the first
            // active row is a multiple of 8.
            //
            stream.seek(offset +
                        (activeSrcMinY - originalSrcMinY)*totalBytesPerRow +
                        ((activeSrcMinX - originalSrcMinX)*bitsPerPixel)/8);

            int lastRow = activeSrcHeight - 1;
            for (int y = 0; y < activeSrcHeight; y++) {
                stream.readFully(b, dstOffset, activeBytesPerRow);
                dstOffset += scanlineStride;

                // Skip unneeded bytes (row suffix + row prefix).
                if(y != lastRow) {
                    stream.skipBytes(bytesToSkipPerRow);
                }
            }
        } else {
            // Read the entire source region.
            stream.seek(offset);
            int bytesPerRow = (srcWidth*bitsPerPixel + 7)/8;
            if(bytesPerRow == scanlineStride) {
                stream.readFully(b, dstOffset, bytesPerRow*srcHeight);
            } else {
                for (int y = 0; y < srcHeight; y++) {
                    stream.readFully(b, dstOffset, bytesPerRow);
                    dstOffset += scanlineStride;
                }
            }
        }
    }
}
