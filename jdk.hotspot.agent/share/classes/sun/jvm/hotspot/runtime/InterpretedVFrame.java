package sun.jvm.hotspot.runtime;

import java.util.*;
import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.interpreter.*;
import sun.jvm.hotspot.oops.*;
import sun.jvm.hotspot.utilities.*;

public class InterpretedVFrame extends JavaVFrame {
  /** JVM state */
  public Method getMethod() {
    return getFrame().getInterpreterFrameMethod();
  }

  public StackValueCollection getLocals() {
    Method m = getMethod();

    int length = (int) m.getMaxLocals();

    if (m.isNative()) {
      // If the method is native, getMaxLocals is not telling the truth.
      // maxlocals then equals the size of parameters
      length = (int) m.getSizeOfParameters();
    }

    StackValueCollection result = new StackValueCollection(length);

    // Get oopmap describing oops and int for current bci
    OopMapCacheEntry oopMask = getMethod().getMaskFor(getBCI());

    // handle locals
    for(int i = 0; i < length; i++) {
      // Find stack location
      Address addr = addressOfLocalAt(i);

      // Depending on oop/int put it in the right package
      StackValue sv;
      if (oopMask.isOop(i)) {
        // oop value
        sv = new StackValue(addr.getOopHandleAt(0), 0);
      } else {
        // integer
        // Fetch a signed integer the size of a stack slot
        sv = new StackValue(addr.getCIntegerAt(0, VM.getVM().getAddressSize(), false));
      }
      result.add(sv);
    }

    return result;
  }

  public StackValueCollection getExpressions() {
    int length = getFrame().getInterpreterFrameExpressionStackSize();

    if (getMethod().isNative()) {
      // If the method is native, there is no expression stack
      length = 0;
    }

    int nofLocals = (int) getMethod().getMaxLocals();
    StackValueCollection result = new StackValueCollection(length);

    // Get oopmap describing oops and int for current bci
    OopMapCacheEntry oopMask = getMethod().getMaskFor(getBCI());

    for(int i = 0; i < length; i++) {
      // Find stack location
      Address addr = addressOfExpressionStackAt(i);

      // Depending on oop/int put it in the right package
      StackValue sv;
      if (oopMask.isOop(i + nofLocals)) {
        // oop value
        sv = new StackValue(addr.getOopHandleAt(0), 0);
      } else {
        // integer
        // Fetch a signed integer the size of a stack slot
        sv = new StackValue(addr.getCIntegerAt(0, VM.getVM().getAddressSize(), false));
      }
      result.add(sv);
    }

    return result;
  }

  /** Returns List<MonitorInfo> */
  public List<MonitorInfo> getMonitors() {
    List<MonitorInfo> result = new ArrayList<>(5);
    for (BasicObjectLock current = getFrame().interpreterFrameMonitorEnd();
         current.address().lessThan(getFrame().interpreterFrameMonitorBegin().address());
         current = getFrame().nextMonitorInInterpreterFrame(current)) {
      result.add(new MonitorInfo(current.obj(), current.lock(), false, false));
    }
    return result;
  }

  /** Test operation */
  public boolean isInterpretedFrame() { return true; }

  /** Package-internal constructor */
  InterpretedVFrame(Frame fr, RegisterMap regMap, JavaThread thread) {
    super(fr, regMap, thread);
  }

  /** Accessor for Byte Code Index (NOTE: access to BCP is not allowed
      in this system; see Frame.java) */
  public int getBCI() {
    return getFrame().getInterpreterFrameBCI();
  }

  /** Setter for Byte Code Index */
  // FIXME: not yet implementable
  //  public void setBCI(int bci) {
  //    getFrame().setInterpreterFrameBCI(bci);
  //  }

  public void verify() {
  }

  //--------------------------------------------------------------------------------
  // Internals only below this point
  //

  private Address addressOfLocalAt(int index) {
    if (Assert.ASSERTS_ENABLED) {
      Assert.that(getFrame().isInterpretedFrame(), "frame should be an interpreted frame");
    }
    return fr.addressOfInterpreterFrameLocal(index);
  }

  private Address addressOfExpressionStackAt(int index) {
    return fr.addressOfInterpreterFrameExpressionStackSlot(index);
  }
}
