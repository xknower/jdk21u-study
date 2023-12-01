#import "TabButtonAccessibility.h"
#import "JavaAccessibilityAction.h"
#import "JavaAccessibilityUtilities.h"
#import "ThreadUtilities.h"
#import "JNIUtilities.h"

@implementation TabButtonAccessibility

- (id)initWithParent:(NSObject *)parent withEnv:(JNIEnv *)env withAccessible:(jobject)accessible withIndex:(jint)index withTabGroup:(jobject)tabGroup withView:(NSView *)view withJavaRole:(NSString *)javaRole
{
    self = [super initWithParent:parent withEnv:env withAccessible:accessible withIndex:index withView:view withJavaRole:javaRole];
    if (self) {
        if (tabGroup != NULL) {
            fTabGroupAxContext = (*env)->NewWeakGlobalRef(env, tabGroup);
            CHECK_EXCEPTION();
        } else {
            fTabGroupAxContext = NULL;
        }
    }
    return self;
}

- (void)dealloc
{
    JNIEnv *env = [ThreadUtilities getJNIEnvUncached];

    if (fTabGroupAxContext != NULL) {
        (*env)->DeleteWeakGlobalRef(env, fTabGroupAxContext);
        fTabGroupAxContext = NULL;
    }

    [super dealloc];
}

- (jobject)tabGroup
{
    if (fTabGroupAxContext == NULL) {
        CommonComponentAccessibility* parent = [self typeSafeParent];
        if (parent != nil) {
            JNIEnv *env = [ThreadUtilities getJNIEnv];
            jobject tabGroupAxContext = [parent axContextWithEnv:env];
            fTabGroupAxContext = (*env)->NewWeakGlobalRef(env, tabGroupAxContext);
            CHECK_EXCEPTION();
            (*env)->DeleteLocalRef(env, tabGroupAxContext);
        }
    }
    return fTabGroupAxContext;
}

- (void)performPressAction {
    JNIEnv *env = [ThreadUtilities getJNIEnv];
    TabGroupAction *action = [[TabGroupAction alloc] initWithEnv:env withTabGroup:[self tabGroup] withIndex:fIndex withComponent:fComponent];
    [action perform];
    [action release];
}

// NSAccessibilityElement protocol methods

- (NSAccessibilitySubrole)accessibilitySubrole
{
    if (@available(macOS 10.13, *)) {
        return NSAccessibilityTabButtonSubrole;
    }
    return NSAccessibilityUnknownSubrole;
}

- (id)accessibilityValue
{
    JNIEnv *env = [ThreadUtilities getJNIEnv];
    jobject axContext = [self axContextWithEnv:env];
    jobject selAccessible = getAxContextSelection(env, [self tabGroup], fIndex, fComponent);

    // Returns the current selection of the page tab list
    id val = [NSNumber numberWithBool:ObjectEquals(env, axContext, selAccessible, fComponent)];

    (*env)->DeleteLocalRef(env, selAccessible);
    (*env)->DeleteLocalRef(env, axContext);
    return val;
}

- (BOOL)accessibilityPerformPress {
    [self performPressAction];
    return YES;
}

@end
