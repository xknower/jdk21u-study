package jdk.vm.ci.hotspot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jdk.vm.ci.services.Services;

/**
 * A mechanism for limiting the lifetime of a foreign object reference encapsulated in a
 * {@link HotSpotObjectConstant}.
 *
 * A {@link HotSpotObjectConstant} allocated in a {@linkplain #openLocalScope local} scope will have
 * its reference to any foreign object cleared when the scope {@linkplain #close() closes}. This
 * allows the foreign memory manager to reclaim the foreign object (once there are no other strong
 * references to it).
 *
 * {@link HotSpotObjectConstantScope}s have no impact on {@link HotSpotObjectConstant}s that do not
 * encapsulate a foreign object reference.
 *
 * The object returned by {@link #enterGlobalScope()} or {@link #openLocalScope(Object)} should
 * always be used in a try-with-resources statement. Failure to close a scope will almost certainly
 * result in foreign objects being leaked.
 */
public final class HotSpotObjectConstantScope implements AutoCloseable {
    static final ThreadLocal<HotSpotObjectConstantScope> CURRENT = new ThreadLocal<>();

    private final HotSpotObjectConstantScope parent;
    private List<IndirectHotSpotObjectConstantImpl> foreignObjects;

    /**
     * An object whose {@link Object#toString()} value describes a non-global scope. This is
     * {@code null} iff this is a global scope.
     */
    final Object localScopeDescription;

    /**
     * Opens a local scope that upon closing, will release foreign object references encapsulated by
     * {@link HotSpotObjectConstant}s created in the scope.
     *
     * @param description an non-null object whose {@link Object#toString()} value describes the
     *            scope being opened
     * @return {@code null} if the current runtime does not support remote object references
     */
    public static HotSpotObjectConstantScope openLocalScope(Object description) {
        return Services.IS_IN_NATIVE_IMAGE ? new HotSpotObjectConstantScope(Objects.requireNonNull(description)) : null;
    }

    /**
     * Enters the global scope. This is useful to escape a local scope for execution that will
     * create foreign object references that need to outlive the local scope.
     *
     * Foreign object references encapsulated by {@link HotSpotObjectConstant}s created in the
     * global scope are only subject to reclamation once the {@link HotSpotObjectConstant} wrapper
     * dies.
     *
     * @return {@code null} if the current runtime does not support remote object references or if
     *         this thread is currently in the global scope
     */
    public static HotSpotObjectConstantScope enterGlobalScope() {
        return Services.IS_IN_NATIVE_IMAGE && CURRENT.get() != null ? new HotSpotObjectConstantScope(null) : null;
    }

    private HotSpotObjectConstantScope(Object localScopeDescription) {
        this.parent = CURRENT.get();
        CURRENT.set(this);
        this.localScopeDescription = localScopeDescription;
    }

    /**
     * Determines if this scope is global.
     */
    boolean isGlobal() {
        return localScopeDescription == null;
    }

    void add(IndirectHotSpotObjectConstantImpl obj) {
        assert !isGlobal();
        if (foreignObjects == null) {
            foreignObjects = new ArrayList<>();
        }
        foreignObjects.add(obj);
    }

    @VMEntryPoint
    @Override
    public void close() {
        if (CURRENT.get() != this) {
            throw new IllegalStateException("Cannot close non-active scope");
        }
        if (foreignObjects != null) {
            for (IndirectHotSpotObjectConstantImpl obj : foreignObjects) {
                obj.clear(localScopeDescription);
            }
            foreignObjects = null;
            CompilerToVM.compilerToVM().releaseClearedOopHandles();
        }
        CURRENT.set(parent);
    }
}
