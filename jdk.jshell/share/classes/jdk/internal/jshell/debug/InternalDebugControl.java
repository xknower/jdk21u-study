package jdk.internal.jshell.debug;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import jdk.jshell.JShell;

/**
 * This class is used to externally control output messages for debugging the
 * implementation of the JShell API.
 * <p>
 * This is not part of the SPI nor API.
 */
public class InternalDebugControl {

    /**
     * This is a static only class; The constructor should never be called.
     */
    private InternalDebugControl() {
    }

    /**
     * General debugging.
     */
    public static final int DBG_GEN = 0b0000001;

    /**
     * File manager debuging.
     */
    public static final int DBG_FMGR = 0b0000010;

    /**
     * Completion analysis debugging.
     */
    public static final int DBG_COMPA = 0b0000100;

    /**
     * Dependency debugging.
     */
    public static final int DBG_DEP = 0b0001000;

    /**
     * Event debugging.
     */
    public static final int DBG_EVNT = 0b0010000;

    /**
     * Event debugging.
     */
    public static final int DBG_WRAP = 0b0100000;

    private static Map<JShell, Integer> debugMap = null;

    /**
     * Sets which debug flags are enabled for a given JShell instance. The flags
     * are or'ed bits as defined in {@code DBG_*}.
     *
     * @param state the JShell instance
     * @param flags the or'ed debug bits
     */
    public static void setDebugFlags(JShell state, int flags) {
        if (debugMap == null) {
            debugMap = new HashMap<>();
        }
        debugMap.put(state, flags);
    }

    /**
     * Release a JShell instance.
     *
     * @param state the JShell instance
     */
    public static void release(JShell state) {
        if (debugMap != null) {
            debugMap.remove(state);
        }
    }

    /**
     * Tests if any of the specified debug flags are enabled.
     *
     * @param state the JShell instance
     * @param flag the {@code DBG_*} bits to check
     * @return true if any of the flags are enabled
     */
    public static boolean isDebugEnabled(JShell state, int flag) {
        if (debugMap == null) {
            return false;
        }
        Integer flags = debugMap.get(state);
        if (flags == null) {
            return false;
        }
        return (flags & flag) != 0;
    }

    /**
     * Displays debug info if the specified debug flags are enabled.
     *
     * @param state the current JShell instance
     * @param err the {@code PrintStream} to report on
     * @param flags {@code DBG_*} flag bits to check
     * @param format format string for the output
     * @param args args for the format string
     */
    public static void debug(JShell state, PrintStream err, int flags, String format, Object... args) {
        if (isDebugEnabled(state, flags)) {
            err.printf(format, args);
        }
    }

    /**
     * Displays a fatal exception as debug info.
     *
     * @param state the current JShell instance
     * @param err the {@code PrintStream} to report on
     * @param ex the fatal Exception
     * @param where additional context
     */
    public static void debug(JShell state, PrintStream err, Throwable ex, String where) {
        if (isDebugEnabled(state, 0xFFFFFFFF)) {
            err.printf("Fatal error: %s: %s\n", where, ex.getMessage());
            ex.printStackTrace(err);
        }
    }
}
