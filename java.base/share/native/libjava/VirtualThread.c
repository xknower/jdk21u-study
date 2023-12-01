#include "jni.h"
#include "jvm.h"
#include "java_lang_VirtualThread.h"

#define THREAD "Ljava/lang/Thread;"
#define VIRTUAL_THREAD  "Ljava/lang/VirtualThread;"

static JNINativeMethod methods[] = {
    { "notifyJvmtiStart",        "()V",  (void *)&JVM_VirtualThreadStart },
    { "notifyJvmtiEnd",          "()V",  (void *)&JVM_VirtualThreadEnd },
    { "notifyJvmtiMount",        "(Z)V", (void *)&JVM_VirtualThreadMount },
    { "notifyJvmtiUnmount",      "(Z)V", (void *)&JVM_VirtualThreadUnmount },
    { "notifyJvmtiHideFrames",   "(Z)V", (void *)&JVM_VirtualThreadHideFrames },
};

JNIEXPORT void JNICALL
Java_java_lang_VirtualThread_registerNatives(JNIEnv *env, jclass clazz) {
    (*env)->RegisterNatives(env, clazz, methods, (sizeof(methods)/sizeof(methods[0])));
}
