package jdk.dynalink.linker.support;

import java.util.Objects;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.linker.LinkRequest;

/**
 * Default simple implementation of {@link LinkRequest}.
 */
public class SimpleLinkRequest implements LinkRequest {

    private final CallSiteDescriptor callSiteDescriptor;
    private final Object[] arguments;
    private final boolean callSiteUnstable;

    /**
     * Creates a new link request.
     *
     * @param callSiteDescriptor the descriptor for the call site being linked.
     * Must not be null.
     * @param callSiteUnstable true if the call site being linked is considered
     * unstable.
     * @param arguments the arguments for the invocation. Must not be null.
     * @throws NullPointerException if either {@code callSiteDescriptor} or
     * {@code arguments} is null.
     */
    public SimpleLinkRequest(final CallSiteDescriptor callSiteDescriptor, final boolean callSiteUnstable, final Object... arguments) {
        this.callSiteDescriptor = Objects.requireNonNull(callSiteDescriptor);
        this.callSiteUnstable = callSiteUnstable;
        this.arguments = arguments.clone();
    }

    @Override
    public Object[] getArguments() {
        return arguments.clone();
    }

    @Override
    public Object getReceiver() {
        return arguments.length > 0 ? arguments[0] : null;
    }

    @Override
    public CallSiteDescriptor getCallSiteDescriptor() {
        return callSiteDescriptor;
    }

    @Override
    public boolean isCallSiteUnstable() {
        return callSiteUnstable;
    }

    @Override
    public LinkRequest replaceArguments(final CallSiteDescriptor newCallSiteDescriptor, final Object... newArguments) {
        return new SimpleLinkRequest(newCallSiteDescriptor, callSiteUnstable, newArguments);
    }
}
