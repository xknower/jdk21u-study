package jdk.vm.ci.hotspot;

import static jdk.vm.ci.hotspot.HotSpotJVMCIRuntime.runtime;
import static jdk.vm.ci.hotspot.UnsafeAccess.UNSAFE;

import jdk.vm.ci.meta.Constant;
import jdk.vm.ci.meta.JavaConstant;
import jdk.vm.ci.meta.JavaKind;
import jdk.vm.ci.meta.MemoryAccessProvider;
import jdk.vm.ci.meta.PrimitiveConstant;

/**
 * HotSpot implementation of {@link MemoryAccessProvider}.
 */
class HotSpotMemoryAccessProviderImpl implements HotSpotMemoryAccessProvider {

    protected final HotSpotJVMCIRuntime runtime;

    HotSpotMemoryAccessProviderImpl(HotSpotJVMCIRuntime runtime) {
        this.runtime = runtime;
    }

    private static long asRawPointer(Constant base) {
        if (base instanceof HotSpotMetaspaceConstantImpl) {
            MetaspaceObject meta = HotSpotMetaspaceConstantImpl.getMetaspaceObject(base);
            return meta.getMetaspacePointer();
        } else if (base instanceof PrimitiveConstant) {
            PrimitiveConstant prim = (PrimitiveConstant) base;
            if (prim.getJavaKind().isNumericInteger()) {
                return prim.asLong();
            }
        }
        throw new IllegalArgumentException(String.valueOf(base));
    }

    @Override
    public JavaConstant readPrimitiveConstant(JavaKind kind, Constant baseConstant, long initialDisplacement, int bits) {
        if (baseConstant instanceof HotSpotObjectConstantImpl) {
            JavaKind readKind = kind;
            if (kind.getBitCount() != bits) {
                switch (bits) {
                    case Byte.SIZE:
                        readKind = JavaKind.Byte;
                        break;
                    case Short.SIZE:
                        readKind = JavaKind.Short;
                        break;
                    case Integer.SIZE:
                        readKind = JavaKind.Int;
                        break;
                    case Long.SIZE:
                        readKind = JavaKind.Long;
                        break;
                    default:
                        throw new IllegalArgumentException(String.valueOf(bits));
                }
            }
            HotSpotObjectConstantImpl baseObject = (HotSpotObjectConstantImpl) baseConstant;
            JavaConstant result = runtime().compilerToVm.readFieldValue(baseObject, null, initialDisplacement, readKind.getTypeChar());
            if (result != null && kind != readKind) {
                return JavaConstant.forPrimitive(kind, result.asLong());
            }
            return result;
        } else {
            long pointer = asRawPointer(baseConstant);
            long value;
            switch (bits) {
                case Byte.SIZE:
                    value = UNSAFE.getByte(pointer + initialDisplacement);
                    break;
                case Short.SIZE:
                    value = UNSAFE.getShort(pointer + initialDisplacement);
                    break;
                case Integer.SIZE:
                    value = UNSAFE.getInt(pointer + initialDisplacement);
                    break;
                case Long.SIZE:
                    value = UNSAFE.getLong(pointer + initialDisplacement);
                    break;
                default:
                    throw new IllegalArgumentException(String.valueOf(bits));
            }
            return JavaConstant.forPrimitive(kind, value);
        }
    }

    @Override
    public JavaConstant readObjectConstant(Constant base, long displacement) {
        if (base instanceof HotSpotObjectConstantImpl) {
            HotSpotObjectConstantImpl hsBase = (HotSpotObjectConstantImpl) base;
            return runtime.getCompilerToVM().readFieldValue(hsBase, null, displacement, JavaKind.Object.getTypeChar());
        }
        if (base instanceof HotSpotMetaspaceConstant) {
            MetaspaceObject metaspaceObject = HotSpotMetaspaceConstantImpl.getMetaspaceObject(base);
            if (metaspaceObject instanceof HotSpotResolvedObjectTypeImpl) {
                HotSpotResolvedObjectTypeImpl type = (HotSpotResolvedObjectTypeImpl) metaspaceObject;
                if (displacement == runtime.getConfig().javaMirrorOffset) {
                    // Klass::_java_mirror is valid for all Klass* values
                    return type.getJavaMirror();
                }
                return null;
            } else {
                throw new IllegalArgumentException(String.valueOf(metaspaceObject));
            }
        }
        return null;
    }

    @Override
    public JavaConstant readNarrowOopConstant(Constant base, long displacement) {
        if (base instanceof HotSpotObjectConstantImpl) {
            assert runtime.getConfig().useCompressedOops;
            HotSpotObjectConstantImpl hsBase = (HotSpotObjectConstantImpl) base;
            JavaConstant res = runtime.getCompilerToVM().readFieldValue(hsBase, null, displacement, JavaKind.Object.getTypeChar());
            if (res != null) {
                return JavaConstant.NULL_POINTER.equals(res) ? HotSpotCompressedNullConstant.COMPRESSED_NULL : ((HotSpotObjectConstant) res).compress();
            }
        }
        return null;
    }

    private HotSpotResolvedObjectTypeImpl readKlass(Constant base, long displacement, boolean compressed) {
        assert (base instanceof HotSpotMetaspaceConstantImpl) || (base instanceof HotSpotObjectConstantImpl) : base.getClass();
        if (base instanceof HotSpotMetaspaceConstantImpl) {
            return runtime.getCompilerToVM().getResolvedJavaType((HotSpotResolvedObjectTypeImpl) ((HotSpotMetaspaceConstantImpl) base).asResolvedJavaType(), displacement, compressed);
        } else {
            return runtime.getCompilerToVM().getResolvedJavaType(((HotSpotObjectConstantImpl) base), displacement, compressed);
        }
    }

    @Override
    public Constant readKlassPointerConstant(Constant base, long displacement) {
        HotSpotResolvedObjectTypeImpl klass = readKlass(base, displacement, false);
        if (klass == null) {
            return JavaConstant.NULL_POINTER;
        }
        return HotSpotMetaspaceConstantImpl.forMetaspaceObject(klass, false);
    }

    @Override
    public Constant readNarrowKlassPointerConstant(Constant base, long displacement) {
        HotSpotResolvedObjectTypeImpl klass = readKlass(base, displacement, true);
        if (klass == null) {
            return HotSpotCompressedNullConstant.COMPRESSED_NULL;
        }
        return HotSpotMetaspaceConstantImpl.forMetaspaceObject(klass, true);
    }

    @Override
    public Constant readMethodPointerConstant(Constant base, long displacement) {
        assert (base instanceof HotSpotObjectConstantImpl);
        HotSpotResolvedJavaMethodImpl method = runtime.getCompilerToVM().getResolvedJavaMethod((HotSpotObjectConstantImpl) base, displacement);
        return HotSpotMetaspaceConstantImpl.forMetaspaceObject(method, false);
    }
}
