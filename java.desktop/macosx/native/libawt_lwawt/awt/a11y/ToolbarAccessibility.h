#import "JavaComponentAccessibility.h"
#import "CommonComponentAccessibility.h"

#import <AppKit/AppKit.h>

@interface ToolbarAccessibility : CommonComponentAccessibility {

};
- (NSAccessibilityRole _Nonnull)accessibilityRole;
@end
