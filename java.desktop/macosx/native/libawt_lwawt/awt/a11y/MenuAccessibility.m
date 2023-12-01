#import "MenuAccessibility.h"

/*
 * Implementing a protocol that represents menus both as submenu and as a
 * MenuBar components
 */
@implementation MenuAccessibility
- (NSAccessibilityRole _Nonnull)accessibilityRole
{
        if ([[[self parent] javaRole] isEqualToString:@"combobox"]) {
            return NSAccessibilityPopUpButtonRole;
        } else if ([[[self parent] javaRole] isEqualToString:@"menubar"]) {
            return NSAccessibilityMenuBarItemRole;
        } else {
            return NSAccessibilityMenuRole;
        }
}

- (BOOL)isAccessibilityElement
{
    return YES;
}

- (id _Nullable)accessibilityValue
{
    return NULL;
}

@end
