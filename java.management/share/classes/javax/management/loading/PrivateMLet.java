package javax.management.loading;

import java.net.URL;
import java.net.URLStreamHandlerFactory;

/**
 * An MLet that is not added to the {@link ClassLoaderRepository}.
 * This class acts exactly like its parent class, {@link MLet}, with
 * one exception.  When a PrivateMLet is registered in an MBean
 * server, it is not added to that MBean server's {@link
 * ClassLoaderRepository}.  This is true because this class implements
 * the interface {@link PrivateClassLoader}.
 *
 * @deprecated This API is part of Management Applets (m-lets), which is a legacy feature that allows loading
 * of remote MBeans. This feature is not usable without a Security Manager, which is deprecated and subject to
 * removal in a future release. Consequently, this API is also deprecated and subject to removal. There is no replacement.
 *
 * @since 1.5
 */
@Deprecated(since="20", forRemoval=true)
@SuppressWarnings({"serial", "removal"}) // Externalizable class w/o no-arg c'tor
public class PrivateMLet extends MLet implements PrivateClassLoader {
    private static final long serialVersionUID = 2503458973393711979L;

    /**
      * Constructs a new PrivateMLet for the specified URLs using the
      * default delegation parent ClassLoader.  The URLs will be
      * searched in the order specified for classes and resources
      * after first searching in the parent class loader.
      *
      * @param  urls  The URLs from which to load classes and resources.
      * @param  delegateToCLR  True if, when a class is not found in
      * either the parent ClassLoader or the URLs, the MLet should delegate
      * to its containing MBeanServer's {@link ClassLoaderRepository}.
      *
      */
    public PrivateMLet(URL[] urls, boolean delegateToCLR) {
        super(urls, delegateToCLR);
    }

    /**
      * Constructs a new PrivateMLet for the given URLs. The URLs will
      * be searched in the order specified for classes and resources
      * after first searching in the specified parent class loader.
      * The parent argument will be used as the parent class loader
      * for delegation.
      *
      * @param  urls  The URLs from which to load classes and resources.
      * @param  parent The parent class loader for delegation.
      * @param  delegateToCLR  True if, when a class is not found in
      * either the parent ClassLoader or the URLs, the MLet should delegate
      * to its containing MBeanServer's {@link ClassLoaderRepository}.
      *
      */
    public PrivateMLet(URL[] urls, ClassLoader parent, boolean delegateToCLR) {
        super(urls, parent, delegateToCLR);
    }

    /**
      * Constructs a new PrivateMLet for the specified URLs, parent
      * class loader, and URLStreamHandlerFactory. The parent argument
      * will be used as the parent class loader for delegation. The
      * factory argument will be used as the stream handler factory to
      * obtain protocol handlers when creating new URLs.
      *
      * @param  urls  The URLs from which to load classes and resources.
      * @param  parent The parent class loader for delegation.
      * @param  factory  The URLStreamHandlerFactory to use when creating URLs.
      * @param  delegateToCLR  True if, when a class is not found in
      * either the parent ClassLoader or the URLs, the MLet should delegate
      * to its containing MBeanServer's {@link ClassLoaderRepository}.
      *
      */
    public PrivateMLet(URL[] urls,
                       ClassLoader parent,
                       URLStreamHandlerFactory factory,
                       boolean delegateToCLR) {
        super(urls, parent, factory, delegateToCLR);
    }
}
