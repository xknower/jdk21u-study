#include    <jni.h>

#include    "JPLISAgent.h"
#include    "JPLISAssert.h"
#include    "Utilities.h"
#include    "JavaExceptions.h"
#include    "FileSystemSupport.h"   /* For uintptr_t */
#include    "sun_instrument_InstrumentationImpl.h"

/*
 * Copyright 2003 Wily Technology, Inc.
 */

/**
 * This module contains the native method implementations to back the
 * sun.instrument.InstrumentationImpl class.
 * The bridge between Java and native code is built by storing a native
 * pointer to the JPLISAgent data structure in a 64 bit scalar field
 * in the InstrumentationImpl instance which is passed to each method.
 */


/*
 * Native methods
 */

/*
 * Declare library specific JNI_Onload entry if static build
 */
DEF_STATIC_JNI_OnLoad


/*
 * Class:     sun_instrument_InstrumentationImpl
 * Method:    jarFile
 * Signature: (J)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL
Java_sun_instrument_InstrumentationImpl_jarFile
  (JNIEnv * jnienv, jobject implThis, jlong agent) {
    return jarFile(jnienv, (JPLISAgent*)(intptr_t)agent);
}

/*
 * Class:     sun_instrument_InstrumentationImpl
 * Method:    isModifiableClass0
 * Signature: (JLjava/lang/Class;)Z
 */
JNIEXPORT jboolean JNICALL
Java_sun_instrument_InstrumentationImpl_isModifiableClass0
  (JNIEnv * jnienv, jobject implThis, jlong agent, jclass clazz) {
    return isModifiableClass(jnienv, (JPLISAgent*)(intptr_t)agent, clazz);
}

/*
 * Class:     sun_instrument_InstrumentationImpl
 * Method:    isRetransformClassesSupported0
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL
Java_sun_instrument_InstrumentationImpl_isRetransformClassesSupported0
  (JNIEnv * jnienv, jobject implThis, jlong agent) {
    return isRetransformClassesSupported(jnienv, (JPLISAgent*)(intptr_t)agent);
}

/*
 * Class:     sun_instrument_InstrumentationImpl
 * Method:    setHasTransformers
 * Signature: (JZ)V
 */
JNIEXPORT void JNICALL
Java_sun_instrument_InstrumentationImpl_setHasTransformers
  (JNIEnv * jnienv, jobject implThis, jlong agent, jboolean has) {
    setHasTransformers(jnienv, (JPLISAgent*)(intptr_t)agent, has);
}

/*
 * Class:     sun_instrument_InstrumentationImpl
 * Method:    setHasRetransformableTransformers
 * Signature: (JZ)V
 */
JNIEXPORT void JNICALL
Java_sun_instrument_InstrumentationImpl_setHasRetransformableTransformers
  (JNIEnv * jnienv, jobject implThis, jlong agent, jboolean has) {
    setHasRetransformableTransformers(jnienv, (JPLISAgent*)(intptr_t)agent, has);
}

/*
 * Class:     sun_instrument_InstrumentationImpl
 * Method:    retransformClasses0
 * Signature: (J[Ljava/lang/Class;)V
 */
JNIEXPORT void JNICALL
Java_sun_instrument_InstrumentationImpl_retransformClasses0
  (JNIEnv * jnienv, jobject implThis, jlong agent, jobjectArray classes) {
    retransformClasses(jnienv, (JPLISAgent*)(intptr_t)agent, classes);
}

/*
 * Class:     sun_instrument_InstrumentationImpl
 * Method:    redefineClasses0
 * Signature: (J[Ljava/lang/instrument/ClassDefinition;)V
 */
JNIEXPORT void JNICALL Java_sun_instrument_InstrumentationImpl_redefineClasses0
  (JNIEnv * jnienv, jobject implThis, jlong agent, jobjectArray classDefinitions) {
    redefineClasses(jnienv, (JPLISAgent*)(intptr_t)agent, classDefinitions);
}

/*
 * Class:     sun_instrument_InstrumentationImpl
 * Method:    getAllLoadedClasses0
 * Signature: (J)[Ljava/lang/Class;
 */
JNIEXPORT jobjectArray JNICALL Java_sun_instrument_InstrumentationImpl_getAllLoadedClasses0
  (JNIEnv * jnienv, jobject implThis, jlong agent) {
    return getAllLoadedClasses(jnienv, (JPLISAgent*)(intptr_t)agent);
}

/*
 * Class:     sun_instrument_InstrumentationImpl
 * Method:    getInitiatedClasses0
 * Signature: (JLjava/lang/ClassLoader;)[Ljava/lang/Class;
 */
JNIEXPORT jobjectArray JNICALL Java_sun_instrument_InstrumentationImpl_getInitiatedClasses0
  (JNIEnv * jnienv, jobject implThis, jlong agent, jobject classLoader) {
    return getInitiatedClasses(jnienv, (JPLISAgent*)(intptr_t)agent, classLoader);
}

/*
 * Class:     sun_instrument_InstrumentationImpl
 * Method:    getObjectSize0
 * Signature: (JLjava/lang/Object;)J
 */
JNIEXPORT jlong JNICALL Java_sun_instrument_InstrumentationImpl_getObjectSize0
  (JNIEnv * jnienv, jobject implThis, jlong agent, jobject objectToSize) {
    return getObjectSize(jnienv, (JPLISAgent*)(intptr_t)agent, objectToSize);
}


/*
 * Class:     sun_instrument_InstrumentationImpl
 * Method:    appendToClassLoaderSearch0
 * Signature: (JLjava/lang/String;Z)V
 */
JNIEXPORT void JNICALL Java_sun_instrument_InstrumentationImpl_appendToClassLoaderSearch0
  (JNIEnv * jnienv, jobject implThis, jlong agent, jstring jarFile, jboolean isBootLoader) {
    appendToClassLoaderSearch(jnienv, (JPLISAgent*)(intptr_t)agent, jarFile, isBootLoader);
}


/*
 * Class:     sun_instrument_InstrumentationImpl
 * Method:    setNativeMethodPrefixes
 * Signature: (J[Ljava/lang/String;Z)V
 */
JNIEXPORT void JNICALL Java_sun_instrument_InstrumentationImpl_setNativeMethodPrefixes
  (JNIEnv * jnienv, jobject implThis, jlong agent, jobjectArray prefixArray, jboolean isRetransformable) {
    setNativeMethodPrefixes(jnienv, (JPLISAgent*)(intptr_t)agent, prefixArray, isRetransformable);
}


/*
 * Class:     sun_instrument_InstrumentationImpl
 * Method:    loadAgent0
 */
JNIEXPORT void JNICALL Java_sun_instrument_InstrumentationImpl_loadAgent0
   (JNIEnv* env, jclass clazz, jstring jarfile)
{
    extern jint loadAgent(JNIEnv* env, jstring path);
    if (loadAgent(env, jarfile) != JNI_OK) {
        if (!(*env)->ExceptionCheck(env)) {
            createAndThrowInternalError(env);
        }
    }
}

