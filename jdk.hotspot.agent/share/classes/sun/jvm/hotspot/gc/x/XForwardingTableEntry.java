package sun.jvm.hotspot.gc.x;

import sun.jvm.hotspot.debugger.Address;

class XForwardingTableEntry {
    private Address entry;

    XForwardingTableEntry(Address addr) {
        entry = addr;
    }

    private static long empty() {
        return ~0L;
    }

    boolean is_empty() {
        return entry.asLongValue() == empty();
    }

    Address to_offset() {
        return entry.andWithMask((1L << 42) - 1);
    }

    long from_index() {
        return entry.asLongValue() >>> 42;
    }

    public String toString() {
        return entry + " - from_index: " + from_index() + " to_offset: " + to_offset();
    }
}
