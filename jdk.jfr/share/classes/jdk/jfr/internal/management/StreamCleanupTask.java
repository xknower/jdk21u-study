package jdk.jfr.internal.management;

import java.util.TimerTask;

// Helper class to StreamManager
final class StreamCleanupTask extends TimerTask {

    private final EventByteStream stream;
    private final StreamManager manager;

    StreamCleanupTask(StreamManager streamManager, EventByteStream stream) {
        this.stream = stream;
        this.manager = streamManager;
    }

    @Override
    public void run() {
        long lastTouched = stream.getLastTouched();
        long now = System.currentTimeMillis();
        if (now - lastTouched >= StreamManager.TIME_OUT) {
            manager.destroy(stream);
        } else {
            manager.scheduleAbort(stream, lastTouched + StreamManager.TIME_OUT);
        }
    }
}
