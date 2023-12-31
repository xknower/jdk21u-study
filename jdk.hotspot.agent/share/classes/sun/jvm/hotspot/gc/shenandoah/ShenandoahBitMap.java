package sun.jvm.hotspot.gc.shenandoah;

import sun.jvm.hotspot.utilities.BitMap;
import sun.jvm.hotspot.utilities.BitMapInterface;

import java.util.HashMap;

public class ShenandoahBitMap implements BitMapInterface {
    private HashMap<ShenandoahHeapRegion, BitMap> regionToBitMap = new HashMap<>();
    private ShenandoahHeap heap;

    ShenandoahBitMap(ShenandoahHeap heap) {
        this.heap = heap;
    }

    @Override
    public boolean at(long offset) {
        ShenandoahHeapRegion region = heap.regionAtOffset(offset);
        BitMap bitmap = regionToBitMap.get(region);
        if (bitmap == null) {
            return false;
        } else {
            int index = toBitMapOffset(offset, region);
            return bitmap.at(index);
        }
    }

    @Override
    public void atPut(long offset, boolean value) {
        ShenandoahHeapRegion region = heap.regionAtOffset(offset);
        BitMap bitmap = getOrAddBitMap(region);
        int index = toBitMapOffset(offset, region);
        bitmap.atPut(index, value);
    }

    @Override
    public void clear() {
        for (BitMap bitMap : regionToBitMap.values()) {
            bitMap.clear();
        }
    }

    private int toBitMapOffset(long offset, ShenandoahHeapRegion region) {
        long regionSize = ShenandoahHeapRegion.regionSizeBytes();
        long regionOffset = region.index() * regionSize;
        long offsetInRegion = offset - regionOffset;

        if (offsetInRegion < 0 || offsetInRegion >= regionSize) {
            throw new RuntimeException("Unexpected negative offset: " + offsetInRegion);
        }
        return (int)(offsetInRegion >>> heap.getLogMinObjAlignmentInBytes());
    }

    private BitMap getOrAddBitMap(ShenandoahHeapRegion region) {
        BitMap bitMap = regionToBitMap.get(region);
        if (bitMap == null) {
            long regionSize = ShenandoahHeapRegion.regionSizeBytes();
            long maxNumObjects = regionSize >>> heap.getLogMinObjAlignmentInBytes();

            if (maxNumObjects > Integer.MAX_VALUE) {
                throw new RuntimeException("int overflow");
            }
            int intMaxNumObjects = (int)maxNumObjects;

            bitMap = new BitMap(intMaxNumObjects);
            regionToBitMap.put(region,  bitMap);
        }

        return bitMap;
    }
}
