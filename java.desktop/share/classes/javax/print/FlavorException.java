package javax.print;

/**
 * Interface {@code FlavorException} is a mixin interface which a subclass of
 * {@link PrintException PrintException} can implement to report an error
 * condition involving a doc flavor or flavors (class {@link DocFlavor}). The
 * Print Service API does not define any print exception classes that implement
 * interface {@code FlavorException}, that being left to the Print Service
 * implementor's discretion.
 */
public interface FlavorException {

    /**
     * Returns the unsupported flavors.
     *
     * @return the unsupported doc flavors
     */
    public DocFlavor[] getUnsupportedFlavors();
}
