#include <jni.h>
#include "jvm.h"
#include "management_ext.h"
#include "com_sun_management_internal_HotSpotDiagnostic.h"

JNIEXPORT void JNICALL
Java_com_sun_management_internal_HotSpotDiagnostic_dumpHeap0
  (JNIEnv *env, jobject dummy, jstring outputfile, jboolean live)
{
    jmm_interface_management_ext->DumpHeap0(env, outputfile, live);
}
