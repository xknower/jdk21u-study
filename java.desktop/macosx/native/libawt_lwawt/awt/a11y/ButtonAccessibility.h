#import "JavaComponentAccessibility.h"
#import "CommonComponentAccessibility.h"

#import <AppKit/AppKit.h>

@interface ButtonAccessibility : CommonComponentAccessibility <NSAccessibilityButton> {

};
- (NSAccessibilityRole _Nonnull)accessibilityRole;
- (NSString * _Nullable)accessibilityLabel;
- (BOOL)accessibilityPerformPress;
@end
