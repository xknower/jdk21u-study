package sun.jvm.hotspot.debugger.cdbg;

import java.util.*;
import sun.jvm.hotspot.debugger.*;

// a comparator used to sort LoadObjects by base address

public class LoadObjectComparator implements Comparator<LoadObject> {
   public int compare(LoadObject lo1, LoadObject lo2) {
      Address base1 = lo1.getBase();
      Address base2 = lo2.getBase();
      long diff = base1.minus(base2);
      return (diff == 0)? 0 : ((diff < 0)? -1 : +1);
   }

   public boolean equals(Object o) {
      return o instanceof LoadObjectComparator;
   }
}
