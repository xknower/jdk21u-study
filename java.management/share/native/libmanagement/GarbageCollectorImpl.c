#include <jni.h>
#include "management.h"
#include "sun_management_GarbageCollectorImpl.h"

JNIEXPORT jlong JNICALL Java_sun_management_GarbageCollectorImpl_getCollectionCount
  (JNIEnv *env, jobject mgr) {
    return jmm_interface->GetLongAttribute(env, mgr, JMM_GC_COUNT);
}

JNIEXPORT jlong JNICALL Java_sun_management_GarbageCollectorImpl_getCollectionTime
  (JNIEnv *env, jobject mgr) {
    return jmm_interface->GetLongAttribute(env, mgr, JMM_GC_TIME_MS);
}
