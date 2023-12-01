package com.sun.hotspot.igv.data;

/**
 * Class representing a generic changed event.
 * @author Thomas Wuerthinger
 * @param <T>
 */
public class ChangedEvent<T> extends Event<ChangedListener<T>> {

    private final T object;

    /**
     * Creates a new event with the specific object as the one for which the event gets fired.
     */
    public ChangedEvent(T object) {
        this.object = object;
    }

    @Override
    protected void fire(ChangedListener<T> l) {
        l.changed(object);
    }
}
