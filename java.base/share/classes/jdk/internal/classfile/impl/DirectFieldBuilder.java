package jdk.internal.classfile.impl;

import java.util.function.Consumer;

import jdk.internal.classfile.BufWriter;
import jdk.internal.classfile.FieldBuilder;
import jdk.internal.classfile.FieldElement;
import jdk.internal.classfile.FieldModel;
import jdk.internal.classfile.WritableElement;
import jdk.internal.classfile.constantpool.Utf8Entry;

public final class DirectFieldBuilder
        extends AbstractDirectBuilder<FieldModel>
        implements TerminalFieldBuilder, WritableElement<FieldModel> {
    private final Utf8Entry name;
    private final Utf8Entry desc;
    private int flags;

    public DirectFieldBuilder(SplitConstantPool constantPool,
                              Utf8Entry name,
                              Utf8Entry type,
                              FieldModel original) {
        super(constantPool);
        setOriginal(original);
        this.name = name;
        this.desc = type;
        this.flags = 0;
    }

    @Override
    public FieldBuilder with(FieldElement element) {
        ((AbstractElement) element).writeTo(this);
        return this;
    }

    public DirectFieldBuilder run(Consumer<? super FieldBuilder> handler) {
        handler.accept(this);
        return this;
    }

    void setFlags(int flags) {
        this.flags = flags;
    }

    @Override
    public void writeTo(BufWriter buf) {
        buf.writeU2(flags);
        buf.writeIndex(name);
        buf.writeIndex(desc);
        attributes.writeTo(buf);
    }
}
