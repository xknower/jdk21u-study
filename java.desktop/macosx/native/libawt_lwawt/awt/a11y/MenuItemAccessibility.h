#import "CommonComponentAccessibility.h"
#import "ButtonAccessibility.h"

#import <AppKit/AppKit.h>

@interface MenuItemAccessibility : ButtonAccessibility {

};
- (NSAccessibilityRole _Nonnull)accessibilityRole;
- (void)handleAction:(NSMenuItem * _Nonnull)sender;
@end
