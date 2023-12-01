#import "ButtonAccessibility.h"

@interface RadiobuttonAccessibility : ButtonAccessibility <NSAccessibilityRadioButton> {

};
- (NSAccessibilityRole _Nonnull)accessibilityRole;
- (id _Nonnull)accessibilityValue;
@end
