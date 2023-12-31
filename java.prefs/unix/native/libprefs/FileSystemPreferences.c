/*
 * Solaris/Linux platform specific code to support the Prefs API.
 */

#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <errno.h>
#include <utime.h>
#include "jni_util.h"
#include "java_util_prefs_FileSystemPreferences.h"

/*
 * Declare library specific JNI_Onload entry if static build
 */
DEF_STATIC_JNI_OnLoad

JNIEXPORT jint JNICALL
Java_java_util_prefs_FileSystemPreferences_chmod(JNIEnv *env,
                       jclass thisclass, jstring java_fname, jint permission) {
    const char *fname = JNU_GetStringPlatformChars(env, java_fname, NULL);
    int result = -1;
    if (fname) {
        result =  chmod(fname, permission);
        if (result != 0)
            result = errno;
        JNU_ReleaseStringPlatformChars(env, java_fname, fname);
    }
    return (jint) result;
}

#if defined(_ALLBSD_SOURCE)
typedef struct flock FLOCK;
#else
typedef struct flock64 FLOCK;
#endif

/**
 * Try to open a named lock file.
 * The result is a cookie that can be used later to unlock the file.
 * On failure the result is zero.
 */
JNIEXPORT jintArray JNICALL
Java_java_util_prefs_FileSystemPreferences_lockFile0(JNIEnv *env,
    jclass thisclass, jstring java_fname, jint permission, jboolean shared) {
    const char *fname = JNU_GetStringPlatformChars(env, java_fname, NULL);
    int fd, rc;
    int result[2] = {0, 0};
    jintArray javaResult = NULL;
    int old_umask;
    FLOCK fl;

    if (!fname)
        return javaResult;

    fl.l_whence = SEEK_SET;
    fl.l_len = 0;
    fl.l_start = 0;
    if (shared == JNI_TRUE) {
        fl.l_type = F_RDLCK;
    } else {
        fl.l_type = F_WRLCK;
    }

    if (shared == JNI_TRUE) {
        fd = open(fname, O_RDONLY, 0);
        result[1] = errno;
    } else {
        old_umask = umask(0);
        fd = open(fname, O_WRONLY|O_CREAT, permission);
        result[1] = errno;
        umask(old_umask);
    }

    if (fd < 0) {
        result[0] = 0;
    } else {
#if defined(_ALLBSD_SOURCE)
        rc = fcntl(fd, F_SETLK, &fl);
#else
        rc = fcntl(fd, F_SETLK64, &fl);
#endif
        result[1] = errno;
        if (rc < 0) {
            result[0]= 0;
            close(fd);
        } else {
          result[0] = fd;
        }
    }
    JNU_ReleaseStringPlatformChars(env, java_fname, fname);
    javaResult = (*env)->NewIntArray(env,2);
    if (javaResult)
        (*env)->SetIntArrayRegion(env, javaResult, 0, 2, result);
    return javaResult;
}


/**
 * Try to unlock a lock file, using a cookie returned by lockFile.
 */
JNIEXPORT jint JNICALL
Java_java_util_prefs_FileSystemPreferences_unlockFile0(JNIEnv *env,
                                      jclass thisclass, jint fd) {

    int rc;
    FLOCK fl;
    fl.l_whence = SEEK_SET;
    fl.l_len = 0;
    fl.l_start = 0;
    fl.l_type = F_UNLCK;

#if defined(_ALLBSD_SOURCE)
    rc = fcntl(fd, F_SETLK, &fl);
#else
    rc = fcntl(fd, F_SETLK64, &fl);
#endif

    if (rc < 0) {
        close(fd);
        return (jint)errno;
    }
    rc = close(fd);
    if (rc < 0) {
        return (jint) errno;
    }
    return 0;
}
