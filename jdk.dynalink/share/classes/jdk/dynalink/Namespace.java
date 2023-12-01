package jdk.dynalink;

/**
 * An object that describes a namespace that is the target of a dynamic operation
 * on an object. Every object can have one or more namespaces. Dynalink defines a
 * set of standard namespaces with the {@link StandardNamespace} enum. Operations
 * that need to specify a namespace they operate on can be expressed using
 * {@link NamespaceOperation}.
 */
public interface Namespace {
}
