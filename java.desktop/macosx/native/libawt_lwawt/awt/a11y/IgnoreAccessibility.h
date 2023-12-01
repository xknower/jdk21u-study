#import "JavaComponentAccessibility.h"
#import "CommonComponentAccessibility.h"

#import <AppKit/AppKit.h>

@interface IgnoreAccessibility : CommonComponentAccessibility {

};
- (BOOL)isAccessibilityElement;
@end
