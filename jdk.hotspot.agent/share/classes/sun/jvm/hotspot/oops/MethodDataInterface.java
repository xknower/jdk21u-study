package sun.jvm.hotspot.oops;

import java.io.*;
import java.util.*;
import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.runtime.*;
import sun.jvm.hotspot.types.*;
import sun.jvm.hotspot.utilities.*;

public interface MethodDataInterface<K, M> {
  K getKlassAtAddress(Address addr);
  M getMethodAtAddress(Address addr);
  void printKlassValueOn(K klass, PrintStream st);
  void printMethodValueOn(M klass, PrintStream st);
}
