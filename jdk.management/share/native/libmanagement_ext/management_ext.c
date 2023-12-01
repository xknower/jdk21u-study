#include <stdio.h>
#include <jni.h>
#include "jni_util.h"
#include "jvm.h"
#include "management_ext.h"

#define ERR_MSG_SIZE 128

const JmmInterface* jmm_interface_management_ext = NULL;
static JavaVM* jvm = NULL;
jint jmm_version_management_ext = 0;

void throw_internal_error(JNIEnv* env, const char* msg) {
    char errmsg[128];

    snprintf(errmsg, sizeof(errmsg), "errno: %d error: %s\n", errno, msg);
    JNU_ThrowInternalError(env, errmsg);
}

JNIEXPORT jint JNICALL
   DEF_JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv* env;

    jvm = vm;
    if ((*vm)->GetEnv(vm, (void**) &env, JNI_VERSION_1_2) != JNI_OK) {
        return JNI_ERR;
    }

    jmm_interface_management_ext = (JmmInterface*) JVM_GetManagement(JMM_VERSION);
    if (jmm_interface_management_ext == NULL) {
        JNU_ThrowInternalError(env, "Unsupported Management version");
        return JNI_ERR;
    }

    jmm_version_management_ext = jmm_interface_management_ext->GetVersion(env);
    return (*env)->GetVersion(env);
}
