#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "nio.h"
#include "nio_util.h"
#include "sun_nio_ch_FileKey.h"

static jfieldID key_volumeSN;    /* id for FileKey.dwVolumeSerialNumber */
static jfieldID key_indexHigh;   /* id for FileKey.nFileIndexHigh */
static jfieldID key_indexLow;    /* id for FileKey.nFileIndexLow */


JNIEXPORT void JNICALL
Java_sun_nio_ch_FileKey_initIDs(JNIEnv *env, jclass clazz)
{
    CHECK_NULL(key_volumeSN = (*env)->GetFieldID(env, clazz, "dwVolumeSerialNumber", "J"));
    CHECK_NULL(key_indexHigh = (*env)->GetFieldID(env, clazz, "nFileIndexHigh", "J"));
    CHECK_NULL(key_indexLow = (*env)->GetFieldID(env, clazz, "nFileIndexLow", "J"));
}


JNIEXPORT void JNICALL
Java_sun_nio_ch_FileKey_init(JNIEnv *env, jobject this, jobject fdo)
{
    HANDLE fileHandle = (HANDLE)(handleval(env, fdo));
    BOOL result;
    BY_HANDLE_FILE_INFORMATION fileInfo;

    result = GetFileInformationByHandle(fileHandle, &fileInfo);
    if (result) {
        (*env)->SetLongField(env, this, key_volumeSN, fileInfo.dwVolumeSerialNumber);
        (*env)->SetLongField(env, this, key_indexHigh, fileInfo.nFileIndexHigh);
        (*env)->SetLongField(env, this, key_indexLow, fileInfo.nFileIndexLow);
    } else {
        JNU_ThrowIOExceptionWithLastError(env, "GetFileInformationByHandle failed");
    }
}
