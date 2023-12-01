package jdk.dynalink;

/**
 * An object that describes a dynamic operation. Dynalink defines a set of
 * standard operations with the {@link StandardOperation} class, as well as a
 * way to express the target {@link Namespace namespace(s)} of an operation
 * on an object using {@link NamespaceOperation} and finally a way to attach
 * a fixed target name to an operation using {@link NamedOperation}.
 * When presenting examples in this documentation, we will refer to standard
 * operations using their name (e.g. {@code GET}), to namespace operations
 * by separating their base operation with a colon from their namespace
 * (e.g. {@code GET:PROPERTY}), or in case of multiple namespaces we will
 * further separate those with the vertical line character (e.g.
 * {@code GET:PROPERTY|ELEMENT}), and finally we will refer to named operations
 * by separating the base operation and the name with the colon character (e.g.
 * {@code GET:PROPERTY|ELEMENT:color}).
 */
public interface Operation {
    /**
     * Returns a {@link NamespaceOperation} using this operation as its base.
     * @param namespace the namespace that is the target of the namespace operation.
     * @return a {@link NamespaceOperation} with this operation as its base and the specified
     * namespace as its target.
     * @throws IllegalArgumentException if this operation is already a namespace operation or a named operation.
     * @throws NullPointerException if {@code namespace} is null.
     */
    default NamespaceOperation withNamespace(final Namespace namespace) {
        return withNamespaces(namespace);
    }

    /**
     * Returns a {@link NamespaceOperation} using this operation as its base.
     * @param namespaces the namespaces that are the target of the namespace operation.
     * @return a {@link NamespaceOperation} with this operation as its base and the specified
     * namespaces as its targets.
     * @throws IllegalArgumentException if this operation is already a namespace operation or a named operation.
     * @throws NullPointerException if {@code namespace} or any of its elements is null.
     */
    default NamespaceOperation withNamespaces(final Namespace... namespaces) {
        return new NamespaceOperation(this, namespaces);
    }

    /**
     * Returns a {@link NamedOperation} using this operation as its base.
     * @param name the name that is the target of the named operation.
     * @return a {@link NamedOperation} with this operation as its base and the specified name.
     * @throws IllegalArgumentException if this operation is already a named operation.
     * @throws NullPointerException if {@code name} is null.
     */
    default NamedOperation named(final Object name) {
        return new NamedOperation(this, name);
    }
}
