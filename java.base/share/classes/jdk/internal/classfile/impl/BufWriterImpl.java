package jdk.internal.classfile.impl;


import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

import jdk.internal.classfile.BufWriter;
import jdk.internal.classfile.WritableElement;
import jdk.internal.classfile.constantpool.ClassEntry;
import jdk.internal.classfile.constantpool.ConstantPool;
import jdk.internal.classfile.constantpool.ConstantPoolBuilder;
import jdk.internal.classfile.constantpool.PoolEntry;

public final class BufWriterImpl implements BufWriter {

    private final ConstantPoolBuilder constantPool;
    private LabelContext labelContext;
    private final ClassEntry thisClass;
    private final int majorVersion;
    byte[] elems;
    int offset = 0;

    public BufWriterImpl(ConstantPoolBuilder constantPool) {
        this(constantPool, 64, null, 0);
    }

    public BufWriterImpl(ConstantPoolBuilder constantPool, int initialSize) {
        this(constantPool, initialSize, null, 0);
    }

    public BufWriterImpl(ConstantPoolBuilder constantPool, int initialSize, ClassEntry thisClass, int majorVersion) {
        this.constantPool = constantPool;
        elems = new byte[initialSize];
        this.thisClass = thisClass;
        this.majorVersion = majorVersion;
    }

    @Override
    public ConstantPoolBuilder constantPool() {
        return constantPool;
    }

    public LabelContext labelContext() {
        return labelContext;
    }

    public void setLabelContext(LabelContext labelContext) {
        this.labelContext = labelContext;
    }
    @Override
    public boolean canWriteDirect(ConstantPool other) {
        return constantPool.canWriteDirect(other);
    }

    public ClassEntry thisClass() {
        return thisClass;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    @Override
    public void writeU1(int x) {
        writeIntBytes(1, x);
    }

    @Override
    public void writeU2(int x) {
        writeIntBytes(2, x);
    }

    @Override
    public void writeInt(int x) {
        writeIntBytes(4, x);
    }

    @Override
    public void writeFloat(float x) {
        writeInt(Float.floatToIntBits(x));
    }

    @Override
    public void writeLong(long x) {
        writeIntBytes(8, x);
    }

    @Override
    public void writeDouble(double x) {
        writeLong(Double.doubleToLongBits(x));
    }

    @Override
    public void writeBytes(byte[] arr) {
        writeBytes(arr, 0, arr.length);
    }

    @Override
    public void writeBytes(BufWriter other) {
        BufWriterImpl o = (BufWriterImpl) other;
        writeBytes(o.elems, 0, o.offset);
    }

    @Override
    public void writeBytes(byte[] arr, int start, int length) {
        reserveSpace(length);
        System.arraycopy(arr, start, elems, offset, length);
        offset += length;
    }

    @Override
    public void patchInt(int offset, int size, int value) {
        int prevOffset = this.offset;
        this.offset = offset;
        writeIntBytes(size, value);
        this.offset = prevOffset;
    }

    @Override
    public void writeIntBytes(int intSize, long intValue) {
        reserveSpace(intSize);
        for (int i = 0; i < intSize; i++) {
            elems[offset++] = (byte) ((intValue >> 8 * (intSize - i - 1)) & 0xFF);
        }
    }

    @Override
    public void reserveSpace(int freeBytes) {
        if (offset + freeBytes > elems.length) {
            int newsize = elems.length * 2;
            while (offset + freeBytes > newsize) {
                newsize *= 2;
            }
            elems = Arrays.copyOf(elems, newsize);
        }
    }

    @Override
    public int size() {
        return offset;
    }

    @Override
    public ByteBuffer asByteBuffer() {
        return ByteBuffer.wrap(elems, 0, offset);
    }

    @Override
    public void copyTo(byte[] array, int bufferOffset) {
        System.arraycopy(elems, 0, array, bufferOffset, size());
    }

    // writeIndex methods ensure that any CP info written
    // is relative to the correct constant pool

    @Override
    public void writeIndex(PoolEntry entry) {
        int idx = AbstractPoolEntry.maybeClone(constantPool, entry).index();
        if (idx < 1 || idx > Character.MAX_VALUE)
            throw new IllegalArgumentException(idx + " is not a valid index. Entry: " + entry);
        writeU2(idx);
    }

    @Override
    public void writeIndexOrZero(PoolEntry entry) {
        if (entry == null || entry.index() == 0)
            writeU2(0);
        else
            writeIndex(entry);
    }

    @Override
    public<T extends WritableElement<?>> void writeList(List<T> list) {
        writeU2(list.size());
        for (T t : list) {
            t.writeTo(this);
        }
    }

    @Override
    public void writeListIndices(List<? extends PoolEntry> list) {
        writeU2(list.size());
        for (PoolEntry info : list) {
            writeIndex(info);
        }
    }
}
