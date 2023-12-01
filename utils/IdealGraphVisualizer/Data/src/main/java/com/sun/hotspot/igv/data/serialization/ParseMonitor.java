package com.sun.hotspot.igv.data.serialization;

public interface ParseMonitor {

    void updateProgress();

    void setState(String state);

}
