#import "ToolbarAccessibility.h"

/*
 * Implementation of the accessibility peer for the Toolbar role
 */
@implementation ToolbarAccessibility

- (NSAccessibilityRole _Nonnull)accessibilityRole
{
    return NSAccessibilityToolbarRole;
}
@end
