package jdk.internal.classfile.impl;

import java.lang.constant.MethodTypeDesc;
import jdk.internal.classfile.*;
import jdk.internal.classfile.constantpool.Utf8Entry;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public final class MethodImpl
        extends AbstractElement
        implements MethodModel, MethodInfo {

    private final ClassReader reader;
    private final int startPos, endPos, attributesPos;
    private List<Attribute<?>> attributes;
    private int[] parameterSlots;
    private MethodTypeDesc mDesc;

    public MethodImpl(ClassReader reader, int startPos, int endPos, int attrStart) {
        this.reader = reader;
        this.startPos = startPos;
        this.endPos = endPos;
        this.attributesPos = attrStart;
    }

    @Override
    public AccessFlags flags() {
        return AccessFlags.ofMethod(reader.readU2(startPos));
    }

    @Override
    public Optional<ClassModel> parent() {
        if (reader instanceof ClassReaderImpl cri)
            return Optional.of(cri.getContainedClass());
        else
            return Optional.empty();
    }

    @Override
    public Utf8Entry methodName() {
        return reader.readUtf8Entry(startPos + 2);
    }

    @Override
    public Utf8Entry methodType() {
        return reader.readUtf8Entry(startPos + 4);
    }

    @Override
    public MethodTypeDesc methodTypeSymbol() {
        if (mDesc == null) {
            mDesc = MethodTypeDesc.ofDescriptor(methodType().stringValue());
        }
        return mDesc;
    }

    @Override
    public int methodFlags() {
        return reader.readU2(startPos);
    }

    @Override
    public int parameterSlot(int paramNo) {
        if (parameterSlots == null)
            parameterSlots = Util.parseParameterSlots(methodFlags(), methodTypeSymbol());
        return parameterSlots[paramNo];
    }

    @Override
    public List<Attribute<?>> attributes() {
        if (attributes == null) {
            attributes = BoundAttribute.readAttributes(this, reader, attributesPos, reader.customAttributes());
        }
        return attributes;
    }

    @Override
    public void writeTo(BufWriter b) {
        BufWriterImpl buf = (BufWriterImpl) b;
        if (buf.canWriteDirect(reader)) {
            reader.copyBytesTo(buf, startPos, endPos - startPos);
        }
        else {
            buf.writeU2(flags().flagsMask());
            buf.writeIndex(methodName());
            buf.writeIndex(methodType());
            buf.writeList(attributes());
        }
    }

    // MethodModel

    @Override
    public Optional<CodeModel> code() {
        return findAttribute(Attributes.CODE).map(a -> (CodeModel) a);
    }

    @Override
    public void forEachElement(Consumer<MethodElement> consumer) {
        consumer.accept(flags());
        for (Attribute<?> attr : attributes()) {
            if (attr instanceof MethodElement e)
                consumer.accept(e);
        }
    }

    @Override
    public void writeTo(DirectClassBuilder builder) {
        if (builder.canWriteDirect(reader)) {
            builder.withMethod(this);
        }
        else {
            builder.withMethod(methodName(), methodType(), methodFlags(),
                               new Consumer<>() {
                @Override
                public void accept(MethodBuilder mb) {
                    MethodImpl.this.forEachElement(mb);
                }
            });
        }
    }

    @Override
    public String toString() {
        return String.format("MethodModel[methodName=%s, methodType=%s, flags=%d]",
                methodName().stringValue(), methodType().stringValue(), flags().flagsMask());
    }
}
