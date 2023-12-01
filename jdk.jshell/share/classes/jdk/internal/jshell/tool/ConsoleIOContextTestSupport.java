package jdk.internal.jshell.tool;

/**A support class to the ConsoleIOContext, containing callbacks called
 * on important points in ConsoleIOContext.
 */
public abstract class ConsoleIOContextTestSupport {

    public static ConsoleIOContextTestSupport IMPL;

    public static void willComputeCompletion() {
        if (IMPL != null)
            IMPL.willComputeCompletionCallback();
    }

    protected abstract void willComputeCompletionCallback();

}
