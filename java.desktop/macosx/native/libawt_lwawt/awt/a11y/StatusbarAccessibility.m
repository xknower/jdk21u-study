#import "StatusbarAccessibility.h"

/*
 * Implementation of the accessibility peer for the Statusbar role
 */
@implementation StatusbarAccessibility

- (NSAccessibilityRole _Nonnull)accessibilityRole
{
    return NSAccessibilityValueIndicatorRole;
}

@end
