package java.lang.foreign;

import jdk.internal.foreign.layout.StructLayoutImpl;
import jdk.internal.javac.PreviewFeature;

/**
 * A group layout whose member layouts are laid out one after the other.
 *
 * @implSpec
 * Implementing classes are immutable, thread-safe and <a href="{@docRoot}/java.base/java/lang/doc-files/ValueBased.html">value-based</a>.
 *
 * @since 20
 */
@PreviewFeature(feature=PreviewFeature.Feature.FOREIGN)
public sealed interface StructLayout extends GroupLayout permits StructLayoutImpl {

    /**
     * {@inheritDoc}
     */
    @Override
    StructLayout withName(String name);

    /**
     * {@inheritDoc}
     */
    @Override
    StructLayout withoutName();

    /**
     * {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    @Override
    StructLayout withByteAlignment(long byteAlignment);
}
