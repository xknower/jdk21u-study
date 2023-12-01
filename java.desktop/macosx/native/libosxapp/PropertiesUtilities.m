#import "PropertiesUtilities.h"
#import "JNIUtilities.h"

@implementation PropertiesUtilities

+ (NSString *) javaSystemPropertyForKey:(NSString *)key withEnv:(JNIEnv *)env {
    DECLARE_CLASS_RETURN(jc_System, "java/lang/System", nil);
    DECLARE_STATIC_METHOD_RETURN(jm_getProperty, jc_System,
                                 "getProperty", "(Ljava/lang/String;)Ljava/lang/String;", nil);

    jstring jKey = NSStringToJavaString(env, key);
    jstring jValue = (*env)->CallStaticObjectMethod(env, jc_System, jm_getProperty, jKey);
    (*env)->DeleteLocalRef(env, jKey);
    CHECK_EXCEPTION_NULL_RETURN(jValue, nil);

    NSString *value = JavaStringToNSString(env, jValue);
    (*env)->DeleteLocalRef(env, jValue);
    return value;
}

@end
