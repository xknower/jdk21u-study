package jdk.dynalink.beans;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.NamedOperation;
import jdk.dynalink.Operation;
import jdk.dynalink.StandardNamespace;
import jdk.dynalink.StandardOperation;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.LinkRequest;
import jdk.dynalink.linker.LinkerServices;
import jdk.dynalink.linker.TypeBasedGuardingDynamicLinker;
import jdk.dynalink.linker.support.Guards;

/**
 * Simple linker that implements the {@link StandardOperation#CALL} operation
 * for {@link DynamicMethod} objects - the objects returned by
 * {@link StandardOperation#GET} on {@link StandardNamespace#METHOD} namespace through
 * {@link AbstractJavaLinker}.
 */
class DynamicMethodLinker implements TypeBasedGuardingDynamicLinker {
    @Override
    public boolean canLinkType(final Class<?> type) {
        return DynamicMethod.class.isAssignableFrom(type);
    }

    @Override
    public GuardedInvocation getGuardedInvocation(final LinkRequest linkRequest, final LinkerServices linkerServices) {
        final Object receiver = linkRequest.getReceiver();
        if(!(receiver instanceof DynamicMethod)) {
            return null;
        }
        final DynamicMethod dynMethod = (DynamicMethod)receiver;
        final boolean constructor = dynMethod.isConstructor();
        final MethodHandle invocation;

        final CallSiteDescriptor desc = linkRequest.getCallSiteDescriptor();
        final Operation op = NamedOperation.getBaseOperation(desc.getOperation());
        if (op == StandardOperation.CALL && !constructor) {
            invocation = dynMethod.getInvocation(desc.changeMethodType(
                    desc.getMethodType().dropParameterTypes(0, 1)), linkerServices);
        } else if (op == StandardOperation.NEW && constructor) {
            final MethodHandle ctorInvocation = dynMethod.getInvocation(desc, linkerServices);
            if(ctorInvocation == null) {
                return null;
            }

            // Insert null for StaticClass parameter
            invocation = MethodHandles.insertArguments(ctorInvocation, 0, (Object)null);
        } else {
            return null;
        }

        if (invocation != null) {
            return new GuardedInvocation(MethodHandles.dropArguments(invocation, 0,
                desc.getMethodType().parameterType(0)), Guards.getIdentityGuard(receiver));
        }

        return null;
    }
}
