package com.sun.management.internal;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.MemoryUsage;
import javax.management.openmbean.OpenType;
import javax.management.openmbean.SimpleType;
import javax.management.openmbean.CompositeType;
import javax.management.openmbean.OpenDataException;
import com.sun.management.GcInfo;
import sun.management.Util;

/**
 * Helper class to build composite data.
 */
public class GcInfoBuilder {
    private final GarbageCollectorMXBean gc;
    private final String[] poolNames;
    private String[] allItemNames;

    // GC-specific composite type:
    // Each GarbageCollectorMXBean may have different GC-specific attributes
    // the CompositeType for the GcInfo could be different.
    private CompositeType gcInfoCompositeType;

    // GC-specific items
    private final int gcExtItemCount;
    private final String[] gcExtItemNames;
    private final String[] gcExtItemDescs;
    private final char[] gcExtItemTypes;

    GcInfoBuilder(GarbageCollectorMXBean gc, String[] poolNames) {
        this.gc = gc;
        this.poolNames = poolNames;
        this.gcExtItemCount = getNumGcExtAttributes(gc);
        this.gcExtItemNames = new String[gcExtItemCount];
        this.gcExtItemDescs = new String[gcExtItemCount];
        this.gcExtItemTypes = new char[gcExtItemCount];

        // Fill the information about extension attributes
        fillGcAttributeInfo(gc, gcExtItemCount, gcExtItemNames,
                            gcExtItemTypes, gcExtItemDescs);

        // lazily build the CompositeType for the GcInfo
        // including the GC-specific extension attributes
        this.gcInfoCompositeType = null;
    }

    GcInfo getLastGcInfo() {
        MemoryUsage[] usageBeforeGC = new MemoryUsage[poolNames.length];
        MemoryUsage[] usageAfterGC = new MemoryUsage[poolNames.length];
        Object[] values = new Object[gcExtItemCount];

        return getLastGcInfo0(gc, gcExtItemCount, values, gcExtItemTypes,
                              usageBeforeGC, usageAfterGC);
    }

    public String[] getPoolNames() {
        return poolNames.clone();
    }

    int getGcExtItemCount() {
        return gcExtItemCount;
    }

    // Returns the CompositeType for the GcInfo including
    // the extension attributes
    synchronized CompositeType getGcInfoCompositeType() {
        if (gcInfoCompositeType != null)
            return gcInfoCompositeType;

        // First, fill with the attributes in the GcInfo
        String[] gcInfoItemNames = GcInfoCompositeData.getBaseGcInfoItemNames();
        OpenType<?>[] gcInfoItemTypes = GcInfoCompositeData.getBaseGcInfoItemTypes();
        int numGcInfoItems = gcInfoItemNames.length;

        int itemCount = numGcInfoItems + gcExtItemCount;
        allItemNames = new String[itemCount];
        String[] allItemDescs = new String[itemCount];
        OpenType<?>[] allItemTypes = new OpenType<?>[itemCount];

        System.arraycopy(gcInfoItemNames, 0, allItemNames, 0, numGcInfoItems);
        System.arraycopy(gcInfoItemNames, 0, allItemDescs, 0, numGcInfoItems);
        System.arraycopy(gcInfoItemTypes, 0, allItemTypes, 0, numGcInfoItems);

        // Then fill with the extension GC-specific attributes, if any.
        if (gcExtItemCount > 0) {
            fillGcAttributeInfo(gc, gcExtItemCount, gcExtItemNames,
                                gcExtItemTypes, gcExtItemDescs);
            System.arraycopy(gcExtItemNames, 0, allItemNames,
                             numGcInfoItems, gcExtItemCount);
            System.arraycopy(gcExtItemDescs, 0, allItemDescs,
                             numGcInfoItems, gcExtItemCount);
            for (int i = numGcInfoItems, j = 0; j < gcExtItemCount; i++, j++) {
                switch (gcExtItemTypes[j]) {
                    case 'Z':
                        allItemTypes[i] = SimpleType.BOOLEAN;
                        break;
                    case 'B':
                        allItemTypes[i] = SimpleType.BYTE;
                        break;
                    case 'C':
                        allItemTypes[i] = SimpleType.CHARACTER;
                        break;
                    case 'S':
                        allItemTypes[i] = SimpleType.SHORT;
                        break;
                    case 'I':
                        allItemTypes[i] = SimpleType.INTEGER;
                        break;
                    case 'J':
                        allItemTypes[i] = SimpleType.LONG;
                        break;
                    case 'F':
                        allItemTypes[i] = SimpleType.FLOAT;
                        break;
                    case 'D':
                        allItemTypes[i] = SimpleType.DOUBLE;
                        break;
                    default:
                        throw new AssertionError(
                            "Unsupported type [" + gcExtItemTypes[i] + "]");
                }
            }
        }

        CompositeType gict = null;
        try {
            final String typeName =
                "sun.management." + gc.getName() + ".GcInfoCompositeType";

            gict = new CompositeType(typeName,
                                     "CompositeType for GC info for " +
                                         gc.getName(),
                                     allItemNames,
                                     allItemDescs,
                                     allItemTypes);
        } catch (OpenDataException e) {
            // shouldn't reach here
            throw new RuntimeException(e);
        }
        gcInfoCompositeType = gict;

        return gcInfoCompositeType;
    }

    synchronized String[] getItemNames() {
        if (allItemNames == null) {
            // initialize when forming the composite type
            getGcInfoCompositeType();
        }
        return allItemNames;
    }

    // Retrieve information about extension attributes
    private native int getNumGcExtAttributes(GarbageCollectorMXBean gc);
    private native void fillGcAttributeInfo(GarbageCollectorMXBean gc,
                                            int numAttributes,
                                            String[] attributeNames,
                                            char[] types,
                                            String[] descriptions);

    /**
     * Returns the last GcInfo
     *
     * @param gc GarbageCollectorMXBean that the gc info is associated with.
     * @param numExtAtts number of extension attributes
     * @param extAttValues Values of extension attributes to be filled.
     * @param before Memory usage before GC to be filled.
     * @param after Memory usage after GC to be filled.
     */
    private native GcInfo getLastGcInfo0(GarbageCollectorMXBean gc,
                                         int numExtAtts,
                                         Object[] extAttValues,
                                         char[] extAttTypes,
                                         MemoryUsage[] before,
                                         MemoryUsage[] after);
}
