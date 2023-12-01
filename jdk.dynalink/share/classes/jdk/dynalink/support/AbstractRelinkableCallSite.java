package jdk.dynalink.support;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MutableCallSite;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.RelinkableCallSite;
import jdk.dynalink.linker.GuardedInvocation;

/**
 * A basic implementation of the {@link RelinkableCallSite} as a
 * {@link MutableCallSite}. It carries a {@link CallSiteDescriptor} passed in
 * the constructor and provides the correct implementation of the
 * {@link #initialize(MethodHandle)} method. Subclasses must provide
 * {@link #relink(GuardedInvocation, MethodHandle)} and
 * {@link #resetAndRelink(GuardedInvocation, MethodHandle)}
 * methods.
 */
public abstract class AbstractRelinkableCallSite extends MutableCallSite implements RelinkableCallSite {
    private final CallSiteDescriptor descriptor;

    /**
     * Creates a new abstract relinkable call site.
     * @param descriptor the descriptor for this call site that will be returned
     * from {@link #getDescriptor()}. The call site's {@link CallSite#type()}
     * will be equal to descriptor's {@link CallSiteDescriptor#getMethodType()}.
     * @throws NullPointerException if {@code descriptor} is null.
     */
    protected AbstractRelinkableCallSite(final CallSiteDescriptor descriptor) {
        super(descriptor.getMethodType());
        this.descriptor = descriptor;
    }

    @Override
    public CallSiteDescriptor getDescriptor() {
        return descriptor;
    }

    @Override
    public void initialize(final MethodHandle relinkAndInvoke) {
        setTarget(relinkAndInvoke);
    }
}
