package jdk.dynalink.beans;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import jdk.dynalink.beans.GuardedInvocationComponent.ValidationType;
import jdk.dynalink.linker.support.Lookup;

/**
 * A linker for java.lang.Class objects. Provides a synthetic property "static" that allows access to static fields and
 * methods on the class (respecting property getter/setter conventions). Note that Class objects are not recognized by
 * the Dynalink as constructors for the instances of the class, {@link StaticClass} is used for this purpose.
 */
class ClassLinker extends BeanLinker {

    ClassLinker() {
        super(Class.class);
        // Map "classObject.static" to StaticClass.forClass(classObject). Can use EXACT_CLASS since class Class is final.
        setPropertyGetter("static", FOR_CLASS, ValidationType.EXACT_CLASS);
    }

    private static final MethodHandle FOR_CLASS = Lookup.PUBLIC.findStatic(StaticClass.class,
            "forClass", MethodType.methodType(StaticClass.class, Class.class));

}
