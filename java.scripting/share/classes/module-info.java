/**
 * Defines the Scripting API.
 *
 * <p> The JDK implementation of this module includes a language-independent
 * command-line script shell, <em>{@index jrunscript jrunscript tool}</em>,
 * that supports executing JavaScript and other languages if its corresponding
 * script engine is installed.
 *
 * @toolGuide jrunscript
 *
 * @uses javax.script.ScriptEngineFactory
 *
 * @moduleGraph
 * @since 9
 */
module java.scripting {
    exports javax.script;

    uses javax.script.ScriptEngineFactory;
}
