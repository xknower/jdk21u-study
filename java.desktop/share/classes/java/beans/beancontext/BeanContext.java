package java.beans.beancontext;

import java.beans.DesignMode;
import java.beans.Visibility;

import java.io.InputStream;
import java.io.IOException;

import java.net.URL;

import java.util.Collection;
import java.util.Locale;

/**
 * <p>
 * The BeanContext acts a logical hierarchical container for JavaBeans.
 * </p>
 *
 * @author Laurence P. G. Cable
 * @since 1.2
 *
 * @see java.beans.Beans
 * @see java.beans.beancontext.BeanContextChild
 * @see java.beans.beancontext.BeanContextMembershipListener
 * @see java.beans.PropertyChangeEvent
 * @see java.beans.DesignMode
 * @see java.beans.Visibility
 * @see java.util.Collection
 */

@SuppressWarnings("rawtypes")
public interface BeanContext extends BeanContextChild, Collection, DesignMode, Visibility {

    /**
     * Instantiate the javaBean named as a
     * child of this {@code BeanContext}.
     * The implementation of the JavaBean is
     * derived from the value of the beanName parameter,
     * and is defined by the
     * {@code java.beans.Beans.instantiate()} method.
     *
     * @return a javaBean named as a child of this
     * {@code BeanContext}
     * @param beanName The name of the JavaBean to instantiate
     * as a child of this {@code BeanContext}
     * @throws IOException if an IO problem occurs
     * @throws ClassNotFoundException if the class identified
     * by the beanName parameter is not found
     */
    Object instantiateChild(String beanName) throws IOException, ClassNotFoundException;

    /**
     * Analogous to {@code java.lang.ClassLoader.getResourceAsStream()},
     * this method allows a {@code BeanContext} implementation
     * to interpose behavior between the child {@code Component}
     * and underlying {@code ClassLoader}.
     *
     * @param name the resource name
     * @param bcc the specified child
     * @return an {@code InputStream} for reading the resource,
     * or {@code null} if the resource could not
     * be found.
     * @throws IllegalArgumentException if
     * the resource is not valid
     */
    InputStream getResourceAsStream(String name, BeanContextChild bcc) throws IllegalArgumentException;

    /**
     * Analogous to {@code java.lang.ClassLoader.getResource()}, this
     * method allows a {@code BeanContext} implementation to interpose
     * behavior between the child {@code Component}
     * and underlying {@code ClassLoader}.
     *
     * @param name the resource name
     * @param bcc the specified child
     * @return a {@code URL} for the named
     * resource for the specified child
     * @throws IllegalArgumentException
     * if the resource is not valid
     */
    URL getResource(String name, BeanContextChild bcc) throws IllegalArgumentException;

     /**
      * Adds the specified {@code BeanContextMembershipListener}
      * to receive {@code BeanContextMembershipEvents} from
      * this {@code BeanContext} whenever it adds
      * or removes a child {@code Component}(s).
      *
      * @param bcml the BeanContextMembershipListener to be added
      */
    void addBeanContextMembershipListener(BeanContextMembershipListener bcml);

     /**
      * Removes the specified {@code BeanContextMembershipListener}
      * so that it no longer receives {@code BeanContextMembershipEvent}s
      * when the child {@code Component}(s) are added or removed.
      *
      * @param bcml the {@code BeanContextMembershipListener}
      * to be removed
      */
    void removeBeanContextMembershipListener(BeanContextMembershipListener bcml);

    /**
     * This global lock is used by both {@code BeanContext}
     * and {@code BeanContextServices} implementors
     * to serialize changes in a {@code BeanContext}
     * hierarchy and any service requests etc.
     */
    public static final Object globalHierarchyLock = new Object();
}
