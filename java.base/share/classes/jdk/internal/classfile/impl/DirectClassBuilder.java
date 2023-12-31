package jdk.internal.classfile.impl;

import java.lang.constant.ConstantDescs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import jdk.internal.classfile.BufWriter;
import jdk.internal.classfile.ClassBuilder;
import jdk.internal.classfile.ClassElement;
import jdk.internal.classfile.ClassModel;
import jdk.internal.classfile.Classfile;
import jdk.internal.classfile.constantpool.ClassEntry;
import jdk.internal.classfile.FieldBuilder;
import jdk.internal.classfile.FieldModel;
import jdk.internal.classfile.FieldTransform;
import jdk.internal.classfile.MethodBuilder;
import jdk.internal.classfile.MethodModel;
import jdk.internal.classfile.MethodTransform;
import jdk.internal.classfile.WritableElement;
import jdk.internal.classfile.constantpool.Utf8Entry;

public final class DirectClassBuilder
        extends AbstractDirectBuilder<ClassModel>
        implements ClassBuilder {

    final ClassEntry thisClassEntry;
    private final List<WritableElement<FieldModel>> fields = new ArrayList<>();
    private final List<WritableElement<MethodModel>> methods = new ArrayList<>();
    private ClassEntry superclassEntry;
    private List<ClassEntry> interfaceEntries;
    private int majorVersion;
    private int minorVersion;
    private int flags;
    private int sizeHint;

    public DirectClassBuilder(SplitConstantPool constantPool,
                              ClassEntry thisClass) {
        super(constantPool);
        this.thisClassEntry = AbstractPoolEntry.maybeClone(constantPool, thisClass);
        this.flags = Classfile.DEFAULT_CLASS_FLAGS;
        this.superclassEntry = null;
        this.interfaceEntries = Collections.emptyList();
        this.majorVersion = Classfile.LATEST_MAJOR_VERSION;
        this.minorVersion = Classfile.LATEST_MINOR_VERSION;
    }

    @Override
    public ClassBuilder with(ClassElement element) {
        ((AbstractElement) element).writeTo(this);
        return this;
    }

    @Override
    public ClassBuilder withField(Utf8Entry name,
                                  Utf8Entry descriptor,
                                  Consumer<? super FieldBuilder> handler) {
        return withField(new DirectFieldBuilder(constantPool, name, descriptor, null)
                                 .run(handler));
    }

    @Override
    public ClassBuilder transformField(FieldModel field, FieldTransform transform) {
        DirectFieldBuilder builder = new DirectFieldBuilder(constantPool, field.fieldName(),
                                                            field.fieldType(), field);
        builder.transform(field, transform);
        return withField(builder);
    }

    @Override
    public ClassBuilder withMethod(Utf8Entry name,
                                   Utf8Entry descriptor,
                                   int flags,
                                   Consumer<? super MethodBuilder> handler) {
        return withMethod(new DirectMethodBuilder(constantPool, name, descriptor, flags, null)
                                  .run(handler));
    }

    @Override
    public ClassBuilder transformMethod(MethodModel method, MethodTransform transform) {
        DirectMethodBuilder builder = new DirectMethodBuilder(constantPool, method.methodName(),
                                                              method.methodType(),
                                                              method.flags().flagsMask(),
                                                              method);
        builder.transform(method, transform);
        return withMethod(builder);
    }

    // internal / for use by elements

    public ClassBuilder withField(WritableElement<FieldModel> field) {
        fields.add(field);
        return this;
    }

    public ClassBuilder withMethod(WritableElement<MethodModel> method) {
        methods.add(method);
        return this;
    }

    void setSuperclass(ClassEntry superclassEntry) {
        this.superclassEntry = superclassEntry;
    }

    void setInterfaces(List<ClassEntry> interfaces) {
        this.interfaceEntries = interfaces;
    }

    void setVersion(int major, int minor) {
        this.majorVersion = major;
        this.minorVersion = minor;
    }

    void setFlags(int flags) {
        this.flags = flags;
    }

    void setSizeHint(int sizeHint) {
        this.sizeHint = sizeHint;
    }


    public byte[] build() {

        // The logic of this is very carefully ordered.  We want to avoid
        // repeated buffer copyings, so we accumulate lists of writers which
        // all get written later into the same buffer.  But, writing can often
        // trigger CP / BSM insertions, so we cannot run the CP writer or
        // BSM writers until everything else is written.

        // Do this early because it might trigger CP activity
        ClassEntry superclass = superclassEntry;
        if (superclass != null)
            superclass = AbstractPoolEntry.maybeClone(constantPool, superclass);
        else if ((flags & Classfile.ACC_MODULE) == 0 && !"java/lang/Object".equals(thisClassEntry.asInternalName()))
            superclass = constantPool.classEntry(ConstantDescs.CD_Object);
        List<ClassEntry> ies = new ArrayList<>(interfaceEntries.size());
        for (ClassEntry ce : interfaceEntries)
            ies.add(AbstractPoolEntry.maybeClone(constantPool, ce));

        // We maintain two writers, and then we join them at the end
        int size = sizeHint == 0 ? 256 : sizeHint;
        BufWriter head = new BufWriterImpl(constantPool, size);
        BufWriterImpl tail = new BufWriterImpl(constantPool, size, thisClassEntry, majorVersion);

        // The tail consists of fields and methods, and attributes
        // This should trigger all the CP/BSM mutation
        tail.writeList(fields);
        tail.writeList(methods);
        int attributesOffset = tail.size();
        attributes.writeTo(tail);

        // Now we have to append the BSM, if there is one
        boolean written = constantPool.writeBootstrapMethods(tail);
        if (written) {
            // Update attributes count
            tail.patchInt(attributesOffset, 2, attributes.size() + 1);
        }

        // Now we can make the head
        head.writeInt(Classfile.MAGIC_NUMBER);
        head.writeU2(minorVersion);
        head.writeU2(majorVersion);
        constantPool.writeTo(head);
        head.writeU2(flags);
        head.writeIndex(thisClassEntry);
        head.writeIndexOrZero(superclass);
        head.writeListIndices(ies);

        // Join head and tail into an exact-size buffer
        byte[] result = new byte[head.size() + tail.size()];
        head.copyTo(result, 0);
        tail.copyTo(result, head.size());
        return result;
    }
}
