package jdk.internal.foreign.layout;

import jdk.internal.foreign.Utils;

import java.lang.foreign.GroupLayout;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.SequenceLayout;
import java.lang.foreign.StructLayout;
import java.lang.foreign.UnionLayout;
import java.lang.foreign.ValueLayout;
import java.util.Objects;
import java.util.Optional;

public abstract sealed class AbstractLayout<L extends AbstractLayout<L> & MemoryLayout>
        permits AbstractGroupLayout, PaddingLayoutImpl, SequenceLayoutImpl, ValueLayouts.AbstractValueLayout {

    private final long byteSize;
    private final long byteAlignment;
    private final Optional<String> name;

    AbstractLayout(long byteSize, long byteAlignment, Optional<String> name) {
        this.byteSize = MemoryLayoutUtil.requireByteSizeValid(byteSize, true);
        this.byteAlignment = requirePowerOfTwoAndGreaterOrEqualToOne(byteAlignment);
        this.name = Objects.requireNonNull(name);
    }

    public final L withName(String name) {
        return dup(byteAlignment(), Optional.of(name));
    }

    public final L withoutName() {
        return dup(byteAlignment(), Optional.empty());
    }

    public final Optional<String> name() {
        return name;
    }

    public L withByteAlignment(long byteAlignment) {
        return dup(byteAlignment, name);
    }

    public final long byteAlignment() {
        return byteAlignment;
    }

    public final long byteSize() {
        return byteSize;
    }

    public boolean hasNaturalAlignment() {
        return byteSize == byteAlignment;
    }

    // the following methods have to copy the same Javadoc as in MemoryLayout, or subclasses will just show
    // the Object methods javadoc

    /**
     * {@return the hash code value for this layout}
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, byteSize, byteAlignment);
    }

    /**
     * Compares the specified object with this layout for equality. Returns {@code true} if and only if the specified
     * object is also a layout, and it is equal to this layout. Two layouts are considered equal if they are of
     * the same kind, have the same size, name and alignment constraints. Furthermore, depending on the layout kind, additional
     * conditions must be satisfied:
     * <ul>
     *     <li>two value layouts are considered equal if they have the same {@linkplain ValueLayout#order() order},
     *     and {@linkplain ValueLayout#carrier() carrier}</li>
     *     <li>two sequence layouts are considered equal if they have the same element count (see {@link SequenceLayout#elementCount()}), and
     *     if their element layouts (see {@link SequenceLayout#elementLayout()}) are also equal</li>
     *     <li>two group layouts are considered equal if they are of the same type (see {@link StructLayout},
     *     {@link UnionLayout}) and if their member layouts (see {@link GroupLayout#memberLayouts()}) are also equal</li>
     * </ul>
     *
     * @param other the object to be compared for equality with this layout.
     * @return {@code true} if the specified object is equal to this layout.
     */
    @Override
    public boolean equals(Object other) {
        return other instanceof AbstractLayout<?> otherLayout &&
                name.equals(otherLayout.name) &&
                byteSize == otherLayout.byteSize &&
                byteAlignment == otherLayout.byteAlignment;
    }

    /**
     * {@return the string representation of this layout}
     */
    @Override
    public abstract String toString();

    abstract L dup(long byteAlignment, Optional<String> name);

    String decorateLayoutString(String s) {
        if (name().isPresent()) {
            s = String.format("%s(%s)", s, name().get());
        }
        if (!hasNaturalAlignment()) {
            s = byteAlignment() + "%" + s;
        }
        return s;
    }

    private static long requirePowerOfTwoAndGreaterOrEqualToOne(long value) {
        if (!Utils.isPowerOfTwo(value) || // value must be a power of two
                value < 1) { // value must be greater or equal to 1
            throw new IllegalArgumentException("Invalid alignment: " + value);
        }
        return value;
    }

}
