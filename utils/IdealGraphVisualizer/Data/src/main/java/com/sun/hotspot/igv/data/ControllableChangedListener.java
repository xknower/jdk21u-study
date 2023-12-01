package com.sun.hotspot.igv.data;

/**
 *
 * @author Thomas Wuerthinger
 */
public abstract class ControllableChangedListener<T> implements ChangedListener<T>{

    private boolean enabled;


    public ControllableChangedListener() {
        enabled = true;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean b) {
        enabled = b;
    }

    @Override
    public void changed(T source) {
        if(enabled) {
            filteredChanged(source);
        }
    }

    public abstract void filteredChanged(T source);
}
