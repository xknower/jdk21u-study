package com.sun.media.sound;

/**
 * {@code SoftControl} are the basic controls
 * used for control-rate processing.
 *
 * @author Karl Helgason
 */
public interface SoftControl {

    double[] get(int instance, String name);
}
