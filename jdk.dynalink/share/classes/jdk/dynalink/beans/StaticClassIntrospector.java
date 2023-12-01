package jdk.dynalink.beans;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class StaticClassIntrospector extends FacetIntrospector {
    StaticClassIntrospector(final Class<?> clazz) {
        super(clazz, false);
    }

    @Override
    Map<String, MethodHandle> getInnerClassGetters() {
        final Map<String, MethodHandle> map = new HashMap<>();
        for(final Class<?> innerClass: membersLookup.getInnerClasses()) {
            map.put(innerClass.getSimpleName(), editMethodHandle(MethodHandles.constant(StaticClass.class,
                    StaticClass.forClass(innerClass))));
        }
        return map;
    }

    @Override Collection<Method> getRecordComponentGetters() {
        return List.of();
    }

    @Override
    MethodHandle editMethodHandle(final MethodHandle mh) {
        return editStaticMethodHandle(mh);
    }

    static MethodHandle editStaticMethodHandle(final MethodHandle mh) {
        return dropReceiver(mh, Object.class);
    }

    static MethodHandle editConstructorMethodHandle(final MethodHandle cmh) {
        return dropReceiver(cmh, StaticClass.class);
    }

    private static MethodHandle dropReceiver(final MethodHandle mh, final Class<?> receiverClass) {
        MethodHandle newHandle = MethodHandles.dropArguments(mh, 0, receiverClass);
        // NOTE: this is a workaround for the fact that dropArguments doesn't preserve vararg collector state.
        if(mh.isVarargsCollector() && !newHandle.isVarargsCollector()) {
            final MethodType type = mh.type();
            newHandle = newHandle.asVarargsCollector(type.parameterType(type.parameterCount() - 1));
        }
        return newHandle;
    }
}
