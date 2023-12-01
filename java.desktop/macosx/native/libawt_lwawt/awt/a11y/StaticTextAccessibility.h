#ifndef STATIC_TEXT_ACCESSIBILITY
#define STATIC_TEXT_ACCESSIBILITY

#import "CommonTextAccessibility.h"

#import <AppKit/NSAccessibility.h>


@interface StaticTextAccessibility : CommonTextAccessibility<NSAccessibilityStaticText> {

};
- (NSAccessibilityRole _Nonnull)accessibilityRole;
- (NSString * _Nullable)accessibilityAttributedStringForRange:(NSRange)range;
- (NSString * _Nullable)accessibilityValue;
- (NSRange)accessibilityVisibleCharacterRange;
@end
#endif
