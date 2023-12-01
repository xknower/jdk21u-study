package jdk.dynalink.support;

import java.lang.invoke.MethodHandle;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.DynamicLinker;
import jdk.dynalink.linker.GuardedInvocation;

/**
 * A relinkable call site that implements monomorphic inline caching strategy,
 * only being linked to a single {@link GuardedInvocation} at any given time.
 * If the guard of that single invocation fails, or it has an invalidated
 * switch point, or its invalidating exception triggered, then the call site
 * will throw it away and ask its associated {@link DynamicLinker} to relink it.
 */
public class SimpleRelinkableCallSite extends AbstractRelinkableCallSite {
    /**
     * Creates a new call site with monomorphic inline caching strategy.
     * @param descriptor the descriptor for this call site
     */
    public SimpleRelinkableCallSite(final CallSiteDescriptor descriptor) {
        super(descriptor);
    }

    @Override
    public void relink(final GuardedInvocation guardedInvocation, final MethodHandle relinkAndInvoke) {
        setTarget(guardedInvocation.compose(relinkAndInvoke));
    }

    @Override
    public void resetAndRelink(final GuardedInvocation guardedInvocation, final MethodHandle relinkAndInvoke) {
        relink(guardedInvocation, relinkAndInvoke);
    }
}
