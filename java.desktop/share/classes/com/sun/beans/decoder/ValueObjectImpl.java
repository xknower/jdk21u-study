package com.sun.beans.decoder;

/**
 * This utility class provides {@code static} method
 * to create the object that contains the result of method execution.
 *
 * @since 1.7
 *
 * @author Sergey A. Malenkov
 */
final class ValueObjectImpl implements ValueObject {
    static final ValueObject NULL = new ValueObjectImpl(null);
    static final ValueObject VOID = new ValueObjectImpl();

    /**
     * Returns the object that describes returning value.
     *
     * @param value  the result of method execution
     * @return the object that describes value
     */
    static ValueObject create(Object value) {
        return (value != null)
                ? new ValueObjectImpl(value)
                : NULL;
    }

    private Object value;
    private boolean isVoid;

    /**
     * Creates the object that describes returning void value.
     */
    private ValueObjectImpl() {
        this.isVoid = true;
    }

    /**
     * Creates the object that describes returning non-void value.
     *
     * @param value  the result of method execution
     */
    private ValueObjectImpl(Object value) {
        this.value = value;
    }

    /**
     * Returns the result of method execution.
     *
     * @return the result of method execution
     */
    public Object getValue() {
        return this.value;
    }

    /**
     * Returns {@code void} state of this value object.
     *
     * @return {@code true} if value should be ignored,
     *         {@code false} otherwise
     */
    public boolean isVoid() {
        return this.isVoid;
    }
}
