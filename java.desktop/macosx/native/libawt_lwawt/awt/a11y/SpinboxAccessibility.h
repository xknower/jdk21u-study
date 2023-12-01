#import "JavaComponentAccessibility.h"
#import "CommonComponentAccessibility.h"

#import <AppKit/AppKit.h>

@interface SpinboxAccessibility : CommonComponentAccessibility <NSAccessibilityStepper> {

};
- (NSAccessibilityRole _Nonnull)accessibilityRole;
- (NSString * _Nullable)accessibilityLabel;
- (id _Nullable)accessibilityValue;
- (BOOL)accessibilityPerformDecrement;
- (BOOL)accessibilityPerformIncrement;
@end
