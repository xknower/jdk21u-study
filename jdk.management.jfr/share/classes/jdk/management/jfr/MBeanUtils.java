package jdk.management.jfr;

import java.io.IOException;
import java.lang.management.ManagementPermission;
import java.security.Permission;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import jdk.jfr.Recording;
import jdk.jfr.internal.management.ManagementSupport;

final class MBeanUtils {

    private static final Permission monitor = new ManagementPermission("monitor");
    private static final Permission control = new ManagementPermission("control");

    static ObjectName createObjectName() {
        try {
            return new ObjectName(FlightRecorderMXBean.MXBEAN_NAME);
        } catch (MalformedObjectNameException mne) {
            throw new Error("Can't happen", mne);
        }
    }

    static void checkControl() {
        @SuppressWarnings("removal")
        SecurityManager secManager = System.getSecurityManager();
        if (secManager != null) {
            secManager.checkPermission(control);
        }
    }

    static void checkMonitor() {
        @SuppressWarnings("removal")
        SecurityManager secManager = System.getSecurityManager();
        if (secManager != null) {
            secManager.checkPermission(monitor);
        }
    }

    static <T, R> List<R> transformList(List<T> source, Function<T, R> function) {
        return source.stream().map(function).collect(Collectors.toList());
    }

    static boolean booleanValue(String s) {
        if ("true".equals(s)) {
            return true;
        }
        if ("false".equals(s)) {
            return false;
        }
        throw new IllegalArgumentException("Value must be true or false.");
    }

    static Duration duration(String s) throws NumberFormatException {
        if (s == null) {
            return null;
        }
        long l = ManagementSupport.parseTimespan(s);
        if (l == 0) {
            return null;
        }
        return Duration.ofNanos(l);
    }

    public static Instant parseTimestamp(String s, Instant defaultValue) {
        if (s == null) {
            return defaultValue;
        }
        try {
            return Instant.parse(s);
        } catch(DateTimeParseException e ) {
            // OK, try with milliseconds since epoch
            // before giving up.
        }
        try {
            return Instant.ofEpochMilli(Long.parseLong(s));
        } catch (NumberFormatException | DateTimeException nfr) {
            throw new IllegalArgumentException("Not a valid timestamp " + s);
        }
    }

    static Long size(String s) throws NumberFormatException {
        long size = Long.parseLong(s);
        if (size < 0) {
            throw new IllegalArgumentException("Negative size not allowed");
        }
        return size;
    }

    public static int parseBlockSize(String string, int defaultSize) {
        if (string == null) {
            return defaultSize;
        }
        int size = Integer.parseInt(string);
        if (size <1)  {
            throw new IllegalArgumentException("Block size must be at least 1 byte");
        }
        return size;
    }

    public static String destination(Recording recording, String destination) throws IllegalArgumentException{
        try {
            ManagementSupport.checkSetDestination(recording, destination);
            return destination;
        }catch(IOException e){
            IllegalArgumentException iae = new IllegalArgumentException("Not a valid destination " + destination);
            iae.addSuppressed(e);
            throw iae;
        }
    }
}
