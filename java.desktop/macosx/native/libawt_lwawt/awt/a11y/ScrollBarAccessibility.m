#import "ScrollBarAccessibility.h"
#import "ThreadUtilities.h"
#import "JNIUtilities.h"

/*
 * Implementation of the accessibility peer for the ScrollBar role
 */
@implementation ScrollBarAccessibility

- (NSAccessibilityRole _Nonnull)accessibilityRole
{
    return NSAccessibilityScrollBarRole;
}

- (NSAccessibilityOrientation) accessibilityOrientation
{
    JNIEnv *env = [ThreadUtilities getJNIEnv];
    jobject elementAxContext = [self axContextWithEnv:env];
    if (isHorizontal(env, elementAxContext, fComponent)) {
        (*env)->DeleteLocalRef(env, elementAxContext);
        return NSAccessibilityOrientationHorizontal;
    } else if (isVertical(env, elementAxContext, fComponent)) {
        (*env)->DeleteLocalRef(env, elementAxContext);
        return NSAccessibilityOrientationVertical;
    } else {
        (*env)->DeleteLocalRef(env, elementAxContext);
        return NSAccessibilityOrientationUnknown;
    }
}
@end
