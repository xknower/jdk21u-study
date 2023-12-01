package sun.jvm.hotspot.gc.x;

class XHash {
    private static long uint32(long value) {
        return value & 0xFFFFFFFFL;
    }

    static long uint32_to_uint32(long key) {
        key = uint32(~key + (key << 15));
        key = uint32(key ^ (key >>> 12));
        key = uint32(key + (key << 2));
        key = uint32(key ^ (key >>> 4));
        key = uint32(key * 2057);
        key = uint32(key ^ (key >>> 16));
        return key;
    }
}
