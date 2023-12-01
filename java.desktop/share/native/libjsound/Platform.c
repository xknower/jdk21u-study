#include "Utilities.h"
// Platform.java includes
#include "com_sun_media_sound_Platform.h"

/*
 * Declare library specific JNI_Onload entry if static build
 */
DEF_STATIC_JNI_OnLoad

/*
 * Class:     com_sun_media_sound_Platform
 * Method:    nIsBigEndian
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_com_sun_media_sound_Platform_nIsBigEndian(JNIEnv *env, jclass clss) {
    return UTIL_IsBigEndianPlatform();
}
