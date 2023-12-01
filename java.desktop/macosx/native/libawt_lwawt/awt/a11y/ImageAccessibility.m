#import "ImageAccessibility.h"

/*
 * Implementation of the accessibility peer for the icon role
 */
@implementation ImageAccessibility
- (NSAccessibilityRole _Nonnull)accessibilityRole
{
    return NSAccessibilityImageRole;
}

- (NSString * _Nullable)accessibilityLabel
{
    return [super accessibilityLabel];
}

- (NSRect)accessibilityFrame
{
    return [super accessibilityFrame];
}

- (id)accessibilityParent
{
    return [super accessibilityParent];
}

- (id _Nullable)accessibilityValue
{
    return NULL;
}

@end
