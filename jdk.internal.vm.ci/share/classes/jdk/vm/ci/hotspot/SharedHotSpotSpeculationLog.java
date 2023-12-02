package jdk.vm.ci.hotspot;

/**
 * A wrapper that holds a strong reference to a "master" speculation log that
 * {@linkplain HotSpotSpeculationLog#managesFailedSpeculations() manages} the failed speculations
 * list.
 */
public class SharedHotSpotSpeculationLog extends HotSpotSpeculationLog {
    private final HotSpotSpeculationLog masterLog;

    public SharedHotSpotSpeculationLog(HotSpotSpeculationLog masterLog) {
        super(masterLog.getFailedSpeculationsAddress());
        this.masterLog = masterLog;
    }

    @Override
    public String toString() {
        return masterLog.toString();
    }
}
