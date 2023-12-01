#import "CommonComponentAccessibility.h"

@interface NavigableTextAccessibility : CommonComponentAccessibility <NSAccessibilityNavigableStaticText>

@property(readonly) BOOL accessibleIsPasswordText;

@end
