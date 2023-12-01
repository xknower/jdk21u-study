#import "JavaComponentAccessibility.h"
#import "CommonComponentAccessibility.h"

#import <AppKit/AppKit.h>

@interface ImageAccessibility : CommonComponentAccessibility <NSAccessibilityImage> {

};
- (NSAccessibilityRole _Nonnull)accessibilityRole;
- (NSString * _Nullable)accessibilityLabel;
@end
