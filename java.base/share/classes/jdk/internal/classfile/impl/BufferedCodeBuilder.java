package jdk.internal.classfile.impl;

import jdk.internal.classfile.BufWriter;
import jdk.internal.classfile.CodeBuilder;
import jdk.internal.classfile.CodeElement;
import jdk.internal.classfile.CodeModel;
import jdk.internal.classfile.TypeKind;
import jdk.internal.classfile.constantpool.ConstantPoolBuilder;
import jdk.internal.classfile.Label;
import jdk.internal.classfile.MethodModel;
import jdk.internal.classfile.instruction.ExceptionCatch;
import jdk.internal.classfile.instruction.IncrementInstruction;
import jdk.internal.classfile.instruction.LoadInstruction;
import jdk.internal.classfile.instruction.StoreInstruction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public final class BufferedCodeBuilder
        implements TerminalCodeBuilder, LabelContext {
    private final SplitConstantPool constantPool;
    private final List<CodeElement> elements = new ArrayList<>();
    private final LabelImpl startLabel, endLabel;
    private final CodeModel original;
    private final MethodInfo methodInfo;
    private boolean finished;
    private int maxLocals;

    public BufferedCodeBuilder(MethodInfo methodInfo,
                               SplitConstantPool constantPool,
                               CodeModel original) {
        this.constantPool = constantPool;
        this.startLabel = new LabelImpl(this, -1);
        this.endLabel = new LabelImpl(this, -1);
        this.original = original;
        this.methodInfo = methodInfo;
        this.maxLocals = Util.maxLocals(methodInfo.methodFlags(), methodInfo.methodTypeSymbol());
        if (original != null)
            this.maxLocals = Math.max(this.maxLocals, original.maxLocals());

        elements.add(startLabel);
    }

    @Override
    public Optional<CodeModel> original() {
        return Optional.ofNullable(original);
    }

    @Override
    public Label newLabel() {
        return new LabelImpl(this, -1);
    }

    @Override
    public Label startLabel() {
        return startLabel;
    }

    @Override
    public Label endLabel() {
        return endLabel;
    }

    @Override
    public int receiverSlot() {
        return methodInfo.receiverSlot();
    }

    @Override
    public int parameterSlot(int paramNo) {
        return methodInfo.parameterSlot(paramNo);
    }

    public int curTopLocal() {
        return maxLocals;
    }

    @Override
    public int allocateLocal(TypeKind typeKind) {
        int retVal = maxLocals;
        maxLocals += typeKind.slotSize();
        return retVal;
    }

    @Override
    public Label getLabel(int bci) {
        throw new UnsupportedOperationException("Lookup by BCI not supported by BufferedCodeBuilder");
    }

    @Override
    public int labelToBci(Label label) {
        throw new UnsupportedOperationException("Label mapping not supported by BufferedCodeBuilder");
    }

    @Override
    public void setLabelTarget(Label label, int bci) {
        throw new UnsupportedOperationException("Label mapping not supported by BufferedCodeBuilder");
    }

    @Override
    public ConstantPoolBuilder constantPool() {
        return constantPool;
    }

    @Override
    public CodeBuilder with(CodeElement element) {
        if (finished)
            throw new IllegalStateException("Can't add elements after traversal");
        elements.add(element);
        return this;
    }

    @Override
    public String toString() {
        return String.format("CodeModel[id=%d]", System.identityHashCode(this));
    }

    public BufferedCodeBuilder run(Consumer<? super CodeBuilder> handler) {
        handler.accept(this);
        return this;
    }

    public CodeModel toModel() {
        if (!finished) {
            elements.add(endLabel);
            finished = true;
        }
        return new Model();
    }

    public final class Model
            extends AbstractUnboundModel<CodeElement>
            implements CodeModel {

        private Model() {
            super(elements);
        }

        @Override
        public List<ExceptionCatch> exceptionHandlers() {
            return elements.stream()
                           .filter(x -> x instanceof ExceptionCatch)
                           .map(x -> (ExceptionCatch) x)
                           .toList();
        }

        @Override
        public int maxLocals() {
            for (CodeElement element : elements) {
                if (element instanceof LoadInstruction i)
                    maxLocals = Math.max(maxLocals, i.slot() + i.typeKind().slotSize());
                else if (element instanceof StoreInstruction i)
                    maxLocals = Math.max(maxLocals, i.slot() + i.typeKind().slotSize());
                else if (element instanceof IncrementInstruction i)
                    maxLocals = Math.max(maxLocals, i.slot() + 1);
            }
            return maxLocals;
        }

        @Override
        public int maxStack() {
            throw new UnsupportedOperationException("nyi");
        }

        @Override
        public Optional<MethodModel> parent() {
            return Optional.empty();
        }

        @Override
        public void writeTo(DirectMethodBuilder builder) {
            builder.withCode(new Consumer<>() {
                @Override
                public void accept(CodeBuilder cb) {
                    forEachElement(cb);
                }
            });
        }

        public void writeTo(BufWriter buf) {
            DirectCodeBuilder.build(methodInfo, cb -> elements.forEach(cb), constantPool, null).writeTo(buf);
        }

        @Override
        public String toString() {
            return String.format("CodeModel[id=%s]", Integer.toHexString(System.identityHashCode(this)));
        }
    }
}
