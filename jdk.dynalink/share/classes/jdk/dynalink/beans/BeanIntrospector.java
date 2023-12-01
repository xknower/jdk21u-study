package jdk.dynalink.beans;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.lang.reflect.RecordComponent;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class BeanIntrospector extends FacetIntrospector {
    private final Class<?> clazz;

    BeanIntrospector(final Class<?> clazz) {
        super(clazz, true);
        this.clazz = clazz;
    }

    @Override
    Map<String, MethodHandle> getInnerClassGetters() {
        return Map.of(); // NOTE: non-static inner classes are also on StaticClassIntrospector.
    }

    @Override Collection<Method> getRecordComponentGetters() {
        if (clazz.isRecord()) {
            try {
                // Need to use doPrivileged as getRecordComponents is rather strict.
                @SuppressWarnings("removal")
                final RecordComponent[] rcs = AccessController.doPrivileged(
                    (PrivilegedAction<RecordComponent[]>) clazz::getRecordComponents);
                return Arrays.stream(rcs)
                    .map(RecordComponent::getAccessor)
                    .map(membersLookup::getAccessibleMethod)
                    .filter(Objects::nonNull) // no accessible counterpart
                    .toList();
            } catch (SecurityException e) {
                // We couldn't execute getRecordComponents.
                return List.of();
            }
        } else {
            return List.of();
        }
    }

    @Override
    MethodHandle editMethodHandle(final MethodHandle mh) {
        return mh;
    }
}
