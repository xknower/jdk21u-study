#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <jni_util.h>

#include "j2secmod.h"

extern void p11ThrowNullPointerException(JNIEnv *env, const char *message);
extern void p11ThrowIOException(JNIEnv *env, const char *message);

void *p11FindFunction(JNIEnv *env, jlong jHandle, const char *functionName) {
    HINSTANCE hModule = (HINSTANCE)jHandle;
    void *fAddress = GetProcAddress(hModule, functionName);
    if (fAddress == NULL) {
        char errorMessage[256];
        _snprintf(errorMessage, sizeof(errorMessage), "Symbol not found: %s", functionName);
        p11ThrowNullPointerException(env, errorMessage);
        return NULL;
    }
    return fAddress;
}

JNIEXPORT jlong JNICALL Java_sun_security_pkcs11_Secmod_nssGetLibraryHandle
  (JNIEnv *env, jclass thisClass, jstring jLibName)
{
    const char *libName = (*env)->GetStringUTFChars(env, jLibName, NULL);
    HMODULE hModule = GetModuleHandle(libName);
    dprintf2("-handle for %s: %d\n", libName, hModule);
    (*env)->ReleaseStringUTFChars(env, jLibName, libName);
    return (jlong)hModule;
}

JNIEXPORT jlong JNICALL Java_sun_security_pkcs11_Secmod_nssLoadLibrary
  (JNIEnv *env, jclass thisClass, jstring jName)
{
    HINSTANCE hModule;
    LPVOID lpMsgBuf;

    const char *libName = (*env)->GetStringUTFChars(env, jName, NULL);
    dprintf1("-lib %s\n", libName);

    hModule = LoadLibrary(libName);
    (*env)->ReleaseStringUTFChars(env, jName, libName);

    if (hModule == NULL) {
        FormatMessage(
            FORMAT_MESSAGE_ALLOCATE_BUFFER |
            FORMAT_MESSAGE_FROM_SYSTEM |
            FORMAT_MESSAGE_IGNORE_INSERTS,
            NULL,
            GetLastError(),
            0, /* Default language */
            (LPTSTR) &lpMsgBuf,
            0,
            NULL
        );
        dprintf1("-error: %s\n", lpMsgBuf);
        p11ThrowIOException(env, (char*)lpMsgBuf);
        LocalFree(lpMsgBuf);
        return 0;
    }
    dprintf2("-handle: %d (0X%X)\n", hModule, hModule);
    return (jlong)hModule;
}
