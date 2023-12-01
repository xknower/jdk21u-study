#include "jni.h"
#import "OutlineRowAccessibility.h"
#import "JavaAccessibilityUtilities.h"
#import "ThreadUtilities.h"
#import "JNIUtilities.h"
#import "OutlineAccessibility.h"
#import "sun_lwawt_macosx_CAccessibility.h"

static jclass sjc_CAccessibility = NULL;

@implementation OutlineRowAccessibility

@synthesize accessibleLevel;

- (jobject)currentAccessibleWithENV:(JNIEnv *)env
{
    GET_CACCESSIBILITY_CLASS_RETURN(NULL);
    DECLARE_STATIC_METHOD_RETURN(sjm_getAccessibleCurrentAccessible, sjc_CAccessibility, "getAccessibleCurrentAccessible", "(Ljavax/accessibility/Accessible;Ljava/awt/Component;)Ljavax/accessibility/Accessible;", NULL);
    jobject currentAccessible = (*env)->CallStaticObjectMethod(env, sjc_CAccessibility, sjm_getAccessibleCurrentAccessible, fAccessible, fComponent);
    CHECK_EXCEPTION();
    return currentAccessible;
}

// NSAccessibilityElement protocol methods

- (NSArray *)accessibilityChildren
{
    JNIEnv *env = [ThreadUtilities getJNIEnv];
    jobject currentAccessible = [self currentAccessibleWithENV:env];
    if (currentAccessible != NULL) {
        CommonComponentAccessibility *currentElement = [CommonComponentAccessibility createWithAccessible:currentAccessible withEnv:env withView:self->fView isCurrent:YES];
        NSArray *children = [CommonComponentAccessibility childrenOfParent:currentElement withEnv:env withChildrenCode:sun_lwawt_macosx_CAccessibility_JAVA_AX_ALL_CHILDREN allowIgnored:YES];
        if ([children count] != 0) {
            return children;
        }
    }
    return [super accessibilityChildren];
}

- (NSInteger)accessibilityDisclosureLevel
{
    int level = [self accessibleLevel];
    return [(OutlineAccessibility *)[self accessibilityParent] isTreeRootVisible] ? level - 1 : level;
}

- (BOOL)isAccessibilityDisclosed
{
    return isExpanded([ThreadUtilities getJNIEnv], [self axContextWithEnv:[ThreadUtilities getJNIEnv]], self->fComponent);
}

- (NSAccessibilitySubrole)accessibilitySubrole
{
    return NSAccessibilityOutlineRowSubrole;
}

- (NSAccessibilityRole)accessibilityRole
{
    return NSAccessibilityRowRole;
}

- (BOOL)isAccessibilitySelected
{
    return YES;
}

@end
