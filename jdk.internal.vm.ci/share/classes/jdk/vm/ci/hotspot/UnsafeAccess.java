package jdk.vm.ci.hotspot;

import jdk.internal.misc.Unsafe;

/**
 * Package private access to the {@link Unsafe} capability.
 */
class UnsafeAccess {

    static final Unsafe UNSAFE = Unsafe.getUnsafe();
}
