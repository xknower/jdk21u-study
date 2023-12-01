#import "JavaComponentAccessibility.h"
#import "CommonComponentAccessibility.h"

#import <AppKit/AppKit.h>

@interface ScrollAreaAccessibility : CommonComponentAccessibility {

};
- (NSAccessibilityRole _Nonnull)accessibilityRole;
- (NSArray * _Nullable)accessibilityContents;
- (id _Nullable)accessibilityHorizontalScrollBar;
- (id _Nullable)accessibilityVerticalScrollBar;

- (NSArray * _Nullable)accessibilityContentsAttribute;
- (id _Nullable)getScrollBarwithOrientation:(enum NSAccessibilityOrientation)orientation;
@end
