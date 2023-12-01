package com.sun.media.sound;

/**
 * Basic resampler interface.
 *
 * @author Karl Helgason
 */
public interface SoftResampler {

    SoftResamplerStreamer openStreamer();
}
