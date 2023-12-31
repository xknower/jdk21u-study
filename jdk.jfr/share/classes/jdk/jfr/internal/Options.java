package jdk.jfr.internal;

import java.io.IOException;

import jdk.jfr.internal.SecuritySupport.SafePath;
import jdk.internal.misc.Unsafe;

import static java.nio.file.LinkOption.*;

/**
 * Options that control Flight Recorder.
 *
 * Can be set using JFR.configure
 *
 */
public final class Options {

    private static final JVM jvm = JVM.getJVM();
    private static final long WAIT_INTERVAL = 1000; // ms;

    private static final long MIN_MAX_CHUNKSIZE = 1024 * 1024;

    private static final long DEFAULT_GLOBAL_BUFFER_COUNT = 20;
    private static final long DEFAULT_GLOBAL_BUFFER_SIZE = 524288;
    private static final long DEFAULT_MEMORY_SIZE = DEFAULT_GLOBAL_BUFFER_COUNT * DEFAULT_GLOBAL_BUFFER_SIZE;
    private static long DEFAULT_THREAD_BUFFER_SIZE;
    private static final int DEFAULT_STACK_DEPTH = 64;
    private static final long DEFAULT_MAX_CHUNK_SIZE = 12 * 1024 * 1024;
    private static final SafePath DEFAULT_DUMP_PATH = null;
    private static final boolean DEFAULT_PRESERVE_REPOSITORY = false;

    private static long memorySize;
    private static long globalBufferSize;
    private static long globalBufferCount;
    private static long threadBufferSize;
    private static int stackDepth;
    private static long maxChunkSize;
    private static boolean preserveRepository;

    static {
        final long pageSize = Unsafe.getUnsafe().pageSize();
        DEFAULT_THREAD_BUFFER_SIZE = pageSize > 8 * 1024 ? pageSize : 8 * 1024;
        reset();
    }

    public static synchronized void setMaxChunkSize(long max) {
        if (max < MIN_MAX_CHUNKSIZE) {
            throw new IllegalArgumentException("Max chunk size must be at least " + MIN_MAX_CHUNKSIZE);
        }
        jvm.setFileNotification(max);
        maxChunkSize = max;
    }

    public static synchronized long getMaxChunkSize() {
        return maxChunkSize;
    }

    public static synchronized void setMemorySize(long memSize) {
        jvm.setMemorySize(memSize);
        memorySize = memSize;
    }

    public static synchronized long getMemorySize() {
        return memorySize;
    }

    public static synchronized void setThreadBufferSize(long threadBufSize) {
        jvm.setThreadBufferSize(threadBufSize);
        threadBufferSize = threadBufSize;
    }

    public static synchronized long getThreadBufferSize() {
        return threadBufferSize;
    }

    public static synchronized long getGlobalBufferSize() {
        return globalBufferSize;
    }

    public static synchronized void setGlobalBufferCount(long globalBufCount) {
        jvm.setGlobalBufferCount(globalBufCount);
        globalBufferCount = globalBufCount;
    }

    public static synchronized long getGlobalBufferCount() {
        return globalBufferCount;
    }

    public static synchronized void setGlobalBufferSize(long globalBufsize) {
        jvm.setGlobalBufferSize(globalBufsize);
        globalBufferSize = globalBufsize;
    }

    public static synchronized void setDumpPath(SafePath path) throws IOException {
        if (path != null) {
            if (SecuritySupport.isWritable(path)) {
                path = SecuritySupport.toRealPath(path, NOFOLLOW_LINKS);
            } else {
                throw new IOException("Cannot write JFR emergency dump to " + path.toString());
            }
        }
        jvm.setDumpPath(path == null ? null : path.toString());
    }

    public static synchronized SafePath getDumpPath() {
        return new SafePath(jvm.getDumpPath());
    }

    public static synchronized void setStackDepth(Integer stackTraceDepth) {
        jvm.setStackDepth(stackTraceDepth);
        stackDepth = stackTraceDepth;
    }

    public static synchronized int getStackDepth() {
        return stackDepth;
    }

    public static synchronized void setPreserveRepository(boolean preserve) {
        preserveRepository = preserve;
    }

    public static synchronized boolean getPreserveRepository() {
        return preserveRepository;
    }

    private static synchronized void reset() {
        setMaxChunkSize(DEFAULT_MAX_CHUNK_SIZE);
        setMemorySize(DEFAULT_MEMORY_SIZE);
        setGlobalBufferSize(DEFAULT_GLOBAL_BUFFER_SIZE);
        setGlobalBufferCount(DEFAULT_GLOBAL_BUFFER_COUNT);
        try {
            setDumpPath(DEFAULT_DUMP_PATH);
        } catch (IOException e) {
            // Ignore (depends on default value in JVM: it would be NULL)
        }
        setStackDepth(DEFAULT_STACK_DEPTH);
        setThreadBufferSize(DEFAULT_THREAD_BUFFER_SIZE);
        setPreserveRepository(DEFAULT_PRESERVE_REPOSITORY);
    }

    static synchronized long getWaitInterval() {
        return WAIT_INTERVAL;
    }

    static void ensureInitialized() {
        // trigger clinit which will setup JVM defaults.
    }


}
