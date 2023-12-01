package sun.jvm.hotspot.runtime.linux_riscv64;

import java.io.*;
import java.util.*;
import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.debugger.riscv64.*;
import sun.jvm.hotspot.runtime.*;
import sun.jvm.hotspot.runtime.riscv64.*;
import sun.jvm.hotspot.types.*;
import sun.jvm.hotspot.utilities.*;
import sun.jvm.hotspot.utilities.Observable;
import sun.jvm.hotspot.utilities.Observer;

public class LinuxRISCV64JavaThreadPDAccess implements JavaThreadPDAccess {
  private static AddressField  lastJavaFPField;
  private static AddressField  osThreadField;

  // Field from OSThread
  private static CIntegerField osThreadThreadIDField;

  // This is currently unneeded but is being kept in case we change
  // the currentFrameGuess algorithm
  private static final long GUESS_SCAN_RANGE = 128 * 1024;

  static {
    VM.registerVMInitializedObserver(new Observer() {
        public void update(Observable o, Object data) {
          initialize(VM.getVM().getTypeDataBase());
        }
      });
  }

  private static synchronized void initialize(TypeDataBase db) {
    Type type = db.lookupType("JavaThread");
    osThreadField           = type.getAddressField("_osthread");

    Type anchorType = db.lookupType("JavaFrameAnchor");
    lastJavaFPField         = anchorType.getAddressField("_last_Java_fp");

    Type osThreadType = db.lookupType("OSThread");
    osThreadThreadIDField   = osThreadType.getCIntegerField("_thread_id");
  }

  public Address getLastJavaFP(Address addr) {
    return lastJavaFPField.getValue(addr.addOffsetTo(sun.jvm.hotspot.runtime.JavaThread.getAnchorField().getOffset()));
  }

  public Address getLastJavaPC(Address addr) {
    return null;
  }

  public Address getBaseOfStackPointer(Address addr) {
    return null;
  }

  public Frame getLastFramePD(JavaThread thread, Address addr) {
    Address fp = thread.getLastJavaFP();
    if (fp == null) {
      return null; // no information
    }
    return new RISCV64Frame(thread.getLastJavaSP(), fp);
  }

  public RegisterMap newRegisterMap(JavaThread thread, boolean updateMap) {
    return new RISCV64RegisterMap(thread, updateMap);
  }

  public Frame getCurrentFrameGuess(JavaThread thread, Address addr) {
    ThreadProxy t = getThreadProxy(addr);
    RISCV64ThreadContext context = (RISCV64ThreadContext) t.getContext();
    RISCV64CurrentFrameGuess guesser = new RISCV64CurrentFrameGuess(context, thread);
    if (!guesser.run(GUESS_SCAN_RANGE)) {
      return null;
    }
    if (guesser.getPC() == null) {
      return new RISCV64Frame(guesser.getSP(), guesser.getFP());
    } else {
      return new RISCV64Frame(guesser.getSP(), guesser.getFP(), guesser.getPC());
    }
  }

  public void printThreadIDOn(Address addr, PrintStream tty) {
    tty.print(getThreadProxy(addr));
  }

  public void printInfoOn(Address threadAddr, PrintStream tty) {
    tty.print("Thread id: ");
    printThreadIDOn(threadAddr, tty);
  }

  public Address getLastSP(Address addr) {
    ThreadProxy t = getThreadProxy(addr);
    RISCV64ThreadContext context = (RISCV64ThreadContext) t.getContext();
    return context.getRegisterAsAddress(RISCV64ThreadContext.SP);
  }

  public ThreadProxy getThreadProxy(Address addr) {
    // Addr is the address of the JavaThread.
    // Fetch the OSThread (for now and for simplicity, not making a
    // separate "OSThread" class in this package)
    Address osThreadAddr = osThreadField.getValue(addr);
    // Get the address of the _thread_id from the OSThread
    Address threadIdAddr = osThreadAddr.addOffsetTo(osThreadThreadIDField.getOffset());

    JVMDebugger debugger = VM.getVM().getDebugger();
    return debugger.getThreadForIdentifierAddress(threadIdAddr);
  }
}
