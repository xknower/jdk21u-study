package jdk.internal.platform;

public class SystemMetrics {
    public static Metrics instance() {
        return CgroupMetrics.getInstance();
    }
}
