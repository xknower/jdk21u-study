package com.sun.hotspot.igv.hierarchicallayout;

/**
 *
 * @author Thomas Wuerthinger
 */
public class Timing {

    private long lastValue;
    private long sum;
    private final String name;

    public Timing(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        long val = sum;
        if (lastValue != 0) {
            // Timer running
            long newValue = System.nanoTime();
            val += (newValue - lastValue);
        }
        return "Timing for " + name + " is: " + val / 1000000 + " ms";
    }

    public void print() {
        System.out.println();
    }

    public void start() {
        lastValue = System.nanoTime();
    }

    public void stop() {
        if (lastValue == 0) {
            throw new IllegalStateException("You must call start before stop");
        }
        long newValue = System.nanoTime();
        sum += newValue - lastValue;
        lastValue = 0;
    }
}
