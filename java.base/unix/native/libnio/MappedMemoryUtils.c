#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jlong.h"
#include "java_nio_MappedMemoryUtils.h"
#include <assert.h>
#include <sys/mman.h>
#include <stddef.h>
#include <stdlib.h>

/* Output type for mincore(2) */
#ifdef __linux__
typedef unsigned char mincore_vec_t;
#else
typedef char mincore_vec_t;
#endif

JNIEXPORT jboolean JNICALL
Java_java_nio_MappedMemoryUtils_isLoaded0(JNIEnv *env, jobject obj, jlong address,
                                         jlong len, jlong numPages)
{
    jboolean loaded = JNI_TRUE;
    int result = 0;
    long i = 0;
    void *a = (void *) jlong_to_ptr(address);
    mincore_vec_t* vec = NULL;

    /* Include space for one sentinel byte at the end of the buffer
     * to catch overflows. */
    vec = (mincore_vec_t*) malloc(numPages + 1);

    if (vec == NULL) {
        JNU_ThrowOutOfMemoryError(env, NULL);
        return JNI_FALSE;
    }

    vec[numPages] = '\x7f'; /* Write sentinel. */
    result = mincore(a, (size_t)len, vec);
    assert(vec[numPages] == '\x7f'); /* Check sentinel. */

    if (result == -1) {
        JNU_ThrowIOExceptionWithLastError(env, "mincore failed");
        free(vec);
        return JNI_FALSE;
    }

    for (i=0; i<numPages; i++) {
        if (vec[i] == 0) {
            loaded = JNI_FALSE;
            break;
        }
    }
    free(vec);
    return loaded;
}


JNIEXPORT void JNICALL
Java_java_nio_MappedMemoryUtils_load0(JNIEnv *env, jobject obj, jlong address,
                                     jlong len)
{
    char *a = (char *)jlong_to_ptr(address);
    int result = madvise((caddr_t)a, (size_t)len, MADV_WILLNEED);
    if (result == -1) {
        JNU_ThrowIOExceptionWithMessageAndLastError(env, "madvise with advise MADV_WILLNEED failed");
    }
}

JNIEXPORT void JNICALL
Java_java_nio_MappedMemoryUtils_unload0(JNIEnv *env, jobject obj, jlong address,
                                     jlong len)
{
    char *a = (char *)jlong_to_ptr(address);
    int result = madvise((caddr_t)a, (size_t)len, MADV_DONTNEED);
    if (result == -1) {
        JNU_ThrowIOExceptionWithMessageAndLastError(env, "madvise with advise MADV_DONTNEED failed");
    }
}

JNIEXPORT void JNICALL
Java_java_nio_MappedMemoryUtils_force0(JNIEnv *env, jobject obj, jobject fdo,
                                      jlong address, jlong len)
{
    void* a = (void *)jlong_to_ptr(address);
    int result = msync(a, (size_t)len, MS_SYNC);
    if (result == -1) {
        JNU_ThrowIOExceptionWithMessageAndLastError(env, "msync with parameter MS_SYNC failed");
    }
}
