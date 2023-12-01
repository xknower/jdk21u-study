#import "GroupAccessibility.h"
#import "JNIUtilities.h"
#import "ThreadUtilities.h"
#import "sun_lwawt_macosx_CAccessibility.h"
/*
 * This is the protocol for the components that contain children.
 * Basic logic of accessibilityChildren might be overridden in the specific implementing
 * classes reflecting the logic of the class.
 */
@implementation GroupAccessibility
- (NSAccessibilityRole _Nonnull)accessibilityRole
{
    return NSAccessibilityGroupRole;
}

/*
 * Return all non-ignored children.
 */
- (NSArray *)accessibilityChildren {
    JNIEnv *env = [ThreadUtilities getJNIEnv];

    NSArray *children = [CommonComponentAccessibility childrenOfParent:self
                                                             withEnv:env
                                                    withChildrenCode:sun_lwawt_macosx_CAccessibility_JAVA_AX_ALL_CHILDREN
                                                        allowIgnored:NO];

    if ([children count] == 0) {
        return nil;
    } else {
        return children;
    }
}

- (NSRect)accessibilityFrame
{
    return [super accessibilityFrame];
}

- (id)accessibilityParent
{
    return [super accessibilityParent];
}

@end
