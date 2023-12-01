#include "jni.h"
#include "jvm.h"

#include "jdk_internal_vm_ForeignLinkerSupport.h"

JNIEXPORT jboolean JNICALL
Java_jdk_internal_vm_ForeignLinkerSupport_isSupported0(JNIEnv *env, jclass cls) {
    return JVM_IsForeignLinkerSupported();
}
