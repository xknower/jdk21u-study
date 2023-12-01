#include <exception>
#include <stdexcept>
#include <jni.h>
#include <windows.h>
#include <WinDef.h>

extern "C" {

BOOL WINAPI DllMain(HINSTANCE hinstDll, DWORD fdwReason, LPVOID lpvReserved) {
    return TRUE;
}

// Determine bitness of Win OS
JNIEXPORT jboolean JNICALL
Java_com_sun_java_accessibility_internal_AccessBridge_isSysWow(JNIEnv *env, jobject callingObj) {
    BOOL bIsWow64 = FALSE;
    typedef BOOL (WINAPI *LPFN_ISWOW64PROCESS) (HANDLE, PBOOL);

    LPFN_ISWOW64PROCESS fnIsWow64Process =
        (LPFN_ISWOW64PROCESS)GetProcAddress(GetModuleHandle(TEXT("kernel32")), "IsWow64Process");

    if (fnIsWow64Process != NULL) {
        if (!fnIsWow64Process(GetCurrentProcess(), &bIsWow64)) {
            throw std::runtime_error("fnIsWow64Process() failed");
        }
    }

    return bIsWow64 ? JNI_TRUE : JNI_FALSE;
}

}
