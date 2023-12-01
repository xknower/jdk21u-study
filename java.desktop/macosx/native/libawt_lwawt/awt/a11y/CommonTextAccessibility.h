#ifndef COMMON_TEXT_ACCESSIBILITY
#define COMMON_TEXT_ACCESSIBILITY

#import "CommonComponentAccessibility.h"
#import "JavaAccessibilityUtilities.h"

#import <AppKit/NSAccessibility.h>

@interface CommonTextAccessibility : CommonComponentAccessibility {

}
- (NSString * _Nullable)accessibilityValueAttribute;
- (NSRange)accessibilityVisibleCharacterRangeAttribute;
- (NSString * _Nullable)accessibilityStringForRangeAttribute:(NSRange)parameter;
@end

#endif
