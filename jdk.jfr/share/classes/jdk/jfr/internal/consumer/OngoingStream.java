package jdk.jfr.internal.consumer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Path;

import jdk.jfr.Recording;
import jdk.jfr.RecordingState;
import jdk.jfr.internal.SecuritySupport;
import jdk.jfr.internal.SecuritySupport.SafePath;
import jdk.jfr.internal.management.EventByteStream;
import jdk.jfr.internal.management.ManagementSupport;

public final class OngoingStream extends EventByteStream {

    private static final byte[] EMPTY_ARRAY = new byte[0];
    private static final int HEADER_SIZE = (int)ChunkHeader.HEADER_SIZE;
    private static final int HEADER_FILE_STATE_POSITION = (int)ChunkHeader.FILE_STATE_POSITION;
    private static final byte MODIFYING_STATE = ChunkHeader.UPDATING_CHUNK_HEADER;

    private final RepositoryFiles repositoryFiles;
    private final Recording recording;
    private final int blockSize;
    private final long endTimeNanos;
    private final byte[] headerBytes = new byte[HEADER_SIZE];

    private RecordingInput input;
    private ChunkHeader header;
    private long position;
    private long startTimeNanos;
    private Path path;
    private boolean first = true;

    public OngoingStream(Recording recording, int blockSize, long startTimeNanos, long endTimeNanos) {
        super();
        this.recording = recording;
        this.blockSize = blockSize;
        this.startTimeNanos = startTimeNanos;
        this.endTimeNanos = endTimeNanos;
        this.repositoryFiles = new RepositoryFiles(SecuritySupport.PRIVILEGED, null, false);
    }

    @Override
    public synchronized byte[] read() throws IOException {
        try {
            return readBytes();
        } catch (IOException ioe) {
            if (recording.getState() == RecordingState.CLOSED) {
                // Recording closed, return null;
                return null;
            }
            // Something unexpected has happened.
            throw ioe;
        }
    }

    private byte[] readBytes() throws IOException {
        touch();
        if (recording.getState() == RecordingState.NEW) {
           return EMPTY_ARRAY;
        }

        if (recording.getState() == RecordingState.DELAYED) {
            return EMPTY_ARRAY;
        }

        if (first) {
            // In case stream starts before recording
            long s = ManagementSupport.getStartTimeNanos(recording);
            startTimeNanos = Math.max(s, startTimeNanos);
            first = false;
        }

        while (true) {
            if (startTimeNanos > endTimeNanos) {
                return null;
            }
            if (isRecordingClosed()) {
                closeInput();
                return null;
            }
            if (!ensurePath()) {
                return EMPTY_ARRAY;
            }
            if (!ensureInput()) {
                return EMPTY_ARRAY;
            }
            if (position < header.getChunkSize()) {
                long size = Math.min(header.getChunkSize() - position, blockSize);
                return readBytes((int) size);
            }
            if (header.isFinished()) {
                if (header.getDurationNanos() < 1) {
                    throw new IOException("No progress");
                }
                startTimeNanos += header.getDurationNanos();
                ManagementSupport.removePath(recording, path);
                closeInput();
            } else {
                header.refresh();
                if (position >= header.getChunkSize()) {
                    return EMPTY_ARRAY;
                }
            }
        }
    }

    private boolean isRecordingClosed() {
        return recording != null && recording.getState() == RecordingState.CLOSED;
    }

    private void closeInput() {
        if (input != null) {
            try {
                input.close();
            } catch (IOException ioe) {
                // ignore
            }
            input = null;
            position = 0;
            path = null;
        }
    }

    private byte[] readBytes(int size) throws IOException {
        if (position == 0) {
            return readWithHeader(size);
        } else {
            return readNonHeader(size);
        }
    }

    private byte[] readNonHeader(int size) throws IOException {
        byte[] result = new byte[size];
        input.readFully(result);
        position += size;
        return result;
    }

    private byte[] readWithHeader(int size) throws IOException {
        byte[] bytes = new byte[Math.max(HEADER_SIZE, size)];
        for (int attempts = 0; attempts < 25; attempts++) {
            // read twice and check files state to avoid simultaneous change by JVM
            input.position(0);
            input.readFully(bytes, 0, HEADER_SIZE);
            input.position(0);
            input.readFully(headerBytes);
            if (bytes[HEADER_FILE_STATE_POSITION] != MODIFYING_STATE) {
                if (bytes[HEADER_FILE_STATE_POSITION] == headerBytes[HEADER_FILE_STATE_POSITION]) {
                    ByteBuffer buffer = ByteBuffer.wrap(bytes);
                    // 0-3: magic
                    // 4-5: major
                    // 6-7: minor
                    // 8-15: chunk size
                    buffer.putLong(8, HEADER_SIZE);
                    // 16-23: constant pool offset
                    buffer.putLong(16, 0);
                    // 24-31: metadata offset
                    buffer.putLong(24, 0);
                    // 32-39: chunk start nanos
                    // 40-47 duration
                    buffer.putLong(40, 0);
                    // 48-55: chunk start ticks
                    // 56-63: ticks per second
                    // 64: file state
                    buffer.put(64, (byte) 1);
                    // 65-67: extension bit
                    int left = bytes.length - HEADER_SIZE;
                    input.readFully(bytes, HEADER_SIZE, left);
                    position += bytes.length;
                    return bytes;
                }
            }
            takeNap();
        }
        return EMPTY_ARRAY;
    }

    private void takeNap() throws IOException {
        try {
            Thread.sleep(10);
        } catch (InterruptedException ie) {
            throw new IOException("Read operation interrupted", ie);
        }
    }

    private boolean ensureInput() throws IOException {
        if (input == null) {
            if (SecuritySupport.getFileSize(new SafePath(path)) < HEADER_SIZE) {
                return false;
            }
            input = new RecordingInput(path.toFile(), SecuritySupport.PRIVILEGED);
            input.setStreamed();
            header = new ChunkHeader(input);
        }
        return true;
    }

    private boolean ensurePath() {
        if (path == null) {
            path = repositoryFiles.nextPath(startTimeNanos, false);
        }
        return path != null;
    }

    @Override
    public synchronized void close() throws IOException {
        closeInput();
        // Close recording if stream times out.
        if (recording.getName().startsWith(EventByteStream.NAME)) {
            recording.close();
        }
    }
}
