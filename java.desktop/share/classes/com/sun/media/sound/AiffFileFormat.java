package com.sun.media.sound;

import javax.sound.sampled.AudioFormat;

/**
 * AIFF file format.
 *
 * @author Jan Borgersen
 */
final class AiffFileFormat extends StandardFileFormat {

    static final int AIFF_MAGIC         = 1179603533;

    // for writing AIFF
    static final int AIFC_MAGIC                 = 0x41494643;   // 'AIFC'
    static final int AIFF_MAGIC2                = 0x41494646;   // 'AIFF'
    static final int FVER_MAGIC                 = 0x46564552;   // 'FVER'
    static final int FVER_TIMESTAMP             = 0xA2805140;   // timestamp of last AIFF-C update
    static final int COMM_MAGIC                 = 0x434f4d4d;   // 'COMM'
    static final int SSND_MAGIC                 = 0x53534e44;   // 'SSND'

    // compression codes
    static final int AIFC_PCM                   = 0x4e4f4e45;   // 'NONE' PCM
    static final int AIFC_ACE2                  = 0x41434532;   // 'ACE2' ACE 2:1 compression
    static final int AIFC_ACE8                  = 0x41434538;   // 'ACE8' ACE 8:3 compression
    static final int AIFC_MAC3                  = 0x4d414333;   // 'MAC3' MACE 3:1 compression
    static final int AIFC_MAC6                  = 0x4d414336;   // 'MAC6' MACE 6:1 compression
    static final int AIFC_ULAW                  = 0x756c6177;   // 'ulaw' ITU G.711 u-Law
    static final int AIFC_IMA4                  = 0x696d6134;   // 'ima4' IMA ADPCM

    // $$fb static approach not good, but needed for estimation
    static final int AIFF_HEADERSIZE    = 54;

    //$$fb 2001-07-13: added management of header size in this class

    /** header size in bytes */
    private final int headerSize=AIFF_HEADERSIZE;

    /** comm chunk size in bytes, inclusive magic and length field */
    private final int commChunkSize=26;

    /** FVER chunk size in bytes, inclusive magic and length field */
    private final int fverChunkSize=0;

    AiffFileFormat(final Type type, final long byteLength,
                   final AudioFormat format, final long frameLength) {
        super(type, byteLength, format, frameLength);
    }

    int getHeaderSize() {
        return headerSize;
    }

    int getCommChunkSize() {
        return commChunkSize;
    }

    int getFverChunkSize() {
        return fverChunkSize;
    }

    int getSsndChunkOffset() {
        return getHeaderSize()-16;
    }
}
