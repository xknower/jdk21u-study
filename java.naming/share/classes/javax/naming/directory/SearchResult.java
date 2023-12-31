package javax.naming.directory;

import javax.naming.Binding;

/**
  * This class represents an item in the NamingEnumeration returned as a
  * result of the DirContext.search() methods.
  *<p>
  * A SearchResult instance is not synchronized against concurrent
  * multithreaded access. Multiple threads trying to access and modify
  * a single SearchResult instance should lock the object.
  *
  * @author Rosanna Lee
  * @author Scott Seligman
  *
  * @see DirContext#search
  * @since 1.3
  */

public class SearchResult extends Binding {
    /**
     * Contains the attributes returned with the object.
     * @serial
     */
    private Attributes attrs;

    /**
      * Constructs a search result using the result's name, its bound object, and
      * its attributes.
      *<p>
      * {@code getClassName()} will return the class name of {@code obj}
      * (or null if {@code obj} is null) unless the class name has been
      * explicitly set using {@code setClassName()}.
      *
      * @param name The non-null name of the search item. It is relative
      *             to the <em>target context</em> of the search (which is
      * named by the first parameter of the <code>search()</code> method)
      *
      * @param obj  The object bound to name. Can be null.
      * @param attrs The attributes that were requested to be returned with
      * this search item. Cannot be null.
      * @see javax.naming.NameClassPair#setClassName
      * @see javax.naming.NameClassPair#getClassName
      */
    public SearchResult(String name, Object obj, Attributes attrs) {
        super(name, obj);
        this.attrs = attrs;
    }

    /**
      * Constructs a search result using the result's name, its bound object, and
      * its attributes, and whether the name is relative.
      *<p>
      * {@code getClassName()} will return the class name of {@code obj}
      * (or null if {@code obj} is null) unless the class name has been
      * explicitly set using {@code setClassName()}
      *
      * @param name The non-null name of the search item.
      * @param obj  The object bound to name. Can be null.
      * @param attrs The attributes that were requested to be returned with
      * this search item. Cannot be null.
      * @param isRelative true if <code>name</code> is relative
      *         to the target context of the search (which is named by
      *         the first parameter of the <code>search()</code> method);
      *         false if <code>name</code> is a URL string.
      * @see javax.naming.NameClassPair#setClassName
      * @see javax.naming.NameClassPair#getClassName
      */
    public SearchResult(String name, Object obj, Attributes attrs,
        boolean isRelative) {
        super(name, obj, isRelative);
        this.attrs = attrs;
    }

    /**
      * Constructs a search result using the result's name, its class name,
      * its bound object, and its attributes.
      *
      * @param name The non-null name of the search item. It is relative
      *             to the <em>target context</em> of the search (which is
      * named by the first parameter of the <code>search()</code> method)
      *
      * @param  className       The possibly null class name of the object
      *         bound to {@code name}. If null, the class name of {@code obj} is
      *         returned by {@code getClassName()}. If {@code obj} is also null,
      *         {@code getClassName()} will return null.
      * @param obj  The object bound to name. Can be null.
      * @param attrs The attributes that were requested to be returned with
      * this search item. Cannot be null.
      * @see javax.naming.NameClassPair#setClassName
      * @see javax.naming.NameClassPair#getClassName
      */
    public SearchResult(String name, String className,
        Object obj, Attributes attrs) {
        super(name, className, obj);
        this.attrs = attrs;
    }

    /**
      * Constructs a search result using the result's name, its class name,
      * its bound object, its attributes, and whether the name is relative.
      *
      * @param name The non-null name of the search item.
      * @param  className       The possibly null class name of the object
      *         bound to {@code name}. If null, the class name of {@code obj} is
      *         returned by {@code getClassName()}. If {@code obj} is also null,
      *         {@code getClassName()} will return null.
      * @param obj  The object bound to name. Can be null.
      * @param attrs The attributes that were requested to be returned with
      * this search item. Cannot be null.
      * @param isRelative true if <code>name</code> is relative
      *         to the target context of the search (which is named by
      *         the first parameter of the <code>search()</code> method);
      *         false if <code>name</code> is a URL string.
      * @see javax.naming.NameClassPair#setClassName
      * @see javax.naming.NameClassPair#getClassName
      */
    public SearchResult(String name, String className, Object obj,
        Attributes attrs, boolean isRelative) {
        super(name, className, obj, isRelative);
        this.attrs = attrs;
    }

    /**
     * Retrieves the attributes in this search result.
     *
     * @return The non-null attributes in this search result. Can be empty.
     * @see #setAttributes
     */
    public Attributes getAttributes() {
        return attrs;
    }


    /**
     * Sets the attributes of this search result to <code>attrs</code>.
     * @param attrs The non-null attributes to use. Can be empty.
     * @see #getAttributes
     */
    public void setAttributes(Attributes attrs) {
        this.attrs = attrs;
        // ??? check for null?
    }


    /**
      * Generates the string representation of this SearchResult.
      * The string representation consists of the string representation
      * of the binding and the string representation of
      * this search result's attributes, separated by ':'.
      * The contents of this string is useful
      * for debugging and is not meant to be interpreted programmatically.
      *
      * @return The string representation of this SearchResult. Cannot be null.
      */
    public String toString() {
        return super.toString() + ":" + getAttributes();
    }

    /**
     * Use serialVersionUID from JNDI 1.1.1 for interoperability
     */
    private static final long serialVersionUID = -9158063327699723172L;
}
