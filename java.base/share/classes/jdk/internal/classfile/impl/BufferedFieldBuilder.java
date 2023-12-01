package jdk.internal.classfile.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import jdk.internal.classfile.*;
import jdk.internal.classfile.constantpool.ConstantPoolBuilder;
import jdk.internal.classfile.constantpool.Utf8Entry;

public final class BufferedFieldBuilder
        implements TerminalFieldBuilder {
    private final SplitConstantPool constantPool;
    private final Utf8Entry name;
    private final Utf8Entry desc;
    private final List<FieldElement> elements = new ArrayList<>();
    private AccessFlags flags;
    private final FieldModel original;

    public BufferedFieldBuilder(SplitConstantPool constantPool,
                                Utf8Entry name,
                                Utf8Entry type,
                                FieldModel original) {
        this.constantPool = constantPool;
        this.name = name;
        this.desc = type;
        this.flags = AccessFlags.ofField();
        this.original = original;
    }

    @Override
    public ConstantPoolBuilder constantPool() {
        return constantPool;
    }

    @Override
    public Optional<FieldModel> original() {
        return Optional.ofNullable(original);
    }

    @Override
    public FieldBuilder with(FieldElement element) {
        elements.add(element);
        if (element instanceof AccessFlags f) this.flags = f;
        return this;
    }

    public BufferedFieldBuilder run(Consumer<? super FieldBuilder> handler) {
        handler.accept(this);
        return this;
    }

    public FieldModel toModel() {
        return new Model();
    }

    public final class Model
            extends AbstractUnboundModel<FieldElement>
            implements FieldModel {
        public Model() {
            super(elements);
        }

        @Override
        public Optional<ClassModel> parent() {
            FieldModel fm = original().orElse(null);
            return fm == null? Optional.empty() : fm.parent();
        }

        @Override
        public AccessFlags flags() {
            return flags;
        }

        @Override
        public Utf8Entry fieldName() {
            return name;
        }

        @Override
        public Utf8Entry fieldType() {
            return desc;
        }

        @Override
        public void writeTo(DirectClassBuilder builder) {
            builder.withField(name, desc, new Consumer<FieldBuilder>() {
                @Override
                public void accept(FieldBuilder fieldBuilder) {
                    elements.forEach(fieldBuilder);
                }
            });
        }

        @Override
        public void writeTo(BufWriter buf) {
            DirectFieldBuilder fb = new DirectFieldBuilder(constantPool, name, desc, null);
            elements.forEach(fb);
            fb.writeTo(buf);
        }

        @Override
        public String toString() {
            return String.format("FieldModel[fieldName=%s, fieldType=%s, flags=%d]", name.stringValue(), desc.stringValue(), flags.flagsMask());
        }
    }
}
