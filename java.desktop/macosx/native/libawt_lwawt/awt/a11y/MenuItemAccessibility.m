#import "MenuItemAccessibility.h"

/*
 * This is the protocol for the MenuItem component.
 */
@implementation MenuItemAccessibility
- (NSAccessibilityRole _Nonnull)accessibilityRole
{
    return NSAccessibilityMenuItemRole;
}

- (BOOL)isAccessibilityElement
{
    return YES;
}

- (BOOL)accessibilityPerformPick
{
    return [self performAccessibleAction:0];
}

- (BOOL)accessibilityPerformPress
{
    return [self performAccessibleAction:0];
}

- (NSString * _Nullable)accessibilityLabel
{
    return [super accessibilityLabel];
}

- (id _Nullable)accessibilityValue
{
    return NULL;
}

@end
