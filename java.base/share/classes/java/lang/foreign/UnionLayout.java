package java.lang.foreign;

import jdk.internal.foreign.layout.UnionLayoutImpl;
import jdk.internal.javac.PreviewFeature;

/**
 * A group layout whose member layouts are laid out at the same starting offset.
 *
 * @implSpec
 * Implementing classes are immutable, thread-safe and <a href="{@docRoot}/java.base/java/lang/doc-files/ValueBased.html">value-based</a>.
 *
 * @since 20
 */
@PreviewFeature(feature=PreviewFeature.Feature.FOREIGN)
public sealed interface UnionLayout extends GroupLayout permits UnionLayoutImpl {

    /**
     * {@inheritDoc}
     */
    @Override
    UnionLayout withName(String name);

    /**
     * {@inheritDoc}
     */
    @Override
    UnionLayout withoutName();

    /**
     * {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    @Override
    UnionLayout withByteAlignment(long byteAlignment);
}
