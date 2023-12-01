#include <sys/types.h>
#include <sys/stat.h>

#include "jni.h"
#include "jni_util.h"
#include "jdk_internal_agent_FileSystemImpl.h"

#ifdef _ALLBSD_SOURCE
#define stat64 stat
#endif

/*
 * JNI_OnLoad
 */
JNIEXPORT jint JNICALL DEF_JNI_OnLoad(JavaVM *vm, void *reserved)
{
    JNIEnv* env;

    if ((*vm)->GetEnv(vm, (void**) &env, JNI_VERSION_1_2) != JNI_OK) {
        return JNI_EVERSION; /* JNI version not supported */
    }

    return JNI_VERSION_19;
}

/*
 * Class:     jdk_internal_agent_FileSystemImpl
 * Method:    isAccessUserOnly0
 * Signature: (Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_jdk_internal_agent_FileSystemImpl_isAccessUserOnly0
  (JNIEnv *env, jclass ignored, jstring str)
{
    jboolean res = JNI_FALSE;
    jboolean isCopy;
    const char *path = JNU_GetStringPlatformChars(env, str, &isCopy);
    if (path != NULL) {
        struct stat64 sb;
        if (stat64(path, &sb) == 0) {
            res = ((sb.st_mode & (S_IRGRP|S_IWGRP|S_IROTH|S_IWOTH)) == 0) ? JNI_TRUE : JNI_FALSE;
        } else {
            JNU_ThrowIOExceptionWithLastError(env, "stat64 failed");
        }
        if (isCopy) {
            JNU_ReleaseStringPlatformChars(env, str, path);
        }
    }
    return res;
}
