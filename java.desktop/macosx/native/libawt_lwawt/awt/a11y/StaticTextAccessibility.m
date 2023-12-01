#import "StaticTextAccessibility.h"

@implementation StaticTextAccessibility

- (NSAccessibilityRole _Nonnull)accessibilityRole
{
    return NSAccessibilityStaticTextRole;
}

- (NSString * _Nullable)accessibilityAttributedStringForRange:(NSRange)range
{
    return [self accessibilityStringForRangeAttribute:range];
}

- (NSString * _Nullable)accessibilityValue
{
    return [self accessibilityValueAttribute];
}

- (NSRange)accessibilityVisibleCharacterRange
{
    return [self accessibilityVisibleCharacterRangeAttribute];
}

- (NSRect)accessibilityFrame
{
    return [super accessibilityFrame];
}

- (id)accessibilityParent
{
    return [super accessibilityParent];
}

@end
