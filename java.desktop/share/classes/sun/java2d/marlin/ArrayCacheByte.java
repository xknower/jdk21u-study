package sun.java2d.marlin;

import static sun.java2d.marlin.ArrayCacheConst.ARRAY_SIZES;
import static sun.java2d.marlin.ArrayCacheConst.BUCKETS;
import static sun.java2d.marlin.ArrayCacheConst.MAX_ARRAY_SIZE;

import static sun.java2d.marlin.MarlinConst.DO_STATS;
import static sun.java2d.marlin.MarlinConst.DO_CHECKS;
import static sun.java2d.marlin.MarlinConst.DO_CLEAN_DIRTY;
import static sun.java2d.marlin.MarlinConst.DO_LOG_WIDEN_ARRAY;
import static sun.java2d.marlin.MarlinConst.DO_LOG_OVERSIZE;

import static sun.java2d.marlin.MarlinUtils.logInfo;
import static sun.java2d.marlin.MarlinUtils.logException;

import java.lang.ref.WeakReference;
import java.util.Arrays;

import sun.java2d.marlin.ArrayCacheConst.BucketStats;
import sun.java2d.marlin.ArrayCacheConst.CacheStats;

/*
 * Note that the ArrayCache[Byte/Double/Int] files are nearly identical except
 * for their array type [byte/double/int] and class name differences.
 * ArrayCache[Byte/Double/Int] class deals with dirty arrays.
 */

final class ArrayCacheByte {

    /* members */
    private final int bucketCapacity;
    private WeakReference<Bucket[]> refBuckets = null;
    final CacheStats stats;

    ArrayCacheByte(final int bucketCapacity) {
        this.bucketCapacity = bucketCapacity;
        this.stats = (DO_STATS) ?
            new CacheStats("ArrayCacheByte(Dirty)") : null;
    }

    Bucket getCacheBucket(final int length) {
        final int bucket = ArrayCacheConst.getBucket(length);
        return getBuckets()[bucket];
    }

    private Bucket[] getBuckets() {
        // resolve reference:
        Bucket[] buckets = (refBuckets != null) ? refBuckets.get() : null;

        // create a new buckets ?
        if (buckets == null) {
            buckets = new Bucket[BUCKETS];

            for (int i = 0; i < BUCKETS; i++) {
                buckets[i] = new Bucket(ARRAY_SIZES[i], bucketCapacity,
                        (DO_STATS) ? stats.bucketStats[i] : null);
            }

            // update weak reference:
            refBuckets = new WeakReference<>(buckets);
        }
        return buckets;
    }

    Reference createRef(final int initialSize) {
        return new Reference(this, initialSize);
    }

    static final class Reference {

        // initial array reference (direct access)
        final byte[] initial;
        private final ArrayCacheByte cache;

        Reference(final ArrayCacheByte cache, final int initialSize) {
            this.cache = cache;
            this.initial = createArray(initialSize);
            if (DO_STATS) {
                cache.stats.totalInitial += initialSize;
            }
        }

        byte[] getArray(final int length) {
            if (length <= MAX_ARRAY_SIZE) {
                return cache.getCacheBucket(length).getArray();
            }
            if (DO_STATS) {
                cache.stats.oversize++;
            }
            if (DO_LOG_OVERSIZE) {
                logInfo("ArrayCacheByte(Dirty): "
                        + "getArray[oversize]: length=\t" + length);
            }
            return createArray(length);
        }

        byte[] widenArray(final byte[] array, final int usedSize,
                          final int needSize)
        {
            final int length = array.length;
            if (DO_CHECKS && length >= needSize) {
                return array;
            }
            if (DO_STATS) {
                cache.stats.resize++;
            }

            // maybe change bucket:
            // ensure getNewSize() > newSize:
            final byte[] res = getArray(ArrayCacheConst.getNewSize(usedSize, needSize));

            // use wrapper to ensure proper copy:
            System.arraycopy(array, 0, res, 0, usedSize); // copy only used elements

            // maybe return current array:
            putArray(array, 0, usedSize); // ensure array is cleared

            if (DO_LOG_WIDEN_ARRAY) {
                logInfo("ArrayCacheByte(Dirty): "
                        + "widenArray[" + res.length
                        + "]: usedSize=\t" + usedSize + "\tlength=\t" + length
                        + "\tneeded length=\t" + needSize);
            }
            return res;
        }

        boolean doCleanRef(final byte[] array) {
            return DO_CLEAN_DIRTY || (array != initial);
        }

        byte[] putArray(final byte[] array)
        {
            // dirty array helper:
            return putArray(array, 0, array.length);
        }

        byte[] putArray(final byte[] array, final int fromIndex,
                        final int toIndex)
        {
            if (array.length <= MAX_ARRAY_SIZE) {
                if (DO_CLEAN_DIRTY && (toIndex != 0)) {
                    // clean-up array of dirty part[fromIndex; toIndex[
                    fill(array, fromIndex, toIndex, (byte)0);
                }
                // ensure to never store initial arrays in cache:
                if (array != initial) {
                    cache.getCacheBucket(array.length).putArray(array);
                }
            }
            return initial;
        }
    }

    static final class Bucket {

        private int tail = 0;
        private final int arraySize;
        private final byte[][] arrays;
        private final BucketStats stats;

        Bucket(final int arraySize,
               final int capacity, final BucketStats stats)
        {
            this.arraySize = arraySize;
            this.stats = stats;
            this.arrays = new byte[capacity][];
        }

        byte[] getArray() {
            if (DO_STATS) {
                stats.getOp++;
            }
            // use cache:
            if (tail != 0) {
                final byte[] array = arrays[--tail];
                arrays[tail] = null;
                return array;
            }
            if (DO_STATS) {
                stats.createOp++;
            }
            return createArray(arraySize);
        }

        void putArray(final byte[] array)
        {
            if (DO_CHECKS && (array.length != arraySize)) {
                logInfo("ArrayCacheByte(Dirty): "
                        + "bad length = " + array.length);
                return;
            }
            if (DO_STATS) {
                stats.returnOp++;
            }
            // fill cache:
            if (arrays.length > tail) {
                arrays[tail++] = array;

                if (DO_STATS) {
                    stats.updateMaxSize(tail);
                }
            } else if (DO_CHECKS) {
                logInfo("ArrayCacheByte(Dirty): "
                        + "array capacity exceeded !");
            }
        }
    }

    static byte[] createArray(final int length) {
        return new byte[length];
    }

    static void fill(final byte[] array, final int fromIndex,
                     final int toIndex, final byte value)
    {
        // clear array data:
        Arrays.fill(array, fromIndex, toIndex, value);
        if (DO_CHECKS) {
            check(array, fromIndex, toIndex, value);
        }
    }

    static void check(final byte[] array, final int fromIndex,
                      final int toIndex, final byte value)
    {
        if (DO_CHECKS) {
            // check zero on full array:
            for (int i = 0; i < array.length; i++) {
                if (array[i] != value) {
                    logException("Invalid value at: " + i + " = " + array[i]
                            + " from: " + fromIndex + " to: " + toIndex + "\n"
                            + Arrays.toString(array), new Throwable());

                    // ensure array is correctly filled:
                    Arrays.fill(array, value);

                    return;
                }
            }
        }
    }
}
