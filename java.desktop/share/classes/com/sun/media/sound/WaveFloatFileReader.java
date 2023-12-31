package com.sun.media.sound;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Floating-point encoded (format 3) WAVE file loader.
 *
 * @author Karl Helgason
 */
public final class WaveFloatFileReader extends SunFileReader {

    @Override
    StandardFileFormat getAudioFileFormatImpl(final InputStream stream)
            throws UnsupportedAudioFileException, IOException {

        RIFFReader riffiterator = new RIFFReader(stream);
        if (!riffiterator.getFormat().equals("RIFF"))
            throw new UnsupportedAudioFileException();
        if (!riffiterator.getType().equals("WAVE"))
            throw new UnsupportedAudioFileException();

        boolean fmt_found = false;
        boolean data_found = false;

        int channels = 1;
        long samplerate = 1;
        int framesize = 1;
        int bits = 1;
        long dataSize = 0;

        while (riffiterator.hasNextChunk()) {
            RIFFReader chunk = riffiterator.nextChunk();
            if (chunk.getFormat().equals("fmt ")) {
                fmt_found = true;

                int format = chunk.readUnsignedShort();
                if (format != WaveFileFormat.WAVE_FORMAT_IEEE_FLOAT) {
                    throw new UnsupportedAudioFileException();
                }
                channels = chunk.readUnsignedShort();
                samplerate = chunk.readUnsignedInt();
                /* framerate = */chunk.readUnsignedInt();
                framesize = chunk.readUnsignedShort();
                if (framesize == 0) {
                    throw new UnsupportedAudioFileException(
                            "Can not process audio format with 0 frame size");
                }
                bits = chunk.readUnsignedShort();
            }
            if (chunk.getFormat().equals("data")) {
                dataSize = chunk.getSize();
                data_found = true;
                break;
            }
        }
        if (!fmt_found || !data_found) {
            throw new UnsupportedAudioFileException();
        }
        AudioFormat audioformat = new AudioFormat(
                Encoding.PCM_FLOAT, samplerate, bits, channels,
                framesize, samplerate, false);
        return new StandardFileFormat(AudioFileFormat.Type.WAVE, audioformat,
                                      dataSize / audioformat.getFrameSize());
    }

    @Override
    public AudioInputStream getAudioInputStream(final InputStream stream)
            throws UnsupportedAudioFileException, IOException {

        final StandardFileFormat format = getAudioFileFormat(stream);
        final AudioFormat af = format.getFormat();
        final long length = format.getLongFrameLength();
        // we've got everything, the stream is supported and it is at the
        // beginning of the header, so find the data chunk again and return an
        // AudioInputStream
        final RIFFReader riffiterator = new RIFFReader(stream);
        while (riffiterator.hasNextChunk()) {
            RIFFReader chunk = riffiterator.nextChunk();
            if (chunk.getFormat().equals("data")) {
                return new AudioInputStream(chunk, af, length);
            }
        }
        throw new UnsupportedAudioFileException();
    }
}
