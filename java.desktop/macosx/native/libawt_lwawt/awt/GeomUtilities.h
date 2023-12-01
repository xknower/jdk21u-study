#import <Cocoa/Cocoa.h>
#include "JNIUtilities.h"

jobject CGToJavaRect(JNIEnv *env, CGRect rect);

jobject NSToJavaRect(JNIEnv *env, NSRect rect);
NSRect JavaToNSRect(JNIEnv *env, jobject rect);

jobject NSToJavaPoint(JNIEnv *env, NSPoint point);
NSPoint JavaToNSPoint(JNIEnv *env, jobject point);

jobject NSToJavaSize(JNIEnv *env, NSSize size);
NSSize JavaToNSSize(JNIEnv *env, jobject);

NSPoint ConvertNSScreenPoint(JNIEnv *env, NSPoint point);
NSRect ConvertNSScreenRect(JNIEnv *env, NSRect rect);
