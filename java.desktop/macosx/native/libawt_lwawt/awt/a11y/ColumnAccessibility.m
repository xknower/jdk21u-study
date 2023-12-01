#include "jni.h"
#import "JavaAccessibilityAction.h"
#import "JavaAccessibilityUtilities.h"
#import "CellAccessibility.h"
#import "ColumnAccessibility.h"
#import "TableAccessibility.h"
#import "ThreadUtilities.h"
#import "JNIUtilities.h"
#import "sun_lwawt_macosx_CAccessibility.h"

static jclass sjc_CAccessibility = NULL;

static jmethodID jm_getChildrenAndRoles = NULL;
#define GET_CHILDRENANDROLES_METHOD_RETURN(ret) \
    GET_CACCESSIBILITY_CLASS_RETURN(ret); \
    GET_STATIC_METHOD_RETURN(jm_getChildrenAndRoles, sjc_CAccessibility, "getChildrenAndRoles",\
                      "(Ljavax/accessibility/Accessible;Ljava/awt/Component;IZ)[Ljava/lang/Object;", ret);

@implementation ColumnAccessibility

// NSAccessibilityElement protocol methods

- (NSAccessibilityRole)accessibilityRole
{
    return NSAccessibilityColumnRole;
}

- (NSInteger)accessibilityIndex
{
    return fIndex;
}

@end
