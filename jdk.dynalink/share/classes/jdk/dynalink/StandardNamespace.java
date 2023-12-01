package jdk.dynalink;

/**
 * An enumeration of standard namespaces defined by Dynalink.
 */
public enum StandardNamespace implements Namespace {
    /**
     * Standard namespace for properties of an object.
     */
    PROPERTY,
    /**
     * Standard namespace for elements of a collection object.
     */
    ELEMENT,
    /**
     * Standard namespace for methods of an object. The method objects retrieved
     * through a {@link StandardOperation#GET} on this namespace can be (and where
     * object semantics allows they should be) unbound, that is: not bound to the
     * object they were retrieved through. When they are used with
     * {@link StandardOperation#CALL} an explicit "this" receiver argument is always
     * passed to them. Of course bound methods can be returned if the object semantics
     * requires them and such methods are free to ignore the receiver passed in the
     * {@code CALL} operation or even raise an error when it is different from the one
     * the method is bound to, or exhibit any other behavior their semantics requires
     * in such case.
     */
    METHOD;

    /**
     * If the passed in operation is a {@link NamespaceOperation}, or a
     * {@link NamedOperation} wrapping a {@link NamespaceOperation}, then it
     * returns the first (if any) {@link StandardNamespace} in its namespace
     * list. If the passed operation is not a namespace operation (optionally
     * wrapped in a named operation), or if it doesn't have any standard
     * namespaces in it, returns {@code null}.
     * @param op the operation
     * @return the first standard namespace in the operation's namespace list
     */
    public static StandardNamespace findFirst(final Operation op) {
        for(final Namespace ns: NamespaceOperation.getNamespaces(NamedOperation.getBaseOperation(op))) {
            if (ns instanceof StandardNamespace) {
                return (StandardNamespace)ns;
            }
        }
        return null;
    }
}
