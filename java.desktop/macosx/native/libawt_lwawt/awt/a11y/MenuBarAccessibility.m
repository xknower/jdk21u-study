#import "MenuBarAccessibility.h"
#import "JNIUtilities.h"
#import "ThreadUtilities.h"
#import "sun_lwawt_macosx_CAccessibility.h"

/*
 * This is the protocol for the Menu Bar component
 */
@implementation MenuBarAccessibility
- (NSAccessibilityRole _Nonnull)accessibilityRole
{
    return NSAccessibilityMenuBarRole;
}

- (BOOL)isAccessibilityElement
{
    return YES;
}

@end
