package sun.jvm.hotspot.debugger.cdbg.basic;

import java.util.*;
import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.debugger.cdbg.*;
import sun.jvm.hotspot.utilities.AddressOps;

public class BasicLineNumberMapping {
  private List<BasicLineNumberInfo> infoList;

  public BasicLineNumberMapping() {
  }

  /** Add line number information for the given PC. The end PC may be
      a very loose approximation (i.e., the end of the given DLL) if
      that information is not available in the debug information.
      recomputeEndPCs() will recompute them if needed. */
  public void addLineNumberInfo(BasicLineNumberInfo info) {
    if (infoList == null) {
      infoList = new ArrayList<>();
    }
    infoList.add(info);
  }

  /** Sort the line number information by increasing starting program
      counter. This must be done before any queries are made. */
  public void sort() {
    if (infoList == null) return;
    infoList.sort(new Comparator<>() {
        public int compare(BasicLineNumberInfo l1, BasicLineNumberInfo l2) {
          Address a1 = l1.getStartPC();
          Address a2 = l2.getStartPC();
          if (AddressOps.lt(a1, a2)) { return -1; }
          if (AddressOps.gt(a1, a2)) { return 1; }
          return 0;
        }
      });
  }

  /** Recomputes the ending PCs of each interval based on the starting
      PC of the next one. If this needs to be called, must be called
      after sort(). */
  public void recomputeEndPCs() {
    if (infoList == null) return;
    for (int i = 0; i < infoList.size() - 1; i++) {
      BasicLineNumberInfo i1 = get(i);
      BasicLineNumberInfo i2 = get(i + 1);
      i1.setEndPC(i2.getStartPC());
    }
  }

  public BasicLineNumberInfo lineNumberForPC(Address pc) throws DebuggerException {
    if (infoList == null) return null;
    return searchLineNumbers(pc, 0, infoList.size() - 1);
  }

  public void iterate(LineNumberVisitor v) {
    if (infoList == null) return;
    for (int i = 0; i < infoList.size(); i++) {
      v.doLineNumber(get(i));
    }
  }

  //----------------------------------------------------------------------
  // Internals only below this point
  //

  private BasicLineNumberInfo get(int i) {
    return infoList.get(i);
  }

  private BasicLineNumberInfo searchLineNumbers(Address addr, int lowIdx, int highIdx) {
    if (highIdx < lowIdx) return null;
    if (lowIdx == highIdx) {
      // Base case: see whether start PC is less than or equal to addr
      if (check(addr, lowIdx)) {
        return get(lowIdx);
      } else {
        return null;
      }
    } else if (lowIdx == highIdx - 1) {
      if (check(addr, lowIdx)) {
        return get(lowIdx);
      } else if (check(addr, highIdx)) {
        return get(highIdx);
      } else {
        return null;
      }
    }
    int midIdx = (lowIdx + highIdx) >> 1;
    BasicLineNumberInfo info = get(midIdx);
    if (AddressOps.lt(addr, info.getStartPC())) {
      // Always move search down
      return searchLineNumbers(addr, lowIdx, midIdx);
    } else if (AddressOps.equal(addr, info.getStartPC())) {
      return info;
    } else {
      // Move search up
      return searchLineNumbers(addr, midIdx, highIdx);
    }
  }

  private boolean check(Address addr, int idx) {
    BasicLineNumberInfo info = get(idx);
    if (AddressOps.lte(info.getStartPC(), addr)) {
      return true;
    } else {
      return false;
    }
  }
}
