package jdk.internal.foreign.abi.ppc64.linux;

import jdk.internal.foreign.abi.AbstractLinker;
import jdk.internal.foreign.abi.LinkerOptions;
import jdk.internal.foreign.abi.ppc64.CallArranger;

import java.lang.foreign.FunctionDescriptor;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.nio.ByteOrder;

public final class LinuxPPC64leLinker extends AbstractLinker {

    public static LinuxPPC64leLinker getInstance() {
        final class Holder {
            private static final LinuxPPC64leLinker INSTANCE = new LinuxPPC64leLinker();
        }

        return Holder.INSTANCE;
    }

    private LinuxPPC64leLinker() {
        // Ensure there is only one instance
    }

    @Override
    protected MethodHandle arrangeDowncall(MethodType inferredMethodType, FunctionDescriptor function, LinkerOptions options) {
        return CallArranger.ABIv2.arrangeDowncall(inferredMethodType, function, options);
    }

    @Override
    protected UpcallStubFactory arrangeUpcall(MethodType targetType, FunctionDescriptor function, LinkerOptions options) {
        return CallArranger.ABIv2.arrangeUpcall(targetType, function, options);
    }

    @Override
    protected ByteOrder linkerByteOrder() {
        return ByteOrder.LITTLE_ENDIAN;
    }
}
