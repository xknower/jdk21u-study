#import "JavaComponentAccessibility.h"
#import "CommonComponentAccessibility.h"

#import <AppKit/AppKit.h>

@interface GroupAccessibility : CommonComponentAccessibility <NSAccessibilityGroup> {

};
- (NSAccessibilityRole _Nonnull)accessibilityRole;
- (NSArray * _Nullable)accessibilityChildren;
@end
