package jdk.jfr.internal.consumer.filter;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Write cache and LEB128 encoder
 */
final class RecordingOutput implements Closeable {
    private final RandomAccessFile file;
    private final byte[] buffer = new byte[16384];
    private int bufferPosition;
    private long position;

    public RecordingOutput(File file) throws IOException {
        this.file = new RandomAccessFile(file, "rw");
    }

    public void writeByte(byte value) throws IOException {
        if (!(bufferPosition < buffer.length)) {
            flush();
        }
        buffer[bufferPosition++] = value;
        position++;
    }

    public void writeRawLong(long v) throws IOException {
        writeByte((byte) ((v >> 56) & 0xff));
        writeByte((byte) ((v >> 48) & 0xff));
        writeByte((byte) ((v >> 40) & 0xff));
        writeByte((byte) ((v >> 32) & 0xff));
        writeByte((byte) ((v >> 24) & 0xff));
        writeByte((byte) ((v >> 16) & 0xff));
        writeByte((byte) ((v >> 8) & 0xff));
        writeByte((byte) ((v) & 0xff));
    }

    public void writePaddedUnsignedInt(long value) throws IOException {
        if (value < 0) {
            throw new IOException("Padded value can't be negative");
        }
        if (value >= 1 << 28) {
            throw new IOException("Padded value must fit four bytes");
        }
        byte b0 = (byte) (value | 0x80);
        byte b1 = (byte) (value >> 7 | 0x80);
        byte b2 = (byte) (value >> 14 | 0x80);
        byte b3 = (byte) (value >> 21);
        writeByte(b0);
        writeByte(b1);
        writeByte(b2);
        writeByte(b3);
    }

    // Essentially copied from EventWriter#putLong
    public void writeLong(long v) throws IOException {
        if ((v & ~0x7FL) == 0L) {
            writeByte((byte) v); // 0-6
            return;
        }
        writeByte((byte) (v | 0x80L)); // 0-6
        v >>>= 7;
        if ((v & ~0x7FL) == 0L) {
            writeByte((byte) v); // 7-13
            return;
        }
        writeByte((byte) (v | 0x80L)); // 7-13
        v >>>= 7;
        if ((v & ~0x7FL) == 0L) {
            writeByte((byte) v); // 14-20
            return;
        }
        writeByte((byte) (v | 0x80L)); // 14-20
        v >>>= 7;
        if ((v & ~0x7FL) == 0L) {
            writeByte((byte) v); // 21-27
            return;
        }
        writeByte((byte) (v | 0x80L)); // 21-27
        v >>>= 7;
        if ((v & ~0x7FL) == 0L) {
            writeByte((byte) v); // 28-34
            return;
        }
        writeByte((byte) (v | 0x80L)); // 28-34
        v >>>= 7;
        if ((v & ~0x7FL) == 0L) {
            writeByte((byte) v); // 35-41
            return;
        }
        writeByte((byte) (v | 0x80L)); // 35-41
        v >>>= 7;
        if ((v & ~0x7FL) == 0L) {
            writeByte((byte) v); // 42-48
            return;
        }
        writeByte((byte) (v | 0x80L)); // 42-48
        v >>>= 7;

        if ((v & ~0x7FL) == 0L) {
            writeByte((byte) v); // 49-55
            return;
        }
        writeByte((byte) (v | 0x80L)); // 49-55
        writeByte((byte) (v >>> 7)); // 56-63, last byte as is.
    }

    public void position(long pos) throws IOException {
        flush();
        position = pos;
        file.seek(position);
    }

    public long position() throws IOException {
        return position;
    }

    public void flush() throws IOException {
        file.write(buffer, 0, bufferPosition);
        bufferPosition = 0;
    }

    @Override
    public void close() throws IOException {
        flush();
        file.close();
    }
}
