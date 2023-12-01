#import "JavaComponentAccessibility.h"
#import "GroupAccessibility.h"

#import <AppKit/AppKit.h>

@interface ProgressIndicatorAccessibility : GroupAccessibility {

};
- (NSAccessibilityRole _Nonnull)accessibilityRole;
- (NSString * _Nullable)accessibilityValue;
@end
