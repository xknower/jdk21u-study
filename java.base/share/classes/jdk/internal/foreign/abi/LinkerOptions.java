package jdk.internal.foreign.abi;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class LinkerOptions {

    private static final LinkerOptions EMPTY = new LinkerOptions(Map.of());
    private final Map<Class<?>, LinkerOptionImpl> optionsMap;

    private LinkerOptions(Map<Class<?>, LinkerOptionImpl> optionsMap) {
        this.optionsMap = optionsMap;
    }

    public static LinkerOptions forDowncall(FunctionDescriptor desc, Linker.Option... options) {
        return forShared(LinkerOptionImpl::validateForDowncall, desc, options);
    }

    public static LinkerOptions forUpcall(FunctionDescriptor desc, Linker.Option[] options) {
        return forShared(LinkerOptionImpl::validateForUpcall, desc, options);
    }

    private static LinkerOptions forShared(BiConsumer<LinkerOptionImpl, FunctionDescriptor> validator,
                                           FunctionDescriptor desc, Linker.Option... options) {
       Map<Class<?>, LinkerOptionImpl> optionMap = new HashMap<>();

        for (Linker.Option option : options) {
            if (optionMap.containsKey(option.getClass())) {
                throw new IllegalArgumentException("Duplicate option: " + option);
            }
            LinkerOptionImpl opImpl = (LinkerOptionImpl) option;
            validator.accept(opImpl, desc);
            optionMap.put(option.getClass(), opImpl);
        }

        return new LinkerOptions(optionMap);
    }

    public static LinkerOptions empty() {
        return EMPTY;
    }

    private <T extends Linker.Option> T getOption(Class<T> type) {
        return type.cast(optionsMap.get(type));
    }

    public boolean isVarargsIndex(int argIndex) {
        FirstVariadicArg fva = getOption(FirstVariadicArg.class);
        return fva != null && argIndex >= fva.index();
    }

    public boolean hasCapturedCallState() {
        return getOption(CaptureCallState.class) != null;
    }

    public Stream<CapturableState> capturedCallState() {
        CaptureCallState stl = getOption(CaptureCallState.class);
        return stl == null ? Stream.empty() : stl.saved().stream();
    }

    public boolean isVariadicFunction() {
        FirstVariadicArg fva = getOption(FirstVariadicArg.class);
        return fva != null;
    }

    public int firstVariadicArgIndex() {
        return getOption(FirstVariadicArg.class).index();
    }

    public boolean isTrivial() {
        IsTrivial it = getOption(IsTrivial.class);
        return it != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o instanceof LinkerOptions that
                && Objects.equals(optionsMap, that.optionsMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(optionsMap);
    }

    public sealed interface LinkerOptionImpl extends Linker.Option
            permits CaptureCallState, FirstVariadicArg, IsTrivial {
        default void validateForDowncall(FunctionDescriptor descriptor) {
            throw new IllegalArgumentException("Not supported for downcall: " + this);
        }

        default void validateForUpcall(FunctionDescriptor descriptor) {
            throw new IllegalArgumentException("Not supported for upcall: " + this);
        }
    }

    public record FirstVariadicArg(int index) implements LinkerOptionImpl {
        @Override
        public void validateForDowncall(FunctionDescriptor descriptor) {
            if (index < 0 || index > descriptor.argumentLayouts().size()) {
                throw new IllegalArgumentException("Index '" + index + "' not in bounds for descriptor: " + descriptor);
            }
        }
    }

    public record CaptureCallState(Set<CapturableState> saved) implements LinkerOptionImpl {
        @Override
        public void validateForDowncall(FunctionDescriptor descriptor) {
            // done during construction
        }
    }

    public record IsTrivial() implements LinkerOptionImpl {
        public static IsTrivial INSTANCE = new IsTrivial();

        @Override
        public void validateForDowncall(FunctionDescriptor descriptor) {
            // always allowed
        }
    }
}
