#include "jni.h"
#include "jvm.h"

#include "jdk_internal_misc_PreviewFeatures.h"

JNIEXPORT jboolean JNICALL
Java_jdk_internal_vm_ContinuationSupport_isSupported0(JNIEnv *env, jclass cls) {
    return JVM_IsContinuationsSupported();
}
