package sun.jvm.hotspot.utilities;

import java.util.*;
import sun.jvm.hotspot.classfile.*;
import sun.jvm.hotspot.oops.*;
import sun.jvm.hotspot.runtime.*;

public class SystemDictionaryHelper {
   static {
      VM.registerVMInitializedObserver(new Observer() {
         public void update(Observable o, Object data) {
            initialize();
         }
      });
   }

   private static synchronized void initialize() {
      klasses = null;
   }

   // Instance klass array sorted by name.
   private static InstanceKlass[] klasses;

   // side-effect!. caches the instance klass array.
   public static synchronized InstanceKlass[] getAllInstanceKlasses() {
      if (klasses != null) {
         return klasses;
      }

      final Vector<InstanceKlass> tmp = new Vector<>();
      ClassLoaderDataGraph cldg = VM.getVM().getClassLoaderDataGraph();
      cldg.classesDo(new ClassLoaderDataGraph.ClassVisitor() {
                        public void visit(Klass k) {
                           if (k instanceof InstanceKlass) {
                              InstanceKlass ik = (InstanceKlass) k;
                              tmp.add(ik);
                           }
                        }
                     });

      klasses = tmp.toArray(new InstanceKlass[0]);
      Arrays.sort(klasses, new Comparator<>() {
                          public int compare(InstanceKlass k1, InstanceKlass k2) {
                             Symbol s1 = k1.getName();
                             Symbol s2 = k2.getName();
                             return s1.asString().compareTo(s2.asString());
                          }
                      });
      return klasses;
   }

   // returns array of instance klasses whose name contains given namePart
   public static InstanceKlass[] findInstanceKlasses(String namePart) {
      namePart = namePart.replace('.', '/');
      InstanceKlass[] tmpKlasses = getAllInstanceKlasses();

      Vector<InstanceKlass> tmp = new Vector<>();
      for (int i = 0; i < tmpKlasses.length; i++) {
         String name = tmpKlasses[i].getName().asString();
         if (name.contains(namePart)) {
            tmp.add(tmpKlasses[i]);
         }
      }

      InstanceKlass[] searchResult = tmp.toArray(new InstanceKlass[0]);
      return searchResult;
   }

   // find first class whose name matches exactly the given argument.
   public static InstanceKlass findInstanceKlass(String className) {
      // convert to internal name
      className = className.replace('.', '/');
      ClassLoaderDataGraph cldg = VM.getVM().getClassLoaderDataGraph();

      // check whether we have a class of given name
      Klass klass = cldg.find(className);
      if (klass instanceof InstanceKlass ik) {
         return ik;
      } else {
        // no match ..
        return null;
      }
   }
}
