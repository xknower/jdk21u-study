/*
 *      Link foreign methods.  This first half of this file contains the
 *      machine independent dynamic linking routines.
 *      See "BUILD_PLATFORM"/java/lang/linker_md.c to see
 *      the implementation of this shared dynamic linking
 *      interface.
 *
 *      NOTE - source in this file is POSIX.1 compliant, host
 *             specific code lives in the platform specific
 *             code tree.
 */

#include "jni.h"
#include "jni_util.h"
#include "jvm.h"

#include "java_lang_Runtime.h"

JNIEXPORT jlong JNICALL
Java_java_lang_Runtime_freeMemory(JNIEnv *env, jobject this)
{
    return JVM_FreeMemory();
}

JNIEXPORT jlong JNICALL
Java_java_lang_Runtime_totalMemory(JNIEnv *env, jobject this)
{
    return JVM_TotalMemory();
}

JNIEXPORT jlong JNICALL
Java_java_lang_Runtime_maxMemory(JNIEnv *env, jobject this)
{
    return JVM_MaxMemory();
}

JNIEXPORT void JNICALL
Java_java_lang_Runtime_gc(JNIEnv *env, jobject this)
{
    JVM_GC();
}

JNIEXPORT jint JNICALL
Java_java_lang_Runtime_availableProcessors(JNIEnv *env, jobject this)
{
    return JVM_ActiveProcessorCount();
}
