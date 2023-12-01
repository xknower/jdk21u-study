#include "jni.h"
#include "jvm.h"

#include "jdk_internal_vm_Continuation.h"

/*
 * Class:     jdk_internal_vm_Continuation
 * Method:    registerNatives
 * Signature: ()
 */
JNIEXPORT void JNICALL
Java_jdk_internal_vm_Continuation_registerNatives(JNIEnv *env, jclass cls)
{
    JVM_RegisterContinuationMethods(env, cls);
}
