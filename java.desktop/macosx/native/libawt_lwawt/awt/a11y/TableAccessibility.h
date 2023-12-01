#import "CommonComponentAccessibility.h"

@interface TableAccessibility : CommonComponentAccessibility <NSAccessibilityTable>
{
    NSMutableDictionary<NSNumber*, id> *rowCache;
    BOOL cacheValid;
}

- (BOOL)isAccessibleChildSelectedFromIndex:(int)index;
- (int) accessibleRowAtIndex:(int)index;
- (int) accessibleColumnAtIndex:(int)index;

@end
