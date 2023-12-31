package com.sun.beans.decoder;

/**
 * This is base class that simplifies access to entities (fields or properties).
 * The {@code name} attribute specifies the name of the accessible entity.
 * The element defines getter if it contains no argument
 * or setter if it contains one argument.
 *
 * @since 1.7
 *
 * @author Sergey A. Malenkov
 */
abstract class AccessorElementHandler extends ElementHandler {
    private String name;
    private ValueObject value;

    /**
     * Parses attributes of the element.
     * The following attributes are supported:
     * <dl>
     * <dt>name
     * <dd>the name of the accessible entity
     * <dt>id
     * <dd>the identifier of the variable that is intended to store the result
     * </dl>
     *
     * @param name   the attribute name
     * @param value  the attribute value
     */
    @Override
    public void addAttribute(String name, String value) {
        if (name.equals("name")) { // NON-NLS: the attribute name
            this.name = value;
        } else {
            super.addAttribute(name, value);
        }
    }

    /**
     * Adds the argument that is used to set the value of this element.
     *
     * @param argument  the value of the element that contained in this one
     */
    @Override
    protected final void addArgument(Object argument) {
        if (this.value != null) {
            throw new IllegalStateException("Could not add argument to evaluated element");
        }
        setValue(this.name, argument);
        this.value = ValueObjectImpl.VOID;
    }

    /**
     * Returns the value of this element.
     *
     * @return the value of this element
     */
    @Override
    protected final ValueObject getValueObject() {
        if (this.value == null) {
            this.value = ValueObjectImpl.create(getValue(this.name));
        }
        return this.value;
    }

    /**
     * Returns the value of the entity with specified {@code name}.
     *
     * @param name  the name of the accessible entity
     * @return the value of the specified entity
     */
    protected abstract Object getValue(String name);

    /**
     * Sets the new value for the entity with specified {@code name}.
     *
     * @param name   the name of the accessible entity
     * @param value  the new value for the specified entity
     */
    protected abstract void setValue(String name, Object value);
}
