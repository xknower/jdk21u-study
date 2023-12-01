package java.nio;

import jdk.internal.misc.ScopedMemoryAccess;
import jdk.internal.util.ArraysSupport;

/**
 * Mismatch methods for buffers
 */
final class BufferMismatch {

    static final ScopedMemoryAccess SCOPED_MEMORY_ACCESS = ScopedMemoryAccess.getScopedMemoryAccess();

    static int mismatch(ByteBuffer a, int aOff, ByteBuffer b, int bOff, int length) {
        int i = 0;
        if (length > 7) {
            if (a.get(aOff) != b.get(bOff))
                return 0;
            i = SCOPED_MEMORY_ACCESS.vectorizedMismatch(a.session(), b.session(),
                    a.base(), a.address + aOff,
                    b.base(), b.address + bOff,
                    length,
                    ArraysSupport.LOG2_ARRAY_BYTE_INDEX_SCALE);
            if (i >= 0) return i;
            i = length - ~i;
        }
        for (; i < length; i++) {
            if (a.get(aOff + i) != b.get(bOff + i))
                return i;
        }
        return -1;
    }

    static int mismatch(CharBuffer a, int aOff, CharBuffer b, int bOff, int length) {
        int i = 0;
        // Ensure only heap or off-heap buffer instances use the
        // vectorized mismatch. If either buffer is a StringCharBuffer
        // (order is null) then the slow path is taken
        if (length > 3 && a.charRegionOrder() == b.charRegionOrder()
            && a.charRegionOrder() != null && b.charRegionOrder() != null) {
            if (a.get(aOff) != b.get(bOff))
                return 0;
            i = SCOPED_MEMORY_ACCESS.vectorizedMismatch(a.session(), b.session(),
                    a.base(), a.address + (aOff << ArraysSupport.LOG2_ARRAY_CHAR_INDEX_SCALE),
                    b.base(), b.address + (bOff << ArraysSupport.LOG2_ARRAY_CHAR_INDEX_SCALE),
                    length,
                    ArraysSupport.LOG2_ARRAY_CHAR_INDEX_SCALE);
            if (i >= 0) return i;
            i = length - ~i;
        }
        for (; i < length; i++) {
            if (a.get(aOff + i) != b.get(bOff + i))
                return i;
        }
        return -1;
    }

    static int mismatch(ShortBuffer a, int aOff, ShortBuffer b, int bOff, int length) {
        int i = 0;
        if (length > 3 && a.order() == b.order()) {
            if (a.get(aOff) != b.get(bOff))
                return 0;
            i = SCOPED_MEMORY_ACCESS.vectorizedMismatch(a.session(), b.session(),
                    a.base(), a.address + (aOff << ArraysSupport.LOG2_ARRAY_SHORT_INDEX_SCALE),
                    b.base(), b.address + (bOff << ArraysSupport.LOG2_ARRAY_SHORT_INDEX_SCALE),
                    length,
                    ArraysSupport.LOG2_ARRAY_SHORT_INDEX_SCALE);
            if (i >= 0) return i;
            i = length - ~i;
        }
        for (; i < length; i++) {
            if (a.get(aOff + i) != b.get(bOff + i))
                return i;
        }
        return -1;
    }

    static int mismatch(IntBuffer a, int aOff, IntBuffer b, int bOff, int length) {
        int i = 0;
        if (length > 1 && a.order() == b.order()) {
            if (a.get(aOff) != b.get(bOff))
                return 0;
            i = SCOPED_MEMORY_ACCESS.vectorizedMismatch(a.session(), b.session(),
                    a.base(), a.address + (aOff << ArraysSupport.LOG2_ARRAY_INT_INDEX_SCALE),
                    b.base(), b.address + (bOff << ArraysSupport.LOG2_ARRAY_INT_INDEX_SCALE),
                    length,
                    ArraysSupport.LOG2_ARRAY_INT_INDEX_SCALE);
            if (i >= 0) return i;
            i = length - ~i;
        }
        for (; i < length; i++) {
            if (a.get(aOff + i) != b.get(bOff + i))
                return i;
        }
        return -1;
    }

    static int mismatch(FloatBuffer a, int aOff, FloatBuffer b, int bOff, int length) {
        int i = 0;
        if (length > 1 && a.order() == b.order()) {
            if (Float.floatToRawIntBits(a.get(aOff)) == Float.floatToRawIntBits(b.get(bOff))) {
                i = SCOPED_MEMORY_ACCESS.vectorizedMismatch(a.session(), b.session(),
                        a.base(), a.address + (aOff << ArraysSupport.LOG2_ARRAY_FLOAT_INDEX_SCALE),
                        b.base(), b.address + (bOff << ArraysSupport.LOG2_ARRAY_FLOAT_INDEX_SCALE),
                        length,
                        ArraysSupport.LOG2_ARRAY_FLOAT_INDEX_SCALE);
            }
            // Mismatched
            if (i >= 0) {
                // Check if mismatch is not associated with two NaN values; and
                // is not associated with +0 and -0
                float av = a.get(aOff + i);
                float bv = b.get(bOff + i);
                if (av != bv && (!Float.isNaN(av) || !Float.isNaN(bv)))
                    return i;

                // Fall back to slow mechanism
                // ISSUE: Consider looping over vectorizedMismatch adjusting ranges
                // However, requires that returned value be relative to input ranges
                i++;
            }
            // Matched
            else {
                i = length - ~i;
            }
        }
        for (; i < length; i++) {
            float av = a.get(aOff + i);
            float bv = b.get(bOff + i);
            if (av != bv && (!Float.isNaN(av) || !Float.isNaN(bv)))
                return i;
        }
        return -1;
    }

    static int mismatch(LongBuffer a, int aOff, LongBuffer b, int bOff, int length) {
        int i = 0;
        if (length > 0 && a.order() == b.order()) {
            if (a.get(aOff) != b.get(bOff))
                return 0;
            i = SCOPED_MEMORY_ACCESS.vectorizedMismatch(a.session(), b.session(),
                    a.base(), a.address + (aOff << ArraysSupport.LOG2_ARRAY_LONG_INDEX_SCALE),
                    b.base(), b.address + (bOff << ArraysSupport.LOG2_ARRAY_LONG_INDEX_SCALE),
                    length,
                    ArraysSupport.LOG2_ARRAY_LONG_INDEX_SCALE);
            return i >= 0 ? i : -1;
        }
        for (; i < length; i++) {
            if (a.get(aOff + i) != b.get(bOff + i))
                return i;
        }
        return -1;
    }

    static int mismatch(DoubleBuffer a, int aOff, DoubleBuffer b, int bOff, int length) {
        int i = 0;
        if (length > 0 && a.order() == b.order()) {
            if (Double.doubleToRawLongBits(a.get(aOff)) == Double.doubleToRawLongBits(b.get(bOff))) {
                i = SCOPED_MEMORY_ACCESS.vectorizedMismatch(a.session(), b.session(),
                        a.base(), a.address + (aOff << ArraysSupport.LOG2_ARRAY_DOUBLE_INDEX_SCALE),
                        b.base(), b.address + (bOff << ArraysSupport.LOG2_ARRAY_DOUBLE_INDEX_SCALE),
                        length,
                        ArraysSupport.LOG2_ARRAY_DOUBLE_INDEX_SCALE);
            }
            // Mismatched
            if (i >= 0) {
                // Check if mismatch is not associated with two NaN values; and
                // is not associated with +0 and -0
                double av = a.get(aOff + i);
                double bv = b.get(bOff + i);
                if (av != bv && (!Double.isNaN(av) || !Double.isNaN(bv)))
                    return i;

                // Fall back to slow mechanism
                // ISSUE: Consider looping over vectorizedMismatch adjusting ranges
                // However, requires that returned value be relative to input ranges
                i++;
            }
            // Matched
            else {
                return -1;
            }
        }
        for (; i < length; i++) {
            double av = a.get(aOff + i);
            double bv = b.get(bOff + i);
            if (av != bv && (!Double.isNaN(av) || !Double.isNaN(bv)))
                return i;
        }
        return -1;
    }
}
