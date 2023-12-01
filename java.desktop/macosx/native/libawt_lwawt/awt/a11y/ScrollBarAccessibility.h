#import "JavaComponentAccessibility.h"
#import "CommonComponentAccessibility.h"

#import <AppKit/AppKit.h>

@interface ScrollBarAccessibility : CommonComponentAccessibility {

};
- (NSAccessibilityRole _Nonnull)accessibilityRole;
- (NSAccessibilityOrientation) accessibilityOrientation;
@end
