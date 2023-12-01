package jdk.dynalink.linker.support;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.GuardingDynamicLinker;
import jdk.dynalink.linker.LinkRequest;
import jdk.dynalink.linker.LinkerServices;

/**
 * A {@link GuardingDynamicLinker} that delegates sequentially to a list of
 * other guarding dynamic linkers in its
 * {@link #getGuardedInvocation(LinkRequest, LinkerServices)}.
 */
public class CompositeGuardingDynamicLinker implements GuardingDynamicLinker {

    private final GuardingDynamicLinker[] linkers;

    /**
     * Creates a new composite linker.
     *
     * @param linkers a list of component linkers.
     * @throws NullPointerException if {@code linkers} or any of its elements
     * are null.
     */
    public CompositeGuardingDynamicLinker(final Iterable<? extends GuardingDynamicLinker> linkers) {
        final List<GuardingDynamicLinker> l = new LinkedList<>();
        for(final GuardingDynamicLinker linker: linkers) {
            l.add(Objects.requireNonNull(linker));
        }
        this.linkers = l.toArray(new GuardingDynamicLinker[0]);
    }

    /**
     * Delegates the call to its component linkers. The first non-null value
     * returned from a component linker is returned. If no component linker
     * returns a non-null invocation, null is returned.
     * @param linkRequest the object describing the request for linking a
     * particular invocation
     * @param linkerServices linker services
     * @return the first non-null return value from a component linker, or null
     * if none of the components returned a non-null.
     */
    @Override
    public GuardedInvocation getGuardedInvocation(final LinkRequest linkRequest, final LinkerServices linkerServices)
            throws Exception {
        for(final GuardingDynamicLinker linker: linkers) {
            final GuardedInvocation invocation = linker.getGuardedInvocation(linkRequest, linkerServices);
            if(invocation != null) {
                return invocation;
            }
        }
        return null;
    }
}
