package com.sun.org.apache.bcel.internal.util;

import com.sun.org.apache.bcel.internal.Const;
import java.util.HashMap;
import java.util.Map;

import com.sun.org.apache.bcel.internal.classfile.JavaClass;

/**
 * Utility class implementing a (type-safe) set of JavaClass objects. Since JavaClass has no equals() method, the name of the class is used for comparison.
 *
 * @see ClassStack
 * @LastModified: Feb 2023
 */
public class ClassSet {

    private final Map<String, JavaClass> map = new HashMap<>();

    public boolean add(final JavaClass clazz) {
        return map.putIfAbsent(clazz.getClassName(), clazz) != null;
    }

    public boolean empty() {
        return map.isEmpty();
    }

    public String[] getClassNames() {
        return map.keySet().toArray(Const.EMPTY_STRING_ARRAY);
    }

    public void remove(final JavaClass clazz) {
        map.remove(clazz.getClassName());
    }

    public JavaClass[] toArray() {
        return map.values().toArray(JavaClass.EMPTY_ARRAY);
    }
}
