#import "CommonComponentAccessibility.h"

@interface TableRowAccessibility : CommonComponentAccessibility <NSAccessibilityRow>

@property(readonly) NSUInteger rowNumberInTable;

@end
