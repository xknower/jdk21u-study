#include "jni.h"
#import "ListRowAccessibility.h"
#import "JavaAccessibilityAction.h"
#import "JavaAccessibilityUtilities.h"
#import "ListAccessibility.h"
#import "ThreadUtilities.h"

@implementation ListRowAccessibility

// NSAccessibilityElement protocol methods

- (NSAccessibilityRole)accessibilityRole
{
    return NSAccessibilityRowRole;
}

- (NSInteger)accessibilityIndex
{
    return [[self accessibilityParent] accessibilityIndexOfChild:self];
}

- (id)accessibilityParent
{
    return [super accessibilityParent];
}

- (NSRect)accessibilityFrame
{
    return [super accessibilityFrame];
}

@end
