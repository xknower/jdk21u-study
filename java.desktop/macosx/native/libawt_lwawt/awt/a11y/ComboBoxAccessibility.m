#import "ComboBoxAccessibility.h"
#import "../JavaAccessibilityUtilities.h"
#import "ThreadUtilities.h"
#import "JNIUtilities.h"

static jclass sjc_CAccessibility = NULL;

static jmethodID sjm_getAccessibleName = NULL;
#define GET_ACCESSIBLENAME_METHOD_RETURN(ret) \
    GET_CACCESSIBILITY_CLASS_RETURN(ret); \
    GET_STATIC_METHOD_RETURN(sjm_getAccessibleName, sjc_CAccessibility, "getAccessibleName", \
                     "(Ljavax/accessibility/Accessible;Ljava/awt/Component;)Ljava/lang/String;", ret);

@implementation ComboBoxAccessibility

- (CommonComponentAccessibility *)accessibleSelection
{
    JNIEnv *env = [ThreadUtilities getJNIEnv];
    GET_CACCESSIBILITY_CLASS_RETURN(nil);
    DECLARE_STATIC_METHOD_RETURN(sjm_getAccessibleComboboxValue, sjc_CAccessibility, "getAccessibleComboboxValue", "(Ljavax/accessibility/Accessible;Ljava/awt/Component;)Ljavax/accessibility/Accessible;", nil);
    jobject axSelectedChild = (*env)->CallStaticObjectMethod(env, sjc_CAccessibility, sjm_getAccessibleComboboxValue, fAccessible, fComponent);
    CHECK_EXCEPTION();
    if (axSelectedChild == NULL) {
        return nil;
    }
    return [CommonComponentAccessibility createWithAccessible:axSelectedChild withEnv:env withView:fView];
}

// NSAccessibilityElement protocol methods

- (id)accessibilityValue
{
    return [[self accessibleSelection] accessibilityLabel];
}

@end
