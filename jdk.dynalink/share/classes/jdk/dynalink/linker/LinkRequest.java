package jdk.dynalink.linker;

import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.DynamicLinker;
import jdk.dynalink.DynamicLinkerFactory;

/**
 * Represents a request to link a particular invocation at a particular call
 * site. Instances of these requests will be constructed and passed to all
 * {@link GuardingDynamicLinker} objects managed by the {@link DynamicLinker}
 * that is trying to link the call site.
 */
public interface LinkRequest {
    /**
     * Returns the call site descriptor for the call site being linked.
     *
     * @return the call site descriptor for the call site being linked.
     */
    public CallSiteDescriptor getCallSiteDescriptor();

    /**
     * Returns the arguments for the invocation being linked. The returned array
     * must be a clone; modifications to it must not affect the arguments in
     * this request.
     *
     * @return the arguments for the invocation being linked.
     */
    public Object[] getArguments();

    /**
     * Returns the first argument for the invocation being linked; this is
     * typically the receiver object. This is a shorthand for
     * {@code getArguments()[0]} that also avoids the cloning of the arguments
     * array.
     *
     * @return the receiver object.
     */
    public Object getReceiver();

    /**
     * Returns true if the call site is considered unstable, that is, it has been relinked more times than was
     * specified in {@link DynamicLinkerFactory#setUnstableRelinkThreshold(int)}. Linkers should use this as a
     * hint to prefer producing linkage that is more stable (its guard fails less frequently), even if that assumption
     * causes a less effective version of an operation to be linked. This is just a hint, though, and linkers are
     * allowed to ignore this property.
     * @return true if the call site is considered unstable.
     */
    public boolean isCallSiteUnstable();

    /**
     * Returns a request identical to this one with call site descriptor and arguments replaced with the ones specified.
     *
     * @param callSiteDescriptor the new call site descriptor
     * @param arguments the new arguments
     * @return a new request identical to this one, except with the call site descriptor and arguments replaced with the
     * specified ones.
     */
    public LinkRequest replaceArguments(CallSiteDescriptor callSiteDescriptor, Object... arguments);
}
