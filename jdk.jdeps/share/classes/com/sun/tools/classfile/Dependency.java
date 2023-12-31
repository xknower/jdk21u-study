package com.sun.tools.classfile;


/**
 * A directed relationship between two {@link Dependency.Location Location}s.
 * Subtypes of {@code Dependency} may provide additional detail about the dependency.
 *
 * @see Dependency.Finder
 * @see Dependency.Filter
 * @see Dependencies
 */
public interface Dependency {
    /**
     * A filter used to select dependencies of interest, and to discard others.
     */
    public interface Filter {
        /**
         * Return true if the dependency is of interest.
         * @param dependency the dependency to be considered
         * @return true if and only if the dependency is of interest.
         */
        boolean accepts(Dependency dependency);
    }

    /**
     * An interface for finding the immediate dependencies of a given class file.
     */
    public interface Finder {
        /**
         * Find the immediate dependencies of a given class file.
         * @param classfile the class file to be examined
         * @return the dependencies located in the given class file.
         */
        public Iterable<? extends Dependency> findDependencies(ClassFile classfile);
    }


    /**
     * A location somewhere within a class. Subtypes of {@code Location}
     * may be used to provide additional detail about the location.
     */
    public interface Location {
        /**
         * Get the name of the class containing the location.
         * This name will be used to locate the class file for transitive
         * dependency analysis.
         * @return the name of the class containing the location.
         */
        String getName();

        /**
         * Get the fully-qualified name of the class containing the location.
         * @return the fully-qualified name of the class containing the location.
         */
        String getClassName();

        /**
         * Get the package name of the class containing the location.
         * @return the package name of the class containing the location.
         */
        String getPackageName();
    }


    /**
     * Get the location that has the dependency.
     * @return the location that has the dependency.
     */
    Location getOrigin();

    /**
     * Get the location that is being depended upon.
     * @return the location that is being depended upon.
     */
    Location getTarget();
}

