package jdk.dynalink.beans;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.SecureLookupSupplier;
import jdk.dynalink.internal.AccessControlContextFactory;
import jdk.dynalink.linker.support.Lookup;

/**
 * A dynamic method bound to exactly one Java method or constructor that is caller sensitive. Since the target method is
 * caller sensitive, it doesn't cache a method handle but rather uses the passed lookup object in
 * {@link #getTarget(CallSiteDescriptor)} to unreflect a method handle from the reflective member on
 * every request.
 */
class CallerSensitiveDynamicMethod extends SingleDynamicMethod {
    @SuppressWarnings("removal")
    private static final AccessControlContext GET_LOOKUP_CONTEXT =
            AccessControlContextFactory.createAccessControlContext(
                    SecureLookupSupplier.GET_LOOKUP_PERMISSION_NAME);

    private final Executable target;
    private final MethodType type;

    CallerSensitiveDynamicMethod(final Executable target) {
        super(getName(target));
        this.target = target;
        this.type = getMethodType(target);
    }

    private static String getName(final Executable m) {
        final boolean constructor = m instanceof Constructor;
        return getMethodNameWithSignature(getMethodType(m), constructor ? m.getName() :
            getClassAndMethodName(m.getDeclaringClass(), m.getName()), !constructor);
    }

    @Override
    MethodType getMethodType() {
        return type;
    }

    private static MethodType getMethodType(final Executable m) {
        final boolean isMethod = m instanceof Method;
        final Class<?> rtype = isMethod ? ((Method)m).getReturnType() : ((Constructor<?>)m).getDeclaringClass();
        final Class<?>[] ptypes = m.getParameterTypes();
        final MethodType type = MethodType.methodType(rtype, ptypes);
        return type.insertParameterTypes(0,
                isMethod ?
                        Modifier.isStatic(m.getModifiers()) ?
                                Object.class :
                                m.getDeclaringClass() :
                        StaticClass.class);
    }

    @Override
    boolean isVarArgs() {
        return target.isVarArgs();
    }

    @Override
    MethodHandle getTarget(final CallSiteDescriptor desc) {
        @SuppressWarnings("removal")
        final MethodHandles.Lookup lookup = AccessController.doPrivileged(
                (PrivilegedAction<MethodHandles.Lookup>)desc::getLookup,
                GET_LOOKUP_CONTEXT);

        if(target instanceof Method) {
            final MethodHandle mh = unreflect(lookup, (Method)target);
            if(Modifier.isStatic(target.getModifiers())) {
                return StaticClassIntrospector.editStaticMethodHandle(mh);
            }
            return mh;
        }
        return StaticClassIntrospector.editConstructorMethodHandle(unreflectConstructor(lookup,
                (Constructor<?>)target));
    }

    @Override
    boolean isConstructor() {
        return target instanceof Constructor;
    }

    private static MethodHandle unreflect(final MethodHandles.Lookup lookup, final Method m) {
        return Lookup.unreflect(lookup, m);
    }

    private static MethodHandle unreflectConstructor(final MethodHandles.Lookup lookup, final Constructor<?> c) {
        return Lookup.unreflectConstructor(lookup, c);
    }
}
