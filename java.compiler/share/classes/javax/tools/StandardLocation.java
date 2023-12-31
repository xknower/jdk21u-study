package javax.tools;

import javax.tools.JavaFileManager.Location;

import java.util.concurrent.*;

/**
 * Standard locations of file objects.
 *
 * @since 1.6
 */
public enum StandardLocation implements Location {

    /**
     * Location of new class files.
     */
    CLASS_OUTPUT,

    /**
     * Location of new source files.
     */
    SOURCE_OUTPUT,

    /**
     * Location to search for user class files.
     */
    CLASS_PATH,

    /**
     * Location to search for existing source files.
     */
    SOURCE_PATH,

    /**
     * Location to search for annotation processors.
     */
    ANNOTATION_PROCESSOR_PATH,

    /**
     * Location to search for modules containing annotation processors.
     * @since 9
     */
    ANNOTATION_PROCESSOR_MODULE_PATH,

    /**
     * Location to search for platform classes.  Sometimes called
     * the boot class path.
     */
    PLATFORM_CLASS_PATH,

    /**
     * Location of new native header files.
     * @since 1.8
     */
    NATIVE_HEADER_OUTPUT,

    /**
     * Location to search for the source code of modules.
     * @since 9
     */
    MODULE_SOURCE_PATH,

    /**
     * Location to search for upgradeable system modules.
     * @since 9
     */
    UPGRADE_MODULE_PATH,

    /**
     * Location to search for system modules.
     * @since 9
     */
    SYSTEM_MODULES,

    /**
     * Location to search for precompiled user modules.
     * @since 9
     */
    MODULE_PATH,

    /**
     * Location to search for module patches.
     * @since 9
     */
    PATCH_MODULE_PATH;

    /**
     * Returns a location object with the given name.  The following
     * property must hold: {@code locationFor(x) ==
     * locationFor(y)} if and only if {@code x.equals(y)}.
     * The returned location will be an output location if and only if
     * name ends with {@code "_OUTPUT"}. It will be considered to
     * be a module-oriented location if the name contains the word
     * {@code "MODULE"}.
     *
     * @param name a name
     * @return a location
     *
     * @revised 9
     */
    public static Location locationFor(final String name) {
        if (locations.isEmpty()) {
            // can't use valueOf which throws IllegalArgumentException
            for (Location location : values())
                locations.putIfAbsent(location.getName(), location);
        }
        name.getClass(); /* null-check */
        locations.putIfAbsent(name, new Location() {
                @Override
                public String getName() { return name; }
                @Override
                public boolean isOutputLocation() { return name.endsWith("_OUTPUT"); }
            });
        return locations.get(name);
    }
    //where
        private static final ConcurrentMap<String,Location> locations
            = new ConcurrentHashMap<>();

    @Override
    public String getName() { return name(); }

    @Override
    public boolean isOutputLocation() {
        switch (this) {
            case CLASS_OUTPUT:
            case SOURCE_OUTPUT:
            case NATIVE_HEADER_OUTPUT:
                return true;
            default:
                return false;
        }
    }

    /**
     * {@inheritDoc}
     * @since 9
     */
    @Override
    public boolean isModuleOrientedLocation() {
        switch (this) {
            case MODULE_SOURCE_PATH:
            case ANNOTATION_PROCESSOR_MODULE_PATH:
            case UPGRADE_MODULE_PATH:
            case SYSTEM_MODULES:
            case MODULE_PATH:
            case PATCH_MODULE_PATH:
                return true;
            default:
                return false;
        }
    }
}
