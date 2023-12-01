package com.sun.media.sound;

import java.io.InputStream;

import javax.sound.midi.Soundbank;
import javax.sound.midi.SoundbankResource;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;

/**
 * Soundfont sample storage.
 *
 * @author Karl Helgason
 */
public final class SF2Sample extends SoundbankResource {

    String name = "";
    long startLoop = 0;
    long endLoop = 0;
    long sampleRate = 44100;
    int originalPitch = 60;
    byte pitchCorrection = 0;
    int sampleLink = 0;
    int sampleType = 0;
    ModelByteBuffer data;
    ModelByteBuffer data24;

    public SF2Sample(Soundbank soundBank) {
        super(soundBank, null, AudioInputStream.class);
    }

    public SF2Sample() {
        super(null, null, AudioInputStream.class);
    }

    @Override
    public Object getData() {

        AudioFormat format = getFormat();
        /*
        if (sampleFile != null) {
            FileInputStream fis;
            try {
                fis = new FileInputStream(sampleFile);
                RIFFReader riff = new RIFFReader(fis);
                if (!riff.getFormat().equals("RIFF")) {
                    throw new RIFFInvalidDataException(
                        "Input stream is not a valid RIFF stream!");
                }
                if (!riff.getType().equals("sfbk")) {
                    throw new RIFFInvalidDataException(
                        "Input stream is not a valid SoundFont!");
                }
                while (riff.hasNextChunk()) {
                    RIFFReader chunk = riff.nextChunk();
                    if (chunk.getFormat().equals("LIST")) {
                        if (chunk.getType().equals("sdta")) {
                            while(chunk.hasNextChunk()) {
                                RIFFReader chunkchunk = chunk.nextChunk();
                                if(chunkchunk.getFormat().equals("smpl")) {
                                    chunkchunk.skip(sampleOffset);
                                    return new AudioInputStream(chunkchunk,
                                            format, sampleLen);
                                }
                            }
                        }
                    }
                }
                return null;
            } catch (Exception e) {
                return new Throwable(e.toString());
            }
        }
        */
        InputStream is = data.getInputStream();
        if (is == null)
            return null;
        return new AudioInputStream(is, format, data.capacity());
    }

    public ModelByteBuffer getDataBuffer() {
        return data;
    }

    public ModelByteBuffer getData24Buffer() {
        return data24;
    }

    public AudioFormat getFormat() {
        return new AudioFormat(sampleRate, 16, 1, true, false);
    }

    public void setData(ModelByteBuffer data) {
        this.data = data;
    }

    public void setData(byte[] data) {
        this.data = new ModelByteBuffer(data);
    }

    public void setData(byte[] data, int offset, int length) {
        this.data = new ModelByteBuffer(data, offset, length);
    }

    public void setData24(ModelByteBuffer data24) {
        this.data24 = data24;
    }

    public void setData24(byte[] data24) {
        this.data24 = new ModelByteBuffer(data24);
    }

    public void setData24(byte[] data24, int offset, int length) {
        this.data24 = new ModelByteBuffer(data24, offset, length);
    }

    /*
    public void setData(File file, int offset, int length) {
        this.data = null;
        this.sampleFile = file;
        this.sampleOffset = offset;
        this.sampleLen = length;
    }
    */

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getEndLoop() {
        return endLoop;
    }

    public void setEndLoop(long endLoop) {
        this.endLoop = endLoop;
    }

    public int getOriginalPitch() {
        return originalPitch;
    }

    public void setOriginalPitch(int originalPitch) {
        this.originalPitch = originalPitch;
    }

    public byte getPitchCorrection() {
        return pitchCorrection;
    }

    public void setPitchCorrection(byte pitchCorrection) {
        this.pitchCorrection = pitchCorrection;
    }

    public int getSampleLink() {
        return sampleLink;
    }

    public void setSampleLink(int sampleLink) {
        this.sampleLink = sampleLink;
    }

    public long getSampleRate() {
        return sampleRate;
    }

    public void setSampleRate(long sampleRate) {
        this.sampleRate = sampleRate;
    }

    public int getSampleType() {
        return sampleType;
    }

    public void setSampleType(int sampleType) {
        this.sampleType = sampleType;
    }

    public long getStartLoop() {
        return startLoop;
    }

    public void setStartLoop(long startLoop) {
        this.startLoop = startLoop;
    }

    @Override
    public String toString() {
        return "Sample: " + name;
    }
}
