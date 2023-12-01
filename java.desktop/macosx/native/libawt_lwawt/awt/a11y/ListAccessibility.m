#import "ListAccessibility.h"
#import "JavaAccessibilityUtilities.h"
#import "ThreadUtilities.h"

@implementation ListAccessibility

// NSAccessibilityElement protocol methods

- (nullable NSArray<id<NSAccessibilityRow>> *)accessibilityRows
{
    return [self accessibilityChildren];
}

- (nullable NSArray<id<NSAccessibilityRow>> *)accessibilitySelectedRows
{
    return [self accessibilitySelectedChildren];
}

- (NSString *)accessibilityLabel
{
    return [super accessibilityLabel] == NULL ? @"list" : [super accessibilityLabel];
}

// to avoid warning (why?): method in protocol 'NSAccessibilityElement' not implemented

- (NSRect)accessibilityFrame
{
    return [super accessibilityFrame];
}

// to avoid warning (why?): method in protocol 'NSAccessibilityElement' not implemented

- (id)accessibilityParent
{
    return [super accessibilityParent];
}

@end

