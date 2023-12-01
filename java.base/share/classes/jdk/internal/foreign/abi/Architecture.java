package jdk.internal.foreign.abi;

public interface Architecture {
    boolean isStackType(int cls);
    int typeSize(int cls);
}
