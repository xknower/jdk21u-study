package com.sun.tools.jdeps;

import com.sun.tools.classfile.ClassFile;
import com.sun.tools.classfile.ConstantPoolException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class VersionHelper {
    private static final String META_INF_VERSIONS = "META-INF/versions/";
    private static final Map<String,String> nameToVersion = new ConcurrentHashMap<>();

    public static String get(String classname) {
        if (nameToVersion.containsKey(classname)) {
            return nameToVersion.get(classname) + "/" + classname;
        }
        return classname;
    }

    public static void add(JarFile jarfile, JarEntry e, ClassFile cf)
            throws ConstantPoolException
    {
        String realName = e.getRealName();
        if (realName.startsWith(META_INF_VERSIONS)) {
            int len = META_INF_VERSIONS.length();
            int n = realName.indexOf('/', len);
            if (n > 0) {
                String version = realName.substring(len, n);
                assert (Integer.parseInt(version) > 8);
                String name = cf.getName().replace('/', '.');
                String v = nameToVersion.computeIfAbsent(name, _n -> version);
                if (!version.equals(v)) {
                    throw new MultiReleaseException("err.multirelease.version.associated",
                                name, nameToVersion.get(name), version);
                }
            } else {
                throw new MultiReleaseException("err.multirelease.jar.malformed",
                        jarfile.getName(), realName);
            }
        }
    }
}
