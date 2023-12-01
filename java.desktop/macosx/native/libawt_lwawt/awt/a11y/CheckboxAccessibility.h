#import "ButtonAccessibility.h"

@interface CheckboxAccessibility : ButtonAccessibility <NSAccessibilityCheckBox> {

};
- (NSAccessibilityRole _Nonnull)accessibilityRole;
- (id _Nonnull)accessibilityValue;
@end
