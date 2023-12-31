package com.sun.jdi;

import java.util.List;

/**
 * A class loader object from the target VM.
 * A ClassLoaderReference is an {@link ObjectReference} with additional
 * access to classloader-specific information from the target VM. Instances
 * ClassLoaderReference are obtained through calls to
 * {@link ReferenceType#classLoader}
 *
 * @see ObjectReference
 *
 * @author Gordon Hirsch
 * @since  1.3
 */
public interface ClassLoaderReference extends ObjectReference {

    /**
     * Returns a list of all classes defined by this class loader.
     * No ordering of this list guaranteed.
     * The returned list will include all reference types, including
     * {@linkplain Class#isHidden hidden classes or interfaces}, loaded
     * at least to the point of preparation, and types (like array)
     * for which preparation is not defined.
     *
     * @return a {@code List} of {@link ReferenceType} objects mirroring types
     * defined by this class loader. The list has length 0 if no types
     * have been defined by this classloader.
     */
    List<ReferenceType> definedClasses();

    /**
     * Returns a list of all classes which this class loader
     * can find by name via {@link ClassLoader#loadClass(String, boolean)
     * ClassLoader::loadClass}, {@link Class#forName(String) Class::forName}
     * and bytecode linkage in the target VM.  That is, all classes for
     * which this class loader has been recorded as an initiating loader.
     * <p>
     * Each class in the returned list was created by this class loader
     * either by defining it directly or by delegation to another class loader
     * (see JVMS {@jvms 5.3}).
     * <p>
     * The returned list does not include {@linkplain Class#isHidden()
     * hidden classes or interfaces} or array classes whose
     * {@linkplain ArrayType#componentType() element type} is a
     * {@linkplain Class#isHidden() hidden class or interface}.
     * as they cannot be discovered by any class loader
     * <p>
     * The visible class list has useful properties with respect to
     * the type namespace. A particular type name will occur at most
     * once in the list. Each field or variable declared with that
     * type name in a class defined by
     * this class loader must be resolved to that single type.
     * <p>
     * No ordering of the returned list is guaranteed.
     * <p>
     * Note that unlike {@link #definedClasses()}
     * and {@link VirtualMachine#allClasses()},
     * some of the returned reference types may not be prepared.
     * Attempts to perform some operations on unprepared reference types
     * (e.g. {@link ReferenceType#fields() fields()}) will throw
     * a {@link ClassNotPreparedException}.
     * Use {@link ReferenceType#isPrepared()} to determine if
     * a reference type is prepared.
     *
     * @return a {@code List} of {@link ReferenceType} objects mirroring
     * classes which this class loader can find by name. The list
     * has length 0 if no classes are visible to this classloader.
     *
     * @see <a href="{@docRoot}/../specs/jvmti.html#GetClassLoaderClasses">
     *     JVM TI GetClassLoaderClasses</a>
     */
    List<ReferenceType> visibleClasses();
}
