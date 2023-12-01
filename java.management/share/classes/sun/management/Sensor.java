package sun.management;

import java.lang.management.MemoryUsage;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

/**
 * An abstract sensor.
 *
 * <p>
 * An {@code AbstractSensor} object consists of two attributes:
 * <ul>
 *   <li>{@code on} is a boolean flag indicating if a sensor is
 *       triggered. This flag will be set or cleared by the
 *       component that owns the sensor.</li>
 *   <li>{@code count} is the total number of times that a sensor
 *       has been triggered.</li>
 * </ul>
 *
 * @author  Mandy Chung
 * @since   1.5
 */

public abstract class Sensor {
    private final Object lock = new Object();
    private final String name;
    private long count;                 // VM-initialized to 0
    private boolean on;                 // VM-initialized to false

    /**
     * Constructs a {@code Sensor} object.
     *
     * @param name The name of this sensor.
     */
    public Sensor(String name) {
        this.name = name;
    }

    /**
     * Returns the name of this sensor.
     *
     * @return the name of this sensor.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the total number of times that this sensor has been triggered.
     *
     * @return the total number of times that this sensor has been triggered.
     */
    public long getCount() {
        synchronized (lock) {
            return count;
        }
    }

    /**
     * Tests if this sensor is currently on.
     *
     * @return {@code true} if the sensor is currently on;
     *         {@code false} otherwise.
     *
     */
    public boolean isOn() {
        synchronized (lock) {
            return on;
        }
    }

    /**
     * Triggers this sensor. This method first sets this sensor on
     * and increments its sensor count.
     */
    public void trigger() {
        synchronized (lock) {
            on = true;
            count++;
        }
        triggerAction();
    }

    /**
     * Triggers this sensor. This method sets this sensor on
     * and increments the count with the input {@code increment}.
     */
    public void trigger(int increment) {
        synchronized (lock) {
            on = true;
            count += increment;
            // Do something here...
        }
        triggerAction();
    }

    /**
     * Triggers this sensor piggybacking a memory usage object.
     * This method sets this sensor on
     * and increments the count with the input {@code increment}.
     */
    public void trigger(int increment, MemoryUsage usage) {
        synchronized (lock) {
            on = true;
            count += increment;
            // Do something here...
        }
        triggerAction(usage);
    }

    /**
     * Clears this sensor.
     */
    public void clear() {
        synchronized (lock) {
            on = false;
        }
        clearAction();
    }


    /**
     * Clears this sensor
     * and increments the count with the input {@code increment}.
     */
    public void clear(int increment) {
        synchronized (lock) {
            on = false;
            count += increment;
        }
        clearAction();
    }

    public String toString() {
        return "Sensor - " + getName() +
            (isOn() ? " on " : " off ") +
            " count = " + getCount();
    }

    abstract void triggerAction();
    abstract void triggerAction(MemoryUsage u);
    abstract void clearAction();
}
