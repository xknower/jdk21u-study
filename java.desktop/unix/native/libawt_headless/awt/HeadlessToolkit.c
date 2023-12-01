#include <jni.h>
#include <jni_util.h>

#ifdef HEADLESS

extern JavaVM *jvm;

JNIEXPORT jint JNICALL
DEF_JNI_OnLoad(JavaVM *vm, void *reserved)
{
    jvm = vm;
    return JNI_VERSION_1_2;
}

#endif
