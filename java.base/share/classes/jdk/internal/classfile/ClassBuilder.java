package jdk.internal.classfile;


import java.lang.constant.ClassDesc;
import java.lang.constant.MethodTypeDesc;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import jdk.internal.classfile.constantpool.ClassEntry;
import jdk.internal.classfile.constantpool.Utf8Entry;
import jdk.internal.classfile.impl.ChainedClassBuilder;
import jdk.internal.classfile.impl.DirectClassBuilder;
import jdk.internal.classfile.impl.Util;
import java.lang.reflect.AccessFlag;
import jdk.internal.classfile.attribute.CodeAttribute;

/**
 * A builder for classfiles.  Builders are not created directly; they are passed
 * to handlers by methods such as {@link Classfile#build(ClassDesc, Consumer)}
 * or to class transforms.  The elements of a classfile can be specified
 * abstractly (by passing a {@link ClassElement} to {@link #with(ClassfileElement)})
 * or concretely by calling the various {@code withXxx} methods.
 *
 * @see ClassTransform
 */
public sealed interface ClassBuilder
        extends ClassfileBuilder<ClassElement, ClassBuilder>
        permits ChainedClassBuilder, DirectClassBuilder {

    /**
     * {@return the {@link ClassModel} representing the class being transformed,
     * if this class builder represents the transformation of some {@link ClassModel}}
     */
    Optional<ClassModel> original();

    /**
     * Sets the classfile version.
     * @param major the major version number
     * @param minor the minor version number
     * @return this builder
     */
    default ClassBuilder withVersion(int major, int minor) {
        return with(ClassfileVersion.of(major, minor));
    }

    /**
     * Sets the classfile access flags.
     * @param flags the access flags, as a bit mask
     * @return this builder
     */
    default ClassBuilder withFlags(int flags) {
        return with(AccessFlags.ofClass(flags));
    }

    /**
     * Sets the classfile access flags.
     * @param flags the access flags
     * @return this builder
     */
    default ClassBuilder withFlags(AccessFlag... flags) {
        return with(AccessFlags.ofClass(flags));
    }

    /**
     * Sets the superclass of this class.
     * @param superclassEntry the superclass
     * @return this builder
     */
    default ClassBuilder withSuperclass(ClassEntry superclassEntry) {
        return with(Superclass.of(superclassEntry));
    }

    /**
     * Sets the superclass of this class.
     * @param desc the superclass
     * @return this builder
     */
    default ClassBuilder withSuperclass(ClassDesc desc) {
        return withSuperclass(constantPool().classEntry(desc));
    }

    /**
     * Sets the interfaces of this class.
     * @param interfaces the interfaces
     * @return this builder
     */
    default ClassBuilder withInterfaces(List<ClassEntry> interfaces) {
        return with(Interfaces.of(interfaces));
    }

    /**
     * Sets the interfaces of this class.
     * @param interfaces the interfaces
     * @return this builder
     */
    default ClassBuilder withInterfaces(ClassEntry... interfaces) {
        return withInterfaces(List.of(interfaces));
    }

    /**
     * Sets the interfaces of this class.
     * @param interfaces the interfaces
     * @return this builder
     */
    default ClassBuilder withInterfaceSymbols(List<ClassDesc> interfaces) {
        return withInterfaces(Util.entryList(interfaces));
    }

    /**
     * Sets the interfaces of this class.
     * @param interfaces the interfaces
     * @return this builder
     */
    default ClassBuilder withInterfaceSymbols(ClassDesc... interfaces) {
        // List view, since ref to interfaces is temporary
        return withInterfaceSymbols(Arrays.asList(interfaces));
    }

    /**
     * Adds a field.
     * @param name the name of the field
     * @param descriptor the field descriptor
     * @param handler handler which receives a {@link FieldBuilder} which can
     *                    further define the contents of the field
     * @return this builder
     */
    ClassBuilder withField(Utf8Entry name,
                           Utf8Entry descriptor,
                           Consumer<? super FieldBuilder> handler);

    /**
     * Adds a field.
     * @param name the name of the field
     * @param descriptor the field descriptor
     * @param flags the access flags for this field
     * @return this builder
     */
    default ClassBuilder withField(Utf8Entry name,
                                   Utf8Entry descriptor,
                                   int flags) {
        return withField(name, descriptor, fb -> fb.withFlags(flags));
    }

    /**
     * Adds a field.
     * @param name the name of the field
     * @param descriptor the field descriptor
     * @param handler handler which receives a {@link FieldBuilder} which can
     *                    further define the contents of the field
     * @return this builder
     */
    default ClassBuilder withField(String name,
                                   ClassDesc descriptor,
                                   Consumer<? super FieldBuilder> handler) {
        return withField(constantPool().utf8Entry(name),
                         constantPool().utf8Entry(descriptor),
                         handler);
    }

    /**
     * Adds a field.
     * @param name the name of the field
     * @param descriptor the field descriptor
     * @param flags the access flags for this field
     * @return this builder
     */
    default ClassBuilder withField(String name,
                                   ClassDesc descriptor,
                                   int flags) {
        return withField(name, descriptor, fb -> fb.withFlags(flags));
    }

    /**
     * Adds a field by transforming a field from another class.
     *
     * @implNote
     * <p>This method behaves as if:
     * {@snippet lang=java :
     *     withField(field.fieldName(), field.fieldType(),
     *                b -> b.transformField(field, transform));
     * }
     *
     * @param field the field to be transformed
     * @param transform the transform to apply to the field
     * @return this builder
     */
    ClassBuilder transformField(FieldModel field, FieldTransform transform);

    /**
     * Adds a method.
     * @param name the name of the method
     * @param descriptor the method descriptor
     * @param methodFlags the access flags
     * @param handler handler which receives a {@link MethodBuilder} which can
     *                    further define the contents of the method
     * @return this builder
     */
    ClassBuilder withMethod(Utf8Entry name,
                            Utf8Entry descriptor,
                            int methodFlags,
                            Consumer<? super MethodBuilder> handler);

    /**
     * Adds a method, with only a {@code Code} attribute.
     *
     * @param name the name of the method
     * @param descriptor the method descriptor
     * @param methodFlags the access flags
     * @param handler handler which receives a {@link CodeBuilder} which can
     *                    define the contents of the method body
     * @return this builder
     */
    default ClassBuilder withMethodBody(Utf8Entry name,
                                        Utf8Entry descriptor,
                                        int methodFlags,
                                        Consumer<? super CodeBuilder> handler) {
        return withMethod(name, descriptor, methodFlags, mb -> mb.withCode(handler));
    }

    /**
     * Adds a method.
     * @param name the name of the method
     * @param descriptor the method descriptor
     * @param methodFlags the access flags
     * @param handler handler which receives a {@link MethodBuilder} which can
     *                    further define the contents of the method
     * @return this builder
     */
    default ClassBuilder withMethod(String name,
                                    MethodTypeDesc descriptor,
                                    int methodFlags,
                                    Consumer<? super MethodBuilder> handler) {
        return withMethod(constantPool().utf8Entry(name),
                          constantPool().utf8Entry(descriptor),
                          methodFlags,
                          handler);
    }

    /**
     * Adds a method, with only a {@link CodeAttribute}.
     * @param name the name of the method
     * @param descriptor the method descriptor
     * @param methodFlags the access flags
     * @param handler handler which receives a {@link CodeBuilder} which can
     *                    define the contents of the method body
     * @return this builder
     */
    default ClassBuilder withMethodBody(String name,
                                        MethodTypeDesc descriptor,
                                        int methodFlags,
                                        Consumer<? super CodeBuilder> handler) {
        return withMethodBody(constantPool().utf8Entry(name),
                              constantPool().utf8Entry(descriptor),
                              methodFlags,
                              handler);
    }

    /**
     * Adds a method by transforming a method from another class.
     *
     * @implNote
     * <p>This method behaves as if:
     * {@snippet lang=java :
     *     withMethod(method.methodName(), method.methodType(),
     *                b -> b.transformMethod(method, transform));
     * }
     * @param method the method to be transformed
     * @param transform the transform to apply to the method
     * @return this builder
     */
    ClassBuilder transformMethod(MethodModel method, MethodTransform transform);
}
