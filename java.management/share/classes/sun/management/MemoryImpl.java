package sun.management;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.MemoryNotificationInfo;
import java.lang.management.MemoryManagerMXBean;
import java.lang.management.MemoryPoolMXBean;
import javax.management.ObjectName;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.openmbean.CompositeData;

/**
 * Implementation class for the memory subsystem.
 * Standard and committed hotspot-specific metrics if any.
 *
 * ManagementFactory.getMemoryMXBean() returns an instance
 * of this class.
 */
class MemoryImpl extends NotificationEmitterSupport
                 implements MemoryMXBean {

    private final VMManagement jvm;

    private static MemoryPoolMXBean[] pools = null;
    private static MemoryManagerMXBean[] mgrs = null;

    /**
     * Constructor of MemoryImpl class
     */
    MemoryImpl(VMManagement vm) {
        this.jvm = vm;
    }

    @SuppressWarnings("deprecation")
    public int getObjectPendingFinalizationCount() {
        return jdk.internal.misc.VM.getFinalRefCount();
    }

    public void gc() {
        Runtime.getRuntime().gc();
    }

    // Need to make a VM call to get coherent value
    public MemoryUsage getHeapMemoryUsage() {
        return getMemoryUsage0(true);
    }

    public MemoryUsage getNonHeapMemoryUsage() {
        return getMemoryUsage0(false);
    }

    public boolean isVerbose() {
        return jvm.getVerboseGC();
    }

    public void setVerbose(boolean value) {
        Util.checkControlAccess();

        setVerboseGC(value);
    }

    // The current Hotspot implementation does not support
    // dynamically add or remove memory pools & managers.
    static synchronized MemoryPoolMXBean[] getMemoryPools() {
        if (pools == null) {
            pools = getMemoryPools0();
        }
        return pools;
    }
    static synchronized MemoryManagerMXBean[] getMemoryManagers() {
        if (mgrs == null) {
            mgrs = getMemoryManagers0();
        }
        return mgrs;
    }
    private static native MemoryPoolMXBean[] getMemoryPools0();
    private static native MemoryManagerMXBean[] getMemoryManagers0();
    private native MemoryUsage getMemoryUsage0(boolean heap);
    private native void setVerboseGC(boolean value);

    private static final String notifName =
        "javax.management.Notification";
    private static final String[] notifTypes = {
        MemoryNotificationInfo.MEMORY_THRESHOLD_EXCEEDED,
        MemoryNotificationInfo.MEMORY_COLLECTION_THRESHOLD_EXCEEDED
    };
    private static final String[] notifMsgs  = {
        "Memory usage exceeds usage threshold",
        "Memory usage exceeds collection usage threshold"
    };

    public MBeanNotificationInfo[] getNotificationInfo() {
        return new MBeanNotificationInfo[] {
            new MBeanNotificationInfo(notifTypes, notifName, "Memory Notification")
        };
    }

    private static String getNotifMsg(String notifType) {
        for (int i = 0; i < notifTypes.length; i++) {
            if (notifType == notifTypes[i]) {
                return notifMsgs[i];
            }
        }
        return "Unknown message";
    }

    private static long seqNumber = 0;
    private static long getNextSeqNumber() {
        return ++seqNumber;
    }

    static void createNotification(String notifType,
                                   String poolName,
                                   MemoryUsage usage,
                                   long count) {
        MemoryImpl mbean = (MemoryImpl) ManagementFactory.getMemoryMXBean();
        if (!mbean.hasListeners()) {
            // if no listener is registered.
            return;
        }
        long timestamp = System.currentTimeMillis();
        String msg = getNotifMsg(notifType);
        Notification notif = new Notification(notifType,
                                              mbean.getObjectName(),
                                              getNextSeqNumber(),
                                              timestamp,
                                              msg);
        MemoryNotificationInfo info =
            new MemoryNotificationInfo(poolName,
                                       usage,
                                       count);
        CompositeData cd =
            MemoryNotifInfoCompositeData.toCompositeData(info);
        notif.setUserData(cd);
        mbean.sendNotification(notif);
    }

    public ObjectName getObjectName() {
        return Util.newObjectName(ManagementFactory.MEMORY_MXBEAN_NAME);
    }

}
