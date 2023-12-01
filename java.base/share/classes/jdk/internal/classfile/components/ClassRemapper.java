package jdk.internal.classfile.components;

import java.lang.constant.ClassDesc;
import java.util.Map;
import java.util.function.Function;
import jdk.internal.classfile.ClassModel;
import jdk.internal.classfile.ClassTransform;
import jdk.internal.classfile.Classfile;
import jdk.internal.classfile.CodeTransform;
import jdk.internal.classfile.FieldTransform;
import jdk.internal.classfile.MethodTransform;
import jdk.internal.classfile.impl.ClassRemapperImpl;

/**
 * {@code ClassRemapper} is a {@link ClassTransform}, {@link FieldTransform},
 * {@link MethodTransform} and {@link CodeTransform}
 * deeply re-mapping all class references in any form, according to given map or
 * map function.
 * <p>
 * The re-mapping is applied to superclass, interfaces, all kinds of descriptors
 * and signatures, all attributes referencing classes in any form (including all
 * types of annotations), and to all instructions referencing to classes.
 * <p>
 * Primitive types and arrays are never subjects of mapping and are not allowed
 * targets of mapping.
 * <p>
 * Arrays of reference types are always decomposed, mapped as the base reference
 * types and composed back to arrays.
 */
public sealed interface ClassRemapper extends ClassTransform permits ClassRemapperImpl {

    /**
     * Creates new instance of {@code ClassRemapper} instructed with a class map.
     * Map may contain only re-mapping entries, identity mapping is applied by default.
     * @param classMap class map
     * @return new instance of {@code ClassRemapper}
     */
    static ClassRemapper of(Map<ClassDesc, ClassDesc> classMap) {
        return of(desc -> classMap.getOrDefault(desc, desc));
    }

    /**
     * Creates new instance of {@code ClassRemapper} instructed with a map function.
     * Map function must return valid {@link java.lang.constant.ClassDesc} of an interface
     * or a class, even for identity mappings.
     * @param mapFunction class map function
     * @return new instance of {@code ClassRemapper}
     */
    static ClassRemapper of(Function<ClassDesc, ClassDesc> mapFunction) {
        return new ClassRemapperImpl(mapFunction);
    }

    /**
     * Access method to internal class mapping function.
     * @param desc source class
     * @return target class
     */
    ClassDesc map(ClassDesc desc);

    /**
     * {@return this {@code ClassRemapper} as {@link FieldTransform} instance}
     */
    FieldTransform asFieldTransform();

    /**
     * {@return this {@code ClassRemapper} as {@link MethodTransform} instance}
     */
    MethodTransform asMethodTransform();

    /**
     * {@return this {@code ClassRemapper} as {@link CodeTransform} instance}
     */
    CodeTransform asCodeTransform();

    /**
     * Remaps the whole ClassModel into a new class file, including the class name.
     * @param clm class model to re-map
     * @return re-mapped class file bytes
     */
    default byte[] remapClass(ClassModel clm) {
        return Classfile.build(map(clm.thisClass().asSymbol()),
                clb -> clm.forEachElement(resolve(clb).consumer()));
    }
}
