#import "CellAccessibility.h"
#import "ThreadUtilities.h"
#import "TableAccessibility.h"

@implementation CellAccessibility

// NSAccessibilityElement protocol methods

- (NSAccessibilityRole)accessibilityRole
{
    return NSAccessibilityCellRole;
}

- (NSInteger)accessibilityIndex
{
    return self->fIndex;
}

- (NSRect)accessibilityFrame
{
    return [super accessibilityFrame];
}

- (id)accessibilityParent
{
    return [super accessibilityParent];
}

- (NSRange)accessibilityRowIndexRange {
    NSInteger location = -1;
    if ([[(CommonComponentAccessibility *)fParent accessibilityParent] isKindOfClass:[TableAccessibility class]]) {
        TableAccessibility *table = [(CommonComponentAccessibility *)fParent accessibilityParent];
        location = [table accessibleRowAtIndex:fIndex];
    }

    return NSMakeRange(location, 1);
}

- (NSRange)accessibilityColumnIndexRange {
    NSInteger location = -1;
    if ([[(CommonComponentAccessibility *)fParent accessibilityParent] isKindOfClass:[TableAccessibility class]]) {
        TableAccessibility *table = [(CommonComponentAccessibility *)fParent accessibilityParent];
        location = [table accessibleColumnAtIndex:fIndex];
    }

    return NSMakeRange(location, 1);
}

@end
