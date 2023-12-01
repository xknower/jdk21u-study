#include "jni_util.h"


JNIEXPORT jlong JNICALL
Java_jdk_internal_misc_VM_getuid(JNIEnv *env, jclass thisclass) {

    /* -1 means function not available. */
    return -1;
}

JNIEXPORT jlong JNICALL
Java_jdk_internal_misc_VM_geteuid(JNIEnv *env, jclass thisclass) {

    /* -1 means function not available. */
    return -1;
}

JNIEXPORT jlong JNICALL
Java_jdk_internal_misc_VM_getgid(JNIEnv *env, jclass thisclass) {

    /* -1 means function not available. */
    return -1;
}

JNIEXPORT jlong JNICALL
Java_jdk_internal_misc_VM_getegid(JNIEnv *env, jclass thisclass) {

    /* -1 means function not available. */
    return -1;
}
