#import "OutlineAccessibility.h"
#import "JavaAccessibilityUtilities.h"
#import "ThreadUtilities.h"
#import "JNIUtilities.h"

static jclass sjc_CAccessibility = NULL;

static jmethodID sjm_isTreeRootVisible = NULL;
#define GET_ISTREEROOTVISIBLE_METHOD_RETURN(ret) \
    GET_CACCESSIBILITY_CLASS_RETURN(ret); \
    GET_STATIC_METHOD_RETURN(sjm_isTreeRootVisible, sjc_CAccessibility, "isTreeRootVisible", \
                     "(Ljavax/accessibility/Accessible;Ljava/awt/Component;)Z", ret);

@implementation OutlineAccessibility

- (BOOL)isTreeRootVisible
{
    JNIEnv *env = [ThreadUtilities getJNIEnv];
    GET_ISTREEROOTVISIBLE_METHOD_RETURN(NO);
    bool isTreeRootVisible = (*env)->CallStaticBooleanMethod(env, sjc_CAccessibility, sjm_isTreeRootVisible, fAccessible, fComponent);
    CHECK_EXCEPTION();
    return isTreeRootVisible;
}

// NSAccessibilityElement protocol methods

- (NSString *)accessibilityLabel
{
    return [[super accessibilityLabel] isEqualToString:@"list"] ? @"tree" : [super accessibilityLabel];
}

@end
