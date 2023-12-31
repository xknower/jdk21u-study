package jdk.vm.ci.hotspot;

import static jdk.vm.ci.hotspot.HotSpotJVMCIRuntime.runtime;

import java.util.Objects;

import jdk.vm.ci.common.JVMCIError;
import jdk.vm.ci.meta.Constant;
import jdk.vm.ci.meta.ConstantReflectionProvider;
import jdk.vm.ci.meta.JavaConstant;
import jdk.vm.ci.meta.JavaKind;
import jdk.vm.ci.meta.MemoryAccessProvider;
import jdk.vm.ci.meta.MethodHandleAccessProvider;
import jdk.vm.ci.meta.ResolvedJavaField;
import jdk.vm.ci.meta.ResolvedJavaType;

/**
 * HotSpot implementation of {@link ConstantReflectionProvider}.
 */
public class HotSpotConstantReflectionProvider implements ConstantReflectionProvider {

    protected final HotSpotJVMCIRuntime runtime;
    protected final HotSpotMethodHandleAccessProvider methodHandleAccess;
    private final HotSpotMemoryAccessProviderImpl memoryAccess;

    public HotSpotConstantReflectionProvider(HotSpotJVMCIRuntime runtime) {
        this.runtime = runtime;
        this.methodHandleAccess = new HotSpotMethodHandleAccessProvider(this);
        this.memoryAccess = new HotSpotMemoryAccessProviderImpl(runtime);
    }

    @Override
    public MethodHandleAccessProvider getMethodHandleAccess() {
        return methodHandleAccess;
    }

    @Override
    public MemoryAccessProvider getMemoryAccessProvider() {
        return memoryAccess;
    }

    @Override
    public Boolean constantEquals(Constant x, Constant y) {
        if (x == y) {
            return true;
        } else if (x instanceof HotSpotObjectConstantImpl) {
            return y instanceof HotSpotObjectConstantImpl && x.equals(y);
        } else {
            return Objects.equals(x, y);
        }
    }

    @Override
    public Integer readArrayLength(JavaConstant array) {
        if (array == null || array.getJavaKind() != JavaKind.Object || array.isNull()) {
            return null;
        }

        HotSpotObjectConstantImpl arrayObject = ((HotSpotObjectConstantImpl) array);
        return runtime.getReflection().getLength(arrayObject);
    }

    @Override
    public JavaConstant readArrayElement(JavaConstant array, int index) {
        if (array == null || array.getJavaKind() != JavaKind.Object || array.isNull()) {
            return null;
        }
        HotSpotObjectConstantImpl arrayObject = ((HotSpotObjectConstantImpl) array);
        return runtime.getReflection().readArrayElement(arrayObject, index);
    }

    /**
     * Check if the constant is a boxed value that is guaranteed to be cached by the platform.
     * Otherwise the generated code might be the only reference to the boxed value and since object
     * references from nmethods are weak this can cause GC problems.
     *
     * @return true if the box is cached
     */
    private static boolean isBoxCached(JavaConstant source) {
        switch (source.getJavaKind()) {
            case Boolean:
                return true;
            case Char:
                return source.asInt() <= 127;
            case Byte:
            case Short:
            case Int:
                return source.asInt() >= -128 && source.asInt() <= 127;
            case Long:
                return source.asLong() >= -128 && source.asLong() <= 127;
            case Float:
            case Double:
                return false;
            default:
                throw new IllegalArgumentException("unexpected kind " + source.getJavaKind());
        }
    }

    @Override
    public JavaConstant boxPrimitive(JavaConstant source) {
        if (source == null || !source.getJavaKind().isPrimitive() || !isBoxCached(source)) {
            return null;
        }
        return runtime.getReflection().boxPrimitive(source);
    }

    @Override
    public JavaConstant unboxPrimitive(JavaConstant source) {
        if (source == null || !source.getJavaKind().isObject()) {
            return null;
        }
        if (source.isNull()) {
            return null;
        }
        return runtime.getReflection().unboxPrimitive((HotSpotObjectConstantImpl) source);
    }

    @Override
    public JavaConstant forString(String value) {
        return runtime.getReflection().forObject(value);
    }

    public JavaConstant forObject(Object value) {
        return runtime.getReflection().forObject(value);
    }

    @Override
    public ResolvedJavaType asJavaType(Constant constant) {
        if (constant instanceof HotSpotObjectConstantImpl) {
            return ((HotSpotObjectConstantImpl) constant).asJavaType();
        }
        if (constant instanceof HotSpotMetaspaceConstant) {
            MetaspaceObject obj = HotSpotMetaspaceConstantImpl.getMetaspaceObject(constant);
            if (obj instanceof HotSpotResolvedObjectTypeImpl) {
                return (ResolvedJavaType) obj;
            }
        }
        return null;
    }

    @Override
    public JavaConstant readFieldValue(ResolvedJavaField field, JavaConstant receiver) {
        HotSpotResolvedJavaField hotspotField = (HotSpotResolvedJavaField) field;
        if (hotspotField.isStatic()) {
            HotSpotResolvedObjectTypeImpl declaringType = (HotSpotResolvedObjectTypeImpl) hotspotField.getDeclaringClass();
            if (declaringType.isInitialized()) {
                return runtime().compilerToVm.readStaticFieldValue(declaringType, hotspotField.getOffset(),
                                hotspotField.getType().getJavaKind().getTypeChar());
            }
        } else if (receiver instanceof HotSpotObjectConstantImpl) {
            return ((HotSpotObjectConstantImpl) receiver).readFieldValue(hotspotField);
        } else if (receiver == null) {
            throw new NullPointerException("receiver is null");
        }
        return null;
    }

    @Override
    public JavaConstant asJavaClass(ResolvedJavaType type) {
        return ((HotSpotResolvedJavaType) type).getJavaMirror();
    }

    @Override
    public Constant asObjectHub(ResolvedJavaType type) {
        if (type instanceof HotSpotResolvedObjectType) {
            return ((HotSpotResolvedObjectType) type).klass();
        } else {
            throw JVMCIError.unimplemented();
        }
    }
}
