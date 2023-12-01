/* Empty stubs for now to satisfy the new build process.                 */
/* Implement and update https://bugs.openjdk.org/browse/JDK-8030957 */

#include <jni.h>
#include "com_sun_management_internal_OperatingSystemImpl.h"

JNIEXPORT jdouble JNICALL
Java_com_sun_management_internal_OperatingSystemImpl_getCpuLoad0
(JNIEnv *env, jobject dummy)
{
    return -1.0;
}

JNIEXPORT jdouble JNICALL
Java_com_sun_management_internal_OperatingSystemImpl_getProcessCpuLoad0
(JNIEnv *env, jobject dummy)
{
    return -1.0;
}

JNIEXPORT jdouble JNICALL
Java_com_sun_management_internal_OperatingSystemImpl_getSingleCpuLoad0
(JNIEnv *env, jobject dummy, jint cpu_number)
{
    return -1.0;
}

JNIEXPORT jlong JNICALL
Java_com_sun_management_internal_OperatingSystemImpl_getHostTotalCpuTicks0
(JNIEnv *env, jobject mbean)
{
    return -1;
}

JNIEXPORT jint JNICALL
Java_com_sun_management_internal_OperatingSystemImpl_getHostConfiguredCpuCount0
(JNIEnv *env, jobject mbean)
{
    return -1;
}

JNIEXPORT jint JNICALL
Java_com_sun_management_internal_OperatingSystemImpl_getHostOnlineCpuCount0
(JNIEnv *env, jobject mbean)
{
    return -1;
}
