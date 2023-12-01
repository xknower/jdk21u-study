package sun.management;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import javax.management.ObjectName;

/**
 * Implementation class for the garbage collector.
 *
 * ManagementFactory.getGarbageCollectorMXBeans() returns a list
 * of instances of this class.
 */
public class GarbageCollectorImpl extends MemoryManagerImpl
    implements GarbageCollectorMXBean {

    protected GarbageCollectorImpl(String name) {
        super(name);
    }

    @Override
    public native long getCollectionCount();

    @Override
    public native long getCollectionTime();

    @Override
    public ObjectName getObjectName() {
        return Util.newObjectName(ManagementFactory.GARBAGE_COLLECTOR_MXBEAN_DOMAIN_TYPE, getName());
    }
}
