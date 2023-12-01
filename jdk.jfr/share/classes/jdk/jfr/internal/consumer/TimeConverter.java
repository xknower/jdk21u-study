package jdk.jfr.internal.consumer;

import java.time.DateTimeException;
import java.time.ZoneOffset;

import jdk.jfr.internal.LogLevel;
import jdk.jfr.internal.LogTag;
import jdk.jfr.internal.Logger;

/**
 * Converts ticks to nanoseconds
 */
final class TimeConverter {
    private final long startTicks;
    private final long startNanos;
    private final double divisor;
    private final ZoneOffset zoneOffset;

    TimeConverter(ChunkHeader chunkHeader, int offset) {
        this.startTicks = chunkHeader.getStartTicks();
        this.startNanos = chunkHeader.getStartNanos();
        this.divisor = chunkHeader.getTicksPerSecond() / 1000_000_000L;
        this.zoneOffset = zoneOfSet(offset);
    }

    public long convertTimestamp(long ticks) {
        return startNanos + (long) ((ticks - startTicks) / divisor);
    }

    public long convertTimespan(long ticks) {
        return (long) (ticks / divisor);
    }

    public ZoneOffset getZoneOffset() {
        return zoneOffset;
    }

    private ZoneOffset zoneOfSet(int offset) {
        try {
            return ZoneOffset.ofTotalSeconds(offset / 1000);
        } catch (DateTimeException dte) {
            Logger.log(LogTag.JFR_SYSTEM_PARSER, LogLevel.INFO, "Could not create ZoneOffset from raw offset " + offset);
        }
        return ZoneOffset.UTC;
    }
}
