package jdk.vm.ci.code.site;

/**
 * Represents some external data that is referenced by the code.
 */
public abstract class Reference {

    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object obj);
}
