package jdk.jfr.events;

import jdk.jfr.Category;
import jdk.jfr.Description;
import jdk.jfr.Label;
import jdk.jfr.Name;
import jdk.jfr.Timespan;
import jdk.jfr.internal.Type;

@Name(Type.EVENT_NAME_PREFIX + "ContainerCPUUsage")
@Label("CPU Usage")
@Category({"Operating System", "Processor"})
@Description("Container CPU usage related information")
public class ContainerCPUUsageEvent extends AbstractJDKEvent {
  @Label("CPU Time")
  @Description("Aggregate time consumed by all tasks in the container")
  @Timespan
  public long cpuTime;

  @Label("CPU User Time")
  @Description("Aggregate user time consumed by all tasks in the container")
  @Timespan
  public long cpuUserTime;

  @Label("CPU System Time")
  @Description("Aggregate system time consumed by all tasks in the container")
  @Timespan
  public long cpuSystemTime;
}
