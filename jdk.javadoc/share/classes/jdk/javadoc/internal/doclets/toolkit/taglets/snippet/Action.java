package jdk.javadoc.internal.doclets.toolkit.taglets.snippet;

/**
 * An action described by markup. Such an action is typically an opaque compound
 * of primitive operations of {@link StyledText}.
 */
public interface Action {

    /**
     * Performs this action.
     */
    void perform();
}
