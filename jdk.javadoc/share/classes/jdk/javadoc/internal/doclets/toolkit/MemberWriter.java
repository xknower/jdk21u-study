package jdk.javadoc.internal.doclets.toolkit;

/**
 * Common behavior for writing members of a type.
 */
public interface MemberWriter {

    /**
     * {@return a list to add member items to}
     *
     * @see #getMemberListItem(Content)
     */
    Content getMemberList();

    /**
     * {@return a member item}
     *
     * @param member the member to represent as an item
     * @see #getMemberList()
     */
    Content getMemberListItem(Content member);
}
