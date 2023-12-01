#include <stdio.h>
#include <jni.h>
#include "jni_util.h"
#include "jvm.h"
#include "management.h"

#define ERR_MSG_SIZE 128

const JmmInterface* jmm_interface = NULL;
static JavaVM* jvm = NULL;
jint jmm_version = 0;

JNIEXPORT jint JNICALL
   DEF_JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv* env;

    jvm = vm;
    if ((*vm)->GetEnv(vm, (void**) &env, JNI_VERSION_1_2) != JNI_OK) {
        return JNI_ERR;
    }

    jmm_interface = (JmmInterface*) JVM_GetManagement(JMM_VERSION);
    if (jmm_interface == NULL) {
        JNU_ThrowInternalError(env, "Unsupported Management version");
        return JNI_ERR;
    }

    jmm_version = jmm_interface->GetVersion(env);
    return (*env)->GetVersion(env);
}
