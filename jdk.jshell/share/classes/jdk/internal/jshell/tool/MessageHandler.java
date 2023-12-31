package jdk.internal.jshell.tool;


/**
 * User message reporting support
 *
 * @author Robert Field
 */
public interface MessageHandler {

    void fluff(String format, Object... args);

    void fluffmsg(String messageKey, Object... args);

    void hard(String format, Object... args);

    void hardmsg(String messageKey, Object... args);

    void errormsg(String messageKey, Object... args);

    boolean showFluff();
}
