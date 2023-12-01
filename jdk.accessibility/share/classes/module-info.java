/**
 * Defines JDK utility classes used by implementors of Assistive Technologies.
 *
 * @moduleGraph
 * @since 9
 */
module jdk.accessibility {
    requires transitive java.desktop;

    exports com.sun.java.accessibility.util;
}

