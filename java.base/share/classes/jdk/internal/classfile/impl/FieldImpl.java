package jdk.internal.classfile.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import jdk.internal.classfile.*;
import jdk.internal.classfile.constantpool.Utf8Entry;

public final class FieldImpl
        extends AbstractElement
        implements FieldModel {

    private final ClassReader reader;
    private final int startPos, endPos, attributesPos;
    private List<Attribute<?>> attributes;

    public FieldImpl(ClassReader reader, int startPos, int endPos, int attributesPos) {
        this.reader = reader;
        this.startPos = startPos;
        this.endPos = endPos;
        this.attributesPos = attributesPos;
    }

    @Override
    public AccessFlags flags() {
        return AccessFlags.ofField(reader.readU2(startPos));
    }

    @Override
    public Optional<ClassModel> parent() {
        if (reader instanceof ClassReaderImpl cri)
            return Optional.of(cri.getContainedClass());
        else
            return Optional.empty();
    }

    @Override
    public Utf8Entry fieldName() {
        return reader.readUtf8Entry(startPos + 2);
    }

    @Override
    public Utf8Entry fieldType() {
        return reader.readUtf8Entry(startPos + 4);
    }

    @Override
    public List<Attribute<?>> attributes() {
        if (attributes == null) {
            attributes = BoundAttribute.readAttributes(this, reader, attributesPos, reader.customAttributes());
        }
        return attributes;
    }

    @Override
    public void writeTo(BufWriter buf) {
        if (buf.canWriteDirect(reader)) {
            reader.copyBytesTo(buf, startPos, endPos - startPos);
        }
        else {
            buf.writeU2(flags().flagsMask());
            buf.writeIndex(fieldName());
            buf.writeIndex(fieldType());
            buf.writeList(attributes());
        }
    }

    // FieldModel

    @Override
    public void writeTo(DirectClassBuilder builder) {
        if (builder.canWriteDirect(reader)) {
            builder.withField(this);
        }
        else {
            builder.withField(fieldName(), fieldType(), new Consumer<>() {
                @Override
                public void accept(FieldBuilder fb) {
                    FieldImpl.this.forEachElement(fb);
                }
            });
        }
    }

    @Override
    public void forEachElement(Consumer<FieldElement> consumer) {
        consumer.accept(flags());
        for (Attribute<?> attr : attributes()) {
            if (attr instanceof FieldElement e)
                consumer.accept(e);
        }
    }

    @Override
    public String toString() {
        return String.format("FieldModel[fieldName=%s, fieldType=%s, flags=%d]",
                fieldName().stringValue(), fieldType().stringValue(), flags().flagsMask());
    }
}
