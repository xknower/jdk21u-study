#include "jni.h"
#include "jni_util.h"

/*
 *    Empty JNI_OnLoad - needed to prevent:
 *    <rdar://4984599> AWT's JNI_OnLoad called multiple times
 *    Please remove when <rdar://5121166> has been resolved.
 */
JNIEXPORT jint JNICALL DEF_JNI_OnLoad(JavaVM *vm, void *reserved)
{
    return JNI_VERSION_1_4;
}
