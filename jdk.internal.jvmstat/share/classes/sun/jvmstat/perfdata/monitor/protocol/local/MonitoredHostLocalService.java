package sun.jvmstat.perfdata.monitor.protocol.local;

import sun.jvmstat.monitor.HostIdentifier;
import sun.jvmstat.monitor.MonitorException;
import sun.jvmstat.monitor.MonitoredHost;
import sun.jvmstat.monitor.MonitoredHostService;

public final class MonitoredHostLocalService implements MonitoredHostService {

    @Override
    public MonitoredHost getMonitoredHost(HostIdentifier hostId)
            throws MonitorException {
        return new MonitoredHostProvider(hostId);
    }

    @Override
    public String getScheme() {
        return "local";
    }

}
