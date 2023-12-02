package jdk.vm.ci.meta;

public class RawConstant extends PrimitiveConstant {

    public RawConstant(long rawValue) {
        super(JavaKind.Int, rawValue);
    }
}
