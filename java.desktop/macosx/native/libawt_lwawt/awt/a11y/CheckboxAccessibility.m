#import "CheckboxAccessibility.h"
#import "JNIUtilities.h"
#import "ThreadUtilities.h"

/*
 * Implementation of the accessibility peer for the checkbox role
 */
@implementation CheckboxAccessibility
- (NSAccessibilityRole _Nonnull)accessibilityRole
{
    return NSAccessibilityCheckBoxRole;
}

- (id _Nonnull) accessibilityValue
{
    AWT_ASSERT_APPKIT_THREAD;
    return [super accessibilityValue];
}

@end
