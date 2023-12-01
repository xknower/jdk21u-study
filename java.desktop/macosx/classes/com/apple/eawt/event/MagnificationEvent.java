package com.apple.eawt.event;

/**
 * Event encapsulating a relative magnification scale performed by the user.
 *
 * @see MagnificationListener
 *
 * @since Java for Mac OS X 10.5 Update 7, Java for Mac OS X 10.6 Update 2
 */
public class MagnificationEvent extends GestureEvent {
    final double magnification;

    MagnificationEvent(final double magnification) {
        // package private
        this.magnification = magnification;
    }

    /**
     * @return an abstract measure of magnification scale (both positive and negative)
     */
    public double getMagnification() {
        return magnification;
    }
}
