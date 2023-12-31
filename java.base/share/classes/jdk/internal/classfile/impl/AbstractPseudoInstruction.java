package jdk.internal.classfile.impl;

import java.util.Optional;

import jdk.internal.classfile.BufWriter;
import jdk.internal.classfile.constantpool.ClassEntry;
import jdk.internal.classfile.constantpool.Utf8Entry;
import jdk.internal.classfile.instruction.CharacterRange;
import jdk.internal.classfile.instruction.ExceptionCatch;
import jdk.internal.classfile.instruction.LocalVariable;
import jdk.internal.classfile.instruction.LocalVariableType;
import jdk.internal.classfile.Label;
import jdk.internal.classfile.PseudoInstruction;

public abstract sealed class AbstractPseudoInstruction
        extends AbstractElement
        implements PseudoInstruction {

    @Override
    public abstract void writeTo(DirectCodeBuilder writer);

    public static final class ExceptionCatchImpl
            extends AbstractPseudoInstruction
            implements ExceptionCatch {

        public final ClassEntry catchTypeEntry;
        public final Label handler;
        public final Label tryStart;
        public final Label tryEnd;

        public ExceptionCatchImpl(Label handler, Label tryStart, Label tryEnd,
                                  ClassEntry catchTypeEntry) {
            this.catchTypeEntry = catchTypeEntry;
            this.handler = handler;
            this.tryStart = tryStart;
            this.tryEnd = tryEnd;
        }

        public ExceptionCatchImpl(Label handler, Label tryStart, Label tryEnd,
                                  Optional<ClassEntry> catchTypeEntry) {
            this.catchTypeEntry = catchTypeEntry.orElse(null);
            this.handler = handler;
            this.tryStart = tryStart;
            this.tryEnd = tryEnd;
        }

        @Override
        public Label tryStart() {
            return tryStart;
        }

        @Override
        public Label handler() {
            return handler;
        }

        @Override
        public Label tryEnd() {
            return tryEnd;
        }

        @Override
        public Optional<ClassEntry> catchType() {
            return Optional.ofNullable(catchTypeEntry);
        }

        ClassEntry catchTypeEntry() {
            return catchTypeEntry;
        }

        @Override
        public void writeTo(DirectCodeBuilder writer) {
            writer.addHandler(this);
        }

        @Override
        public String toString() {
            return String.format("ExceptionCatch[catchType=%s]", catchTypeEntry == null ? "<any>" : catchTypeEntry.name().stringValue());
        }
    }

    public static final class UnboundCharacterRange
            extends AbstractPseudoInstruction
            implements CharacterRange {

        public final Label startScope;
        public final Label endScope;
        public final int characterRangeStart;
        public final int characterRangeEnd;
        public final int flags;

        public UnboundCharacterRange(Label startScope, Label endScope, int characterRangeStart,
                                     int characterRangeEnd, int flags) {
            this.startScope = startScope;
            this.endScope = endScope;
            this.characterRangeStart = characterRangeStart;
            this.characterRangeEnd = characterRangeEnd;
            this.flags = flags;
        }

        @Override
        public Label startScope() {
            return startScope;
        }

        @Override
        public Label endScope() {
            return endScope;
        }

        @Override
        public int characterRangeStart() {
            return characterRangeStart;
        }

        @Override
        public int characterRangeEnd() {
            return characterRangeEnd;
        }

        @Override
        public int flags() {
            return flags;
        }

        @Override
        public void writeTo(DirectCodeBuilder writer) {
            writer.addCharacterRange(this);
        }

    }

    private static abstract sealed class AbstractLocalPseudo extends AbstractPseudoInstruction {
        protected final int slot;
        protected final Utf8Entry name;
        protected final Utf8Entry descriptor;
        protected final Label startScope;
        protected final Label endScope;

        public AbstractLocalPseudo(int slot, Utf8Entry name, Utf8Entry descriptor, Label startScope, Label endScope) {
            this.slot = slot;
            this.name = name;
            this.descriptor = descriptor;
            this.startScope = startScope;
            this.endScope = endScope;
        }

        public int slot() {
            return slot;
        }

        public Utf8Entry name() {
            return name;
        }

        public String nameString() {
            return name.stringValue();
        }

        public Label startScope() {
            return startScope;
        }

        public Label endScope() {
            return endScope;
        }

        public boolean writeTo(BufWriter b) {
            var lc = ((BufWriterImpl)b).labelContext();
            int startBci = lc.labelToBci(startScope());
            int endBci = lc.labelToBci(endScope());
            if (startBci == -1 || endBci == -1) {
                return false;
            }
            int length = endBci - startBci;
            b.writeU2(startBci);
            b.writeU2(length);
            b.writeIndex(name);
            b.writeIndex(descriptor);
            b.writeU2(slot());
            return true;
        }
    }

    public static final class UnboundLocalVariable extends AbstractLocalPseudo
            implements LocalVariable {

        public UnboundLocalVariable(int slot, Utf8Entry name, Utf8Entry descriptor, Label startScope, Label endScope) {
            super(slot, name, descriptor, startScope, endScope);
        }

        @Override
        public Utf8Entry type() {
            return descriptor;
        }

        @Override
        public void writeTo(DirectCodeBuilder writer) {
            writer.addLocalVariable(this);
        }

        @Override
        public String toString() {
            return "LocalVariable[Slot=" + slot()
                   + ", name=" + nameString()
                   + ", descriptor='" + type().stringValue()
                   + "']";
        }
    }

    public static final class UnboundLocalVariableType extends AbstractLocalPseudo
            implements LocalVariableType {

        public UnboundLocalVariableType(int slot, Utf8Entry name, Utf8Entry signature, Label startScope, Label endScope) {
            super(slot, name, signature, startScope, endScope);
        }

        @Override
        public Utf8Entry signature() {
            return descriptor;
        }

        @Override
        public void writeTo(DirectCodeBuilder writer) {
            writer.addLocalVariableType(this);
        }

        @Override
        public String toString() {
            return "LocalVariableType[Slot=" + slot()
                   + ", name=" + nameString()
                   + ", signature='" + signature().stringValue()
                   + "']";
        }
    }
}
