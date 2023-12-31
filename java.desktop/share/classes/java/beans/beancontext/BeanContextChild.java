package java.beans.beancontext;

import java.beans.PropertyChangeListener;
import java.beans.VetoableChangeListener;
import java.beans.PropertyVetoException;

import java.beans.beancontext.BeanContext;

/**
 * <p>
 * JavaBeans wishing to be nested within, and obtain a reference to their
 * execution environment, or context, as defined by the BeanContext
 * sub-interface shall implement this interface.
 * </p>
 * <p>
 * Conformant BeanContexts shall as a side effect of adding a BeanContextChild
 * object shall pass a reference to itself via the setBeanContext() method of
 * this interface.
 * </p>
 * <p>
 * Note that a BeanContextChild may refuse a change in state by throwing
 * PropertyVetoedException in response.
 * </p>
 * <p>
 * In order for persistence mechanisms to function properly on BeanContextChild
 * instances across a broad variety of scenarios, implementing classes of this
 * interface are required to define as transient, any or all fields, or
 * instance variables, that may contain, or represent, references to the
 * nesting BeanContext instance or other resources obtained
 * from the BeanContext via any unspecified mechanisms.
 * </p>
 *
 * @author      Laurence P. G. Cable
 * @since       1.2
 *
 * @see java.beans.beancontext.BeanContext
 * @see java.beans.PropertyChangeEvent
 * @see java.beans.PropertyChangeListener
 * @see java.beans.PropertyVetoException
 * @see java.beans.VetoableChangeListener
 */

public interface BeanContextChild {

    /**
     * <p>
     * Objects that implement this interface,
     * shall fire a java.beans.PropertyChangeEvent, with parameters:
     *
     * propertyName "beanContext", oldValue (the previous nesting
     * {@code BeanContext} instance, or {@code null}),
     * newValue (the current nesting
     * {@code BeanContext} instance, or {@code null}).
     * <p>
     * A change in the value of the nesting BeanContext property of this
     * BeanContextChild may be vetoed by throwing the appropriate exception.
     * </p>
     * @param bc The {@code BeanContext} with which
     * to associate this {@code BeanContextChild}.
     * @throws PropertyVetoException if the
     * addition of the specified {@code BeanContext} is refused.
     */
    void setBeanContext(BeanContext bc) throws PropertyVetoException;

    /**
     * Gets the {@code BeanContext} associated
     * with this {@code BeanContextChild}.
     * @return the {@code BeanContext} associated
     * with this {@code BeanContextChild}.
     */
    BeanContext getBeanContext();

    /**
     * Adds a {@code PropertyChangeListener}
     * to this {@code BeanContextChild}
     * in order to receive a {@code PropertyChangeEvent}
     * whenever the specified property has changed.
     * @param name the name of the property to listen on
     * @param pcl the {@code PropertyChangeListener} to add
     */
    void addPropertyChangeListener(String name, PropertyChangeListener pcl);

    /**
     * Removes a {@code PropertyChangeListener} from this
     * {@code BeanContextChild}  so that it no longer
     * receives {@code PropertyChangeEvents} when the
     * specified property is changed.
     *
     * @param name the name of the property that was listened on
     * @param pcl the {@code PropertyChangeListener} to remove
     */
    void removePropertyChangeListener(String name, PropertyChangeListener pcl);

    /**
     * Adds a {@code VetoableChangeListener} to
     * this {@code BeanContextChild}
     * to receive events whenever the specified property changes.
     * @param name the name of the property to listen on
     * @param vcl the {@code VetoableChangeListener} to add
     */
    void addVetoableChangeListener(String name, VetoableChangeListener vcl);

    /**
     * Removes a {@code VetoableChangeListener} from this
     * {@code BeanContextChild} so that it no longer receives
     * events when the specified property changes.
     * @param name the name of the property that was listened on.
     * @param vcl the {@code VetoableChangeListener} to remove.
     */
    void removeVetoableChangeListener(String name, VetoableChangeListener vcl);

}
