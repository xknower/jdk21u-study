#include "jni.h"
#include "jni_util.h"

#import <Cocoa/Cocoa.h>


JNIEXPORT @interface PropertiesUtilities : NSObject

+ (NSString *) javaSystemPropertyForKey:(NSString *)key withEnv:(JNIEnv *)env;

@end
