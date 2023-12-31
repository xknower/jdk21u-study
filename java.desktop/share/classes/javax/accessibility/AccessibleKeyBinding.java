package javax.accessibility;

/**
 * The {@code AccessibleKeyBinding} interface should be supported by any object
 * that has a keyboard bindings such as a keyboard mnemonic and/or keyboard
 * shortcut which can be used to select the object. This interface provides the
 * standard mechanism for an assistive technology to determine the key bindings
 * which exist for this object. Any object that has such key bindings should
 * support this interface.
 *
 * @author Lynn Monsanto
 * @see Accessible
 * @see Accessible#getAccessibleContext
 * @see AccessibleContext
 * @since 1.4
 */
public interface AccessibleKeyBinding {

    /**
     * Returns the number of key bindings for this object.
     *
     * @return the zero-based number of key bindings for this object
     */
    public int getAccessibleKeyBindingCount();

    /**
     * Returns a key binding for this object. The value returned is an
     * {@code java.lang.Object} which must be cast to appropriate type depending
     * on the underlying implementation of the key.
     *
     * @param  i zero-based index of the key bindings
     * @return a {@code javax.lang.Object} which specifies the key binding
     * @see #getAccessibleKeyBindingCount
     */
    public Object getAccessibleKeyBinding(int i);
}
