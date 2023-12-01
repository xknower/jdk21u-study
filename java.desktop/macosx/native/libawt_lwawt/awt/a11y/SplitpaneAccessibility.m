#import "SplitpaneAccessibility.h"

/*
 * Implementation of the accessibility peer for the Splitpane role
 */
@implementation SplitpaneAccessibility

- (NSAccessibilityRole _Nonnull)accessibilityRole
{
    return NSAccessibilitySplitGroupRole;
}

@end
