#import "JNIUtilities.h"

extern NSString *const JavaAccessibilityIgnore;

extern NSMutableDictionary *sRoles;
extern void initializeRoles();

#define GET_CACCESSIBILITY_CLASS() \
    GET_CLASS(sjc_CAccessibility, "sun/lwawt/macosx/CAccessibility");
#define GET_CACCESSIBILITY_CLASS_RETURN(ret) \
    GET_CLASS_RETURN(sjc_CAccessibility, "sun/lwawt/macosx/CAccessibility", ret);

NSSize getAxComponentSize(JNIEnv *env, jobject axComponent, jobject component);
NSString *getJavaRole(JNIEnv *env, jobject axComponent, jobject component);
jobject getAxSelection(JNIEnv *env, jobject axContext, jobject component);
jobject getAxContextSelection(JNIEnv *env, jobject axContext, jint index, jobject component);
void setAxContextSelection(JNIEnv *env, jobject axContext, jint index, jobject component);
jobject getAxContext(JNIEnv *env, jobject accessible, jobject component);
BOOL isChildSelected(JNIEnv *env, jobject accessible, jint index, jobject component);
jobject getAxStateSet(JNIEnv *env, jobject axContext, jobject component);
BOOL containsAxState(JNIEnv *env, jobject axContext, jobject axState, jobject component);
BOOL isVertical(JNIEnv *env, jobject axContext, jobject component);
BOOL isHorizontal(JNIEnv *env, jobject axContext, jobject component);
BOOL isShowing(JNIEnv *env, jobject axContext, jobject component);
BOOL isSelectable(JNIEnv *env, jobject axContext, jobject component);
BOOL isExpanded(JNIEnv *env, jobject axContext, jobject component);
NSPoint getAxComponentLocationOnScreen(JNIEnv *env, jobject axComponent, jobject component);
jint getAxTextCharCount(JNIEnv *env, jobject axText, jobject component);

// these methods are copied from the corresponding NSAccessibility methods
id JavaAccessibilityAttributeValue(id element, NSString *attribute);
BOOL JavaAccessibilityIsAttributeSettable(id element, NSString *attribute);
void JavaAccessibilitySetAttributeValue(id element, NSString *attribute, id value);

// these methods are copied from the corresponding NSAccessibilityErrors methods
void JavaAccessibilityRaiseSetAttributeToIllegalTypeException(const char *functionName, id element, NSString *attribute, id value);
void JavaAccessibilityRaiseUnimplementedAttributeException(const char *functionName, id element, NSString *attribute);
void JavaAccessibilityRaiseIllegalParameterTypeException(const char *functionName, id element, NSString *attribute, id parameter);
BOOL ObjectEquals(JNIEnv *env, jobject a, jobject b, jobject component);
NSNumber* JavaNumberToNSNumber(JNIEnv *env, jobject jnumber);
NSValue *javaIntArrayToNSRangeValue(JNIEnv* env, jintArray array);
