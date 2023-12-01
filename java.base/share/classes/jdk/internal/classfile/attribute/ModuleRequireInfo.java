package jdk.internal.classfile.attribute;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import jdk.internal.classfile.constantpool.ModuleEntry;
import jdk.internal.classfile.constantpool.Utf8Entry;
import java.lang.reflect.AccessFlag;
import java.lang.constant.ModuleDesc;
import jdk.internal.classfile.impl.TemporaryConstantPool;
import jdk.internal.classfile.impl.UnboundAttribute;
import jdk.internal.classfile.impl.Util;

/**
 * Models a single "requires" declaration in the {@link jdk.internal.classfile.attribute.ModuleAttribute}.
 */
public sealed interface ModuleRequireInfo
        permits UnboundAttribute.UnboundModuleRequiresInfo {

    /**
     * {@return The module on which the current module depends}
     */
    ModuleEntry requires();

    /**
     * {@return the flags associated with this require declaration, as a bit mask}
     * Valid flags include {@link jdk.internal.classfile.Classfile#ACC_TRANSITIVE},
     * {@link jdk.internal.classfile.Classfile#ACC_STATIC_PHASE},
     * {@link jdk.internal.classfile.Classfile#ACC_SYNTHETIC} and
     * {@link jdk.internal.classfile.Classfile#ACC_MANDATED}
     */
    int requiresFlagsMask();

    default Set<AccessFlag> requiresFlags() {
        return AccessFlag.maskToAccessFlags(requiresFlagsMask(), AccessFlag.Location.MODULE_REQUIRES);
    }

    /**
     * {@return the required version of the required module, if present}
     */
    Optional<Utf8Entry> requiresVersion();

    /**
     * {@return whether the specific access flag is set}
     * @param flag the access flag
     */
    default boolean has(AccessFlag flag) {
        return Util.has(AccessFlag.Location.MODULE_REQUIRES, requiresFlagsMask(), flag);
    }

    /**
     * {@return a module requirement description}
     * @param requires the required module
     * @param requiresFlags the require-specific flags
     * @param requiresVersion the required version
     */
    static ModuleRequireInfo of(ModuleEntry requires, int requiresFlags, Utf8Entry requiresVersion) {
        return new UnboundAttribute.UnboundModuleRequiresInfo(requires, requiresFlags, Optional.ofNullable(requiresVersion));
    }

    /**
     * {@return a module requirement description}
     * @param requires the required module
     * @param requiresFlags the require-specific flags
     * @param requiresVersion the required version
     */
    static ModuleRequireInfo of(ModuleEntry requires, Collection<AccessFlag> requiresFlags, Utf8Entry requiresVersion) {
        return of(requires, Util.flagsToBits(AccessFlag.Location.MODULE_REQUIRES, requiresFlags), requiresVersion);
    }

    /**
     * {@return a module requirement description}
     * @param requires the required module
     * @param requiresFlags the require-specific flags
     * @param requiresVersion the required version
     */
    static ModuleRequireInfo of(ModuleDesc requires, int requiresFlags, String requiresVersion) {
        return new UnboundAttribute.UnboundModuleRequiresInfo(TemporaryConstantPool.INSTANCE.moduleEntry(TemporaryConstantPool.INSTANCE.utf8Entry(requires.name())), requiresFlags, Optional.ofNullable(requiresVersion).map(s -> TemporaryConstantPool.INSTANCE.utf8Entry(s)));
    }

    /**
     * {@return a module requirement description}
     * @param requires the required module
     * @param requiresFlags the require-specific flags
     * @param requiresVersion the required version
     */
    static ModuleRequireInfo of(ModuleDesc requires, Collection<AccessFlag> requiresFlags, String requiresVersion) {
        return of(requires, Util.flagsToBits(AccessFlag.Location.MODULE_REQUIRES, requiresFlags), requiresVersion);
    }
}
