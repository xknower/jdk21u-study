package com.sun.imageio.plugins.tiff;

import javax.imageio.plugins.tiff.BaselineTIFFTagSet;
import javax.imageio.plugins.tiff.TIFFField;

public class TIFFYCbCrColorConverter extends TIFFColorConverter {

    private static final float CODING_RANGE_Y = 255.0f;
    private static final float CODING_RANGE_CB_CR = 127.0f;

    private float lumaRed = 0.299f;
    private float lumaGreen = 0.587f;
    private float lumaBlue = 0.114f;

    private float referenceBlackY = 0.0f;
    private float referenceWhiteY = 255.0f;

    private float referenceBlackCb = 128.0f;
    private float referenceWhiteCb = 255.0f;

    private float referenceBlackCr = 128.0f;
    private float referenceWhiteCr = 255.0f;

    public TIFFYCbCrColorConverter(TIFFImageMetadata metadata) {
        TIFFImageMetadata tmetadata = metadata;

        TIFFField f =
           tmetadata.getTIFFField(BaselineTIFFTagSet.TAG_Y_CB_CR_COEFFICIENTS);
        if (f != null && f.getCount() == 3) {
            this.lumaRed = f.getAsFloat(0);
            this.lumaGreen = f.getAsFloat(1);
            this.lumaBlue = f.getAsFloat(2);
        }

        f =
          tmetadata.getTIFFField(BaselineTIFFTagSet.TAG_REFERENCE_BLACK_WHITE);
        if (f != null && f.getCount() == 6) {
            this.referenceBlackY = f.getAsFloat(0);
            this.referenceWhiteY = f.getAsFloat(1);
            this.referenceBlackCb = f.getAsFloat(2);
            this.referenceWhiteCb = f.getAsFloat(3);
            this.referenceBlackCr = f.getAsFloat(4);
            this.referenceWhiteCr = f.getAsFloat(5);
        }
    }

    /*
      The full range component value is converted from the code by:

      FullRangeValue = (code - ReferenceBlack) * CodingRange
                / (ReferenceWhite - ReferenceBlack);

      The code is converted from the full-range component value by:

      code = (FullRangeValue * (ReferenceWhite - ReferenceBlack)
                / CodingRange) + ReferenceBlack;

     */
    public void fromRGB(float r, float g, float b, float[] result) {
        // Convert RGB to full-range YCbCr.
        float Y = (lumaRed*r + lumaGreen*g + lumaBlue*b);
        float Cb = (b - Y)/(2 - 2*lumaBlue);
        float Cr = (r - Y)/(2 - 2*lumaRed);

        // Convert full-range YCbCr to code.
        result[0] = Y*(referenceWhiteY - referenceBlackY)/CODING_RANGE_Y +
            referenceBlackY;
        result[1] = Cb*(referenceWhiteCb - referenceBlackCb)/CODING_RANGE_CB_CR +
            referenceBlackCb;
        result[2] = Cr*(referenceWhiteCr - referenceBlackCr)/CODING_RANGE_CB_CR +
            referenceBlackCr;
    }

    public void toRGB(float x0, float x1, float x2, float[] rgb) {
        // Convert YCbCr code to full-range YCbCr.
        float Y = (x0 - referenceBlackY)*CODING_RANGE_Y/
            (referenceWhiteY - referenceBlackY);
        float Cb = (x1 - referenceBlackCb)*CODING_RANGE_CB_CR/
            (referenceWhiteCb - referenceBlackCb);
        float Cr = (x2 - referenceBlackCr)*CODING_RANGE_CB_CR/
            (referenceWhiteCr - referenceBlackCr);

        // Convert YCbCr to RGB.
        rgb[0] = Cr*(2 - 2*lumaRed) + Y;
        rgb[2] = Cb*(2 - 2*lumaBlue) + Y;
        rgb[1] = (Y - lumaBlue*rgb[2] - lumaRed*rgb[0])/lumaGreen;
    }
}
