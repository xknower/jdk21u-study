#import "RadiobuttonAccessibility.h"
#import "JNIUtilities.h"
#import "ThreadUtilities.h"

/*
 * Implementation of the accessibility peer for the radiobutton role
 */
@implementation RadiobuttonAccessibility
- (NSAccessibilityRole _Nonnull)accessibilityRole
{
    return NSAccessibilityRadioButtonRole;
}

- (id _Nonnull) accessibilityValue
{
    AWT_ASSERT_APPKIT_THREAD;
    return [super accessibilityValue];
}

@end
