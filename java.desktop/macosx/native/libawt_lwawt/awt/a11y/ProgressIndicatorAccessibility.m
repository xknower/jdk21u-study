#import "ProgressIndicatorAccessibility.h"

/*
 * Implementation of the accessibility peer for the NSProgressIndicator role.
 * Main usage is JProgressBar
 */
@implementation ProgressIndicatorAccessibility

- (NSAccessibilityRole _Nonnull)accessibilityRole
{
    return NSAccessibilityProgressIndicatorRole;
}

- (NSString * _Nullable)accessibilityValue
{
    return [super accessibilityValue];
}

@end
