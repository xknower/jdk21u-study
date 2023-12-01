#import "JavaComponentAccessibility.h"
#import "CommonComponentAccessibility.h"

#import <AppKit/AppKit.h>

@interface SliderAccessibility : CommonComponentAccessibility <NSAccessibilitySlider> {

};
- (NSAccessibilityRole _Nonnull)accessibilityRole;
- (NSString * _Nullable)accessibilityLabel;
- (id _Nullable)accessibilityValue;
- (BOOL)accessibilityPerformDecrement;
- (BOOL)accessibilityPerformIncrement;
@end
