#include <windows.h>

#include "jni.h"
#include "jni_util.h"
#include "jlong.h"
#include "nio.h"
#include "nio_util.h"

#include "sun_nio_ch_Iocp.h"


static jfieldID completionStatus_error;
static jfieldID completionStatus_bytesTransferred;
static jfieldID completionStatus_completionKey;
static jfieldID completionStatus_overlapped;


JNIEXPORT void JNICALL
Java_sun_nio_ch_Iocp_initIDs(JNIEnv* env, jclass this)
{
    jclass clazz;

    clazz = (*env)->FindClass(env, "sun/nio/ch/Iocp$CompletionStatus");
    CHECK_NULL(clazz);
    completionStatus_error = (*env)->GetFieldID(env, clazz, "error", "I");
    CHECK_NULL(completionStatus_error);
    completionStatus_bytesTransferred = (*env)->GetFieldID(env, clazz, "bytesTransferred", "I");
    CHECK_NULL(completionStatus_bytesTransferred);
    completionStatus_completionKey = (*env)->GetFieldID(env, clazz, "completionKey", "I");
    CHECK_NULL(completionStatus_completionKey);
    completionStatus_overlapped = (*env)->GetFieldID(env, clazz, "overlapped", "J");
    CHECK_NULL(completionStatus_overlapped);
}

JNIEXPORT jlong JNICALL
Java_sun_nio_ch_Iocp_createIoCompletionPort(JNIEnv* env, jclass this,
    jlong handle, jlong existingPort, jint completionKey, jint concurrency)
{
    ULONG_PTR ck = completionKey;
    HANDLE port = CreateIoCompletionPort((HANDLE)jlong_to_ptr(handle),
                                         (HANDLE)jlong_to_ptr(existingPort),
                                         ck,
                                         (DWORD)concurrency);
    if (port == NULL) {
        JNU_ThrowIOExceptionWithLastError(env, "CreateIoCompletionPort failed");
    }
    return ptr_to_jlong(port);
}

JNIEXPORT void JNICALL
Java_sun_nio_ch_Iocp_close0(JNIEnv* env, jclass this,
    jlong handle)
{
    HANDLE h = (HANDLE)jlong_to_ptr(handle);
    CloseHandle(h);
}


JNIEXPORT void JNICALL
Java_sun_nio_ch_Iocp_getQueuedCompletionStatus(JNIEnv* env, jclass this,
    jlong completionPort, jobject obj)
{
    DWORD bytesTransferred;
    ULONG_PTR completionKey;
    OVERLAPPED *lpOverlapped;
    BOOL res;

    res = GetQueuedCompletionStatus((HANDLE)jlong_to_ptr(completionPort),
                                  &bytesTransferred,
                                  &completionKey,
                                  &lpOverlapped,
                                  INFINITE);
    if (res == 0 && lpOverlapped == NULL) {
        JNU_ThrowIOExceptionWithLastError(env, "GetQueuedCompletionStatus failed");
    } else {
        DWORD ioResult = (res == 0) ? GetLastError() : 0;
        (*env)->SetIntField(env, obj, completionStatus_error, ioResult);
        (*env)->SetIntField(env, obj, completionStatus_bytesTransferred,
            (jint)bytesTransferred);
        (*env)->SetIntField(env, obj, completionStatus_completionKey,
            (jint)completionKey);
        (*env)->SetLongField(env, obj, completionStatus_overlapped,
            ptr_to_jlong(lpOverlapped));

    }
}

JNIEXPORT void JNICALL
Java_sun_nio_ch_Iocp_postQueuedCompletionStatus(JNIEnv* env, jclass this,
    jlong completionPort, jint completionKey)
{
    BOOL res;

    res = PostQueuedCompletionStatus((HANDLE)jlong_to_ptr(completionPort),
                                     (DWORD)0,
                                     (DWORD)completionKey,
                                     NULL);
    if (res == 0) {
        JNU_ThrowIOExceptionWithLastError(env, "PostQueuedCompletionStatus");
    }
}

JNIEXPORT jstring JNICALL
Java_sun_nio_ch_Iocp_getErrorMessage(JNIEnv* env, jclass this, jint errorCode)
{
    WCHAR message[255];

    DWORD len = FormatMessageW(FORMAT_MESSAGE_FROM_SYSTEM,
                               NULL,
                               (DWORD)errorCode,
                               0,
                               &message[0],
                               255,
                               NULL);


    if (len == 0) {
        return NULL;
    } else {
        if (len > 3) {
            // Drop final '.', CR, LF
            if (message[len - 1] == L'\n') len--;
            if (message[len - 1] == L'\r') len--;
            if (message[len - 1] == L'.') len--;
            message[len] = L'\0';
        }

        return (*env)->NewString(env, (const jchar *)message, (jsize)wcslen(message));
    }
}
