#import "SpinboxAccessibility.h"

#define INCREMENT 0
#define DECREMENT 1

/*
 * Implementation of the accessibility peer for the spinner role
 */
@implementation SpinboxAccessibility
- (NSAccessibilityRole _Nonnull)accessibilityRole
{
    return NSAccessibilityIncrementorRole;
}

- (NSString * _Nullable)accessibilityLabel
{
    return [super accessibilityLabel];
}

- (id _Nullable)accessibilityValue
{
    return [super accessibilityValue];
}

- (BOOL)accessibilityPerformIncrement
{
    return [self performAccessibleAction:INCREMENT];
}


- (BOOL)accessibilityPerformDecrement
{
    return [self performAccessibleAction:DECREMENT];
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
