#import "ListAccessibility.h"

// This is a tree representation. See: https://developer.apple.com/documentation/appkit/nsoutlineview

@interface OutlineAccessibility : ListAccessibility <NSAccessibilityOutline>

@property(readonly) BOOL isTreeRootVisible;

@end
