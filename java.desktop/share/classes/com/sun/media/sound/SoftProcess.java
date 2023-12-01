package com.sun.media.sound;

/**
 * Control signal processor interface.
 *
 * @author Karl Helgason
 */
public interface SoftProcess extends SoftControl {

    void init(SoftSynthesizer synth);

    @Override
    double[] get(int instance, String name);

    void processControlLogic();

    void reset();
}
