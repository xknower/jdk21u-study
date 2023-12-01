#include <jni.h>
#include "management_ext.h"
#include "com_sun_management_internal_GarbageCollectorExtImpl.h"

JNIEXPORT void JNICALL Java_com_sun_management_internal_GarbageCollectorExtImpl_setNotificationEnabled
(JNIEnv *env, jobject dummy, jobject gc,jboolean enabled) {

    if (gc == NULL) {
        JNU_ThrowNullPointerException(env, "Invalid GarbageCollectorMXBean");
        return;
    }
    if((jmm_version_management_ext > JMM_VERSION_1_2)
       || (jmm_version_management_ext == JMM_VERSION_1_2 && ((jmm_version_management_ext&0xFF)>=1))) {
      jmm_interface_management_ext->SetGCNotificationEnabled(env, gc, enabled);
    }
}
