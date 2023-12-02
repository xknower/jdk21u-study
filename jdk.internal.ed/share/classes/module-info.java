/**
 * Internal editor support for JDK tools.  Includes the Service Provider
 * Interface to built-in editors.
 *
 * @since 9
 */
module jdk.internal.ed {

    exports jdk.internal.editor.spi to
        jdk.editpad,
        jdk.jshell;
    exports jdk.internal.editor.external to
        jdk.jshell;
}
