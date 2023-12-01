#import "ListRowAccessibility.h"

@interface OutlineRowAccessibility : ListRowAccessibility

@property(readwrite) int accessibleLevel;

- (jobject)currentAccessibleWithENV:(JNIEnv *)env;

@end
