package jdk.jfr.internal.consumer;

import java.io.IOException;

import jdk.jfr.internal.LogLevel;
import jdk.jfr.internal.LogTag;
import jdk.jfr.internal.Logger;
import jdk.jfr.internal.MetadataDescriptor;

public final class ChunkHeader {
    public static final long HEADER_SIZE = 68;
    static final byte UPDATING_CHUNK_HEADER = (byte) 255;
    public static final long CHUNK_SIZE_POSITION = 8;
    static final long DURATION_NANOS_POSITION = 40;
    static final long FILE_STATE_POSITION = 64;
    static final long FLAG_BYTE_POSITION = 67;
    static final long METADATA_TYPE_ID = 0;
    static final byte[] FILE_MAGIC = { 'F', 'L', 'R', '\0' };
    static final int MASK_FINAL_CHUNK = 1 << 1;

    private final short major;
    private final short minor;
    private final long chunkStartTicks;
    private final long ticksPerSecond;
    private final long chunkStartNanos;
    private final long absoluteChunkStart;
    private final RecordingInput input;
    private final long id;
    private long absoluteEventStart;
    private long chunkSize = 0;
    private long constantPoolPosition = 0;
    private long metadataPosition = 0;
    private long durationNanos;
    private long absoluteChunkEnd;
    private boolean finished;
    private boolean finalChunk;

    public ChunkHeader(RecordingInput input) throws IOException {
        this(input, 0, 0);
    }

    private ChunkHeader(RecordingInput input, long absoluteChunkStart, long id) throws IOException {
        this.absoluteChunkStart = absoluteChunkStart;
        this.absoluteEventStart = absoluteChunkStart + HEADER_SIZE;
        if (input.getFileSize() < HEADER_SIZE) {
            throw new IOException("Not a complete Chunk header");
        }
        input.setValidSize(absoluteChunkStart + HEADER_SIZE);
        input.position(absoluteChunkStart);
        if (input.position() >= input.size()) {
           throw new IOException("Chunk contains no data");
        }
        verifyMagic(input);
        this.input = input;
        this.id = id;
        Logger.log(LogTag.JFR_SYSTEM_PARSER, LogLevel.INFO, "Chunk: " + id);
        Logger.log(LogTag.JFR_SYSTEM_PARSER, LogLevel.INFO, "Chunk: file=" + input.getFilename());
        Logger.log(LogTag.JFR_SYSTEM_PARSER, LogLevel.INFO, "Chunk: startPosition=" + absoluteChunkStart);
        major = input.readRawShort();
        Logger.log(LogTag.JFR_SYSTEM_PARSER, LogLevel.INFO, "Chunk: major=" + major);
        minor = input.readRawShort();
        Logger.log(LogTag.JFR_SYSTEM_PARSER, LogLevel.INFO, "Chunk: minor=" + minor);
        if (major != 1 && major != 2) {
            throw new IOException("File version " + major + "." + minor + ". Only Flight Recorder files of version 1.x and 2.x can be read by this JDK.");
        }
        // Chunk size, constant pool position and metadata position are
        // updated by JVM and not reliable to read
        input.skipBytes(3 * Long.BYTES);

        chunkStartNanos = input.readRawLong();
        Logger.log(LogTag.JFR_SYSTEM_PARSER, LogLevel.INFO, "Chunk: startNanos=" + chunkStartNanos);

        // Duration nanos, updated by JVM and not reliable to read
        input.skipBytes(Long.BYTES);

        chunkStartTicks = input.readRawLong();
        Logger.log(LogTag.JFR_SYSTEM_PARSER, LogLevel.INFO, "Chunk: startTicks=" + chunkStartTicks);

        ticksPerSecond = input.readRawLong();
        Logger.log(LogTag.JFR_SYSTEM_PARSER, LogLevel.INFO, "Chunk: ticksPerSecond=" + ticksPerSecond);

        // File state and flag bit, updated by JVM and not reliable to read
        input.skipBytes(Integer.BYTES);

        refresh();
        input.position(absoluteEventStart);
    }

    private byte readFileState() throws IOException {
        byte fs;
        input.positionPhysical(absoluteChunkStart + FILE_STATE_POSITION);
        while ((fs = input.readPhysicalByte()) == UPDATING_CHUNK_HEADER) {
            input.pollWait();
            input.positionPhysical(absoluteChunkStart + FILE_STATE_POSITION);
        }
        return fs;
    }

    public void refresh() throws IOException {
        while (true) {
            byte fileState1 = readFileState();
            input.positionPhysical(absoluteChunkStart + CHUNK_SIZE_POSITION);
            long chunkSize = input.readPhysicalLong();
            long constantPoolPosition = input.readPhysicalLong();
            long metadataPosition = input.readPhysicalLong();
            input.positionPhysical(absoluteChunkStart + DURATION_NANOS_POSITION);
            long durationNanos = input.readPhysicalLong();
            input.positionPhysical(absoluteChunkStart + FILE_STATE_POSITION);
            byte fileState2 =  input.readPhysicalByte();
            input.positionPhysical(absoluteChunkStart + FLAG_BYTE_POSITION);
            int flagByte = input.readPhysicalByte();
            if (fileState1 == fileState2) { // valid header
                finished = fileState1 == 0;
                if (metadataPosition != 0) {
                    Logger.log(LogTag.JFR_SYSTEM_PARSER, LogLevel.INFO, "Setting input size to " + (absoluteChunkStart + chunkSize));
                    if (finished) {
                        // This assumes that the whole recording
                        // is finished if the first chunk is.
                        // This is a limitation we may want to
                        // remove, but greatly improves performance as
                        // data can be read across chunk boundaries
                        // of multi-chunk files and only once.
                        input.setValidSize(input.getFileSize());
                    } else {
                        input.setValidSize(absoluteChunkStart + chunkSize);
                    }
                    this.chunkSize = chunkSize;
                    Logger.log(LogTag.JFR_SYSTEM_PARSER, LogLevel.INFO, "Chunk: chunkSize=" + chunkSize);
                    this.constantPoolPosition = constantPoolPosition;
                    Logger.log(LogTag.JFR_SYSTEM_PARSER, LogLevel.INFO, "Chunk: constantPoolPosition=" + constantPoolPosition);
                    this.metadataPosition = metadataPosition;
                    Logger.log(LogTag.JFR_SYSTEM_PARSER, LogLevel.INFO, "Chunk: metadataPosition=" + metadataPosition);
                    this.durationNanos = durationNanos;
                    Logger.log(LogTag.JFR_SYSTEM_PARSER, LogLevel.INFO, "Chunk: durationNanos =" + durationNanos);
                    Logger.log(LogTag.JFR_SYSTEM_PARSER, LogLevel.INFO, "Chunk: generation=" + fileState2);
                    Logger.log(LogTag.JFR_SYSTEM_PARSER, LogLevel.INFO, "Chunk: finished=" + finished);
                    Logger.log(LogTag.JFR_SYSTEM_PARSER, LogLevel.INFO, "Chunk: fileSize=" + input.size());
                    this.finalChunk = (flagByte & MASK_FINAL_CHUNK) != 0;
                    Logger.log(LogTag.JFR_SYSTEM_PARSER, LogLevel.INFO, "Chunk: finalChunk=" + finalChunk);
                    absoluteChunkEnd = absoluteChunkStart + chunkSize;
                    return;
                } else {
                    if (finished) {
                        throw new IOException("No metadata event found in finished chunk.");
                    }
                    if (chunkSize == HEADER_SIZE) {
                        // This ensures that a non-streaming parser is able
                        // to break out of the loop in case the file is
                        // ended before the first metadata event has
                        // been written. This can happen during a failed crash
                        // dump.
                        input.pollWait();
                    }
                }
            }
        }
    }

    public void awaitFinished() throws IOException {
        if (finished) {
            return;
        }
        long pos = input.position();
        try {
            while (true) {
                byte fileState = readFileState();
                if (fileState == 0) {
                    finished = true;
                    return;
                }
                input.pollWait();
            }
        } finally {
            input.position(pos);
        }
    }

    public boolean isLastChunk() throws IOException {
        awaitFinished();
        // streaming files only have one chunk
        return input.getFileSize() == absoluteChunkEnd;
   }

    public boolean isFinalChunk() {
        return finalChunk;
    }

    public boolean isFinished() throws IOException {
        return finished;
    }

    public ChunkHeader nextHeader() throws IOException {
        return new ChunkHeader(input, absoluteChunkEnd, id + 1);
    }
    public MetadataDescriptor readMetadata() throws IOException {
        return readMetadata(null);
    }

    public MetadataDescriptor readMetadata(MetadataDescriptor previous) throws IOException {
        input.position(absoluteChunkStart + metadataPosition);
        input.readInt(); // size
        long id = input.readLong(); // event type id
        if (id != METADATA_TYPE_ID) {
            throw new IOException("Expected metadata event. Type id=" + id + ", should have been " + METADATA_TYPE_ID);
        }
        input.readLong(); // start time
        input.readLong(); // duration
        long metadataId = input.readLong();
        if (previous != null && metadataId == previous.metadataId) {
            return previous;
        }
        Logger.log(LogTag.JFR_SYSTEM_PARSER, LogLevel.TRACE, "New metadata id = " + metadataId);
        MetadataDescriptor m =  MetadataDescriptor.read(input);
        m.metadataId = metadataId;
        return m;
    }


    public short getMajor() {
        return major;
    }

    public short getMinor() {
        return minor;
    }

    public long getAbsoluteChunkStart() {
        return absoluteChunkStart;
    }

    public long getAbsoluteEventStart() {
        return absoluteEventStart;
    }
    public long getConstantPoolPosition() {
        return constantPoolPosition;
    }

    public long getMetadataPosition() {
        return metadataPosition;
    }
    public long getStartTicks() {
        return chunkStartTicks;
    }
    public long getChunkSize() {
        return chunkSize;
    }

    public double getTicksPerSecond() {
        return ticksPerSecond;
    }

    public long getStartNanos() {
        return chunkStartNanos;
    }

    public long getEnd() {
        return absoluteChunkEnd;
    }

    public long getSize() {
        return chunkSize;
    }

    public long getDurationNanos() {
        return durationNanos;
    }

    public RecordingInput getInput() {
        return input;
    }

    private static void verifyMagic(RecordingInput input) throws IOException {
        for (byte c : FILE_MAGIC) {
            if (input.readByte() != c) {
                throw new IOException("Not a Flight Recorder file");
            }
        }
    }

    public long getEventStart() {
        return absoluteEventStart;
    }

    static long headerSize() {
        return HEADER_SIZE;
    }

    public long getLastNanos() {
        return getStartNanos() + getDurationNanos();
    }
}
