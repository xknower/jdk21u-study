#import "IgnoreAccessibility.h"

/*
 * Indicates that component does not participate in accessibility exchange
 */
@implementation IgnoreAccessibility
- (BOOL)isAccessibilityElement
{
    return NO;
}

@end
