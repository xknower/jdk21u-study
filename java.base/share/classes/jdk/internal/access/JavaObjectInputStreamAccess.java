package jdk.internal.access;

import java.io.ObjectStreamException;
import java.io.ObjectInputStream;

/**
 * Interface to specify methods for accessing {@code ObjectInputStream}.
 */
@FunctionalInterface
public interface JavaObjectInputStreamAccess {
    void checkArray(ObjectInputStream ois, Class<?> arrayType, int arrayLength)
        throws ObjectStreamException;
}
