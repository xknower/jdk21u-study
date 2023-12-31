package jdk.vm.ci.hotspot;

import java.lang.invoke.CallSite;
import java.util.Objects;

import jdk.vm.ci.meta.Assumptions;
import jdk.vm.ci.meta.JavaConstant;
import jdk.vm.ci.meta.ResolvedJavaType;
import jdk.vm.ci.meta.VMConstant;

/**
 * Represents a constant non-{@code null} object reference, within the compiler and across the
 * compiler/runtime interface.
 */
public interface HotSpotObjectConstant extends JavaConstant, HotSpotConstant, VMConstant {

    @Override
    JavaConstant compress();

    @Override
    JavaConstant uncompress();

    /**
     * Gets the resolved Java type of the object represented by this constant.
     */
    HotSpotResolvedObjectType getType();

    /**
     * Gets the {@linkplain System#identityHashCode(Object) identity} has code for the object
     * represented by this constant.
     */
    int getIdentityHashCode();

    /**
     * Gets the result of {@link CallSite#getTarget()} for the {@link CallSite} object represented
     * by this constant.
     *
     * @param assumptions used to register an assumption that the {@link CallSite}'s target does not
     *            change
     * @return {@code null} if this constant does not represent a {@link CallSite} object
     */
    JavaConstant getCallSiteTarget(Assumptions assumptions);

    /**
     * Determines if this constant represents an {@linkplain String#intern() interned} string.
     */
    boolean isInternedString();

    /**
     * Gets the object represented by this constant represents if it is of a given type.
     *
     * @param type the expected type of the object represented by this constant. If the object is
     *            required to be of this type, then wrap the call to this method in
     *            {@link Objects#requireNonNull(Object)}.
     * @return the object value represented by this constant if it is an
     *         {@link ResolvedJavaType#isInstance(JavaConstant) instance of} {@code type} otherwise
     *         {@code null}
     */
    <T> T asObject(Class<T> type);

    /**
     * Gets the object represented by this constant represents if it is of a given type.
     *
     * @param type the expected type of the object represented by this constant. If the object is
     *            required to be of this type, then wrap the call to this method in
     *            {@link Objects#requireNonNull(Object)}.
     * @return the object value represented by this constant if it is an
     *         {@link ResolvedJavaType#isInstance(JavaConstant) instance of} {@code type} otherwise
     *         {@code null}
     */
    Object asObject(ResolvedJavaType type);

    @Override
    String toValueString();
}
