#import "CommonComponentAccessibility.h"

/**
 * Some native a11y elements do not have direct peers in Java, like list rows and cells.
 * However, these elements are required by Cocoa in order for a11y to work properly.
 * The ComponentWrapperAccessibility interface provides a concept of wrapping an element
 * originated from java (like a list item, or a table element) with a component
 * which has a11y role required Cocoa (like NSAccessibilityRowRole, or NSAccessibilityCellRole)
 * but does not have peer in java.
 *
 * The wrapping component becomes a parent of the wrapped child in the a11y hierarchy.
 * The child component is created automatically on demand with the same set of arguments,
 * except that it has a11y role of its java peer.
 *
 * It is important that only the wrapping component is linked with sun.lwawt.macosx.CAccessible
 * and thus its lifecycle depends on the java accessible. So when the same java accessible is passed
 * to create a native peer, the wrapping component is retrieved in case it has already been
 * created (see [CommonComponentAccessibility createWithParent]). When the wrapping component is
 * deallocated (as triggered from the java side) it releases the wrapped child.
 */
@interface ComponentWrapperAccessibility : CommonComponentAccessibility

@property (nonatomic, retain) CommonComponentAccessibility *wrappedChild;

@end
