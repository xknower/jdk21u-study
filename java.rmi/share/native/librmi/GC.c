#include <jni.h>
#include <jvm.h>
#include "sun_rmi_transport_GC.h"


JNIEXPORT jlong JNICALL
Java_sun_rmi_transport_GC_maxObjectInspectionAge(JNIEnv *env, jclass cls)
{
    return JVM_MaxObjectInspectionAge();
}
