#import "ButtonAccessibility.h"

/*
 * Implementation of the accessibility peer for the pushbutton role
 */
@implementation ButtonAccessibility
- (NSAccessibilityRole _Nonnull)accessibilityRole
{
    return NSAccessibilityButtonRole;
}

- (NSString * _Nullable)accessibilityLabel
{
    return [super accessibilityLabel];
}

- (BOOL)accessibilityPerformPress
{
    return [self performAccessibleAction:0];
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
    if ([self accessibilityRole] == NSAccessibilityButtonRole) {
        // Only do it for buttons, radio buttons and checkbox buttons
        // have a meaningful value to return
        return NULL;
    } else {
        return [super accessibilityValue];
    }
}

@end
