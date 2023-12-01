#import "ScrollAreaAccessibility.h"
#import "ThreadUtilities.h"
#import "JNIUtilities.h"
#import "sun_lwawt_macosx_CAccessibility.h"

/*
 * Implementation of the accessibility peer for the ScrollArea role
 */
@implementation ScrollAreaAccessibility

- (NSArray * _Nullable)accessibilityContentsAttribute
{
    JNIEnv *env = [ThreadUtilities getJNIEnv];
    NSArray *children = [CommonComponentAccessibility childrenOfParent:self withEnv:env withChildrenCode:sun_lwawt_macosx_CAccessibility_JAVA_AX_ALL_CHILDREN allowIgnored:YES];

    if ([children count] <= 0) return nil;
    NSArray *contents = [NSMutableArray arrayWithCapacity:[children count]];

    // The scroll bars are in the children. children less the scroll bars is the contents
    NSEnumerator *enumerator = [children objectEnumerator];
    CommonComponentAccessibility *aElement;
    while ((aElement = (CommonComponentAccessibility *)[enumerator nextObject])) {
        if (![[aElement accessibilityRole] isEqualToString:NSAccessibilityScrollBarRole]) {
            // no scroll bars in contents
            [(NSMutableArray *)contents addObject:aElement];
        }
    }
    return contents;
}

- (id _Nullable)getScrollBarwithOrientation:(enum NSAccessibilityOrientation)orientation
{
    JNIEnv *env = [ThreadUtilities getJNIEnv];

    NSArray *children = [CommonComponentAccessibility childrenOfParent:self withEnv:env withChildrenCode:sun_lwawt_macosx_CAccessibility_JAVA_AX_ALL_CHILDREN allowIgnored:YES];
    if ([children count] <= 0) return nil;

    // The scroll bars are in the children.
    CommonComponentAccessibility *aElement;
    NSEnumerator *enumerator = [children objectEnumerator];
    while ((aElement = (CommonComponentAccessibility *)[enumerator nextObject])) {
        if ([[aElement accessibilityRole] isEqualToString:NSAccessibilityScrollBarRole]) {
            jobject elementAxContext = [aElement axContextWithEnv:env];
            if (orientation == NSAccessibilityOrientationHorizontal) {
                if (isHorizontal(env, elementAxContext, fComponent)) {
                    (*env)->DeleteLocalRef(env, elementAxContext);
                    return aElement;
                }
            } else if (orientation == NSAccessibilityOrientationVertical) {
                if (isVertical(env, elementAxContext, fComponent)) {
                    (*env)->DeleteLocalRef(env, elementAxContext);
                    return aElement;
                }
            } else {
                (*env)->DeleteLocalRef(env, elementAxContext);
            }
        }
    }
    return nil;
}

- (NSAccessibilityRole _Nonnull)accessibilityRole
{
    return NSAccessibilityScrollAreaRole;
}

- (NSArray * _Nullable)accessibilityContents
{
    return [self accessibilityContentsAttribute];
}

- (id _Nullable)accessibilityHorizontalScrollBar
{
    return [self getScrollBarwithOrientation:NSAccessibilityOrientationHorizontal];
}

- (id _Nullable)accessibilityVerticalScrollBar
{
    return [self getScrollBarwithOrientation:NSAccessibilityOrientationVertical];
}
@end
