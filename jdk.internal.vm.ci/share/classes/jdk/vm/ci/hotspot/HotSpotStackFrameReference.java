package jdk.vm.ci.hotspot;

import java.util.Arrays;

import jdk.vm.ci.code.stack.InspectedFrame;
import jdk.vm.ci.meta.ResolvedJavaMethod;

public class HotSpotStackFrameReference implements InspectedFrame {

    private CompilerToVM compilerToVM;
    // set in the VM when materializeVirtualObjects is called
    @SuppressWarnings("unused") private boolean objectsMaterialized;

    // information used to find the stack frame
    private long stackPointer;
    private int frameNumber;

    // information about the stack frame's contents
    private int bci;
    private HotSpotResolvedJavaMethod method;
    private Object[] locals;
    private boolean[] localIsVirtual;

    public long getStackPointer() {
        return stackPointer;
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    @Override
    public Object getLocal(int index) {
        return locals[index];
    }

    @Override
    public boolean isVirtual(int index) {
        return localIsVirtual == null ? false : localIsVirtual[index];
    }

    @Override
    public void materializeVirtualObjects(boolean invalidateCode) {
        if (Thread.currentThread().isVirtual()) {
            throw new IllegalArgumentException("cannot materialize frames of a virtual thread");
        }
        compilerToVM.materializeVirtualObjects(this, invalidateCode);
    }

    @Override
    public int getBytecodeIndex() {
        return bci;
    }

    @Override
    public ResolvedJavaMethod getMethod() {
        return method;
    }

    @Override
    public boolean isMethod(ResolvedJavaMethod otherMethod) {
        return method.equals(otherMethod);
    }

    @Override
    public boolean hasVirtualObjects() {
        return localIsVirtual != null;
    }

    @Override
    public String toString() {
        return "HotSpotStackFrameReference [stackPointer=" + stackPointer + ", frameNumber=" + frameNumber + ", bci=" + bci + ", method=" + getMethod() + ", locals=" + Arrays.toString(locals) +
                        ", localIsVirtual=" + Arrays.toString(localIsVirtual) + "]";
    }
}
