#include "jni_util.h"
#include "awt.h"
#include <jni.h>
#include "awt_Taskbar.h"
#include "awt_Window.h"


#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:     sun_awt_windows_WTaskbarPeer
 * Method:    nativeInit
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_sun_awt_windows_WTaskbarPeer_nativeInit
  (JNIEnv *env, jclass)
{
    if (SUCCEEDED(::CoCreateInstance(CLSID_TaskbarList, NULL,
            CLSCTX_INPROC_SERVER, IID_ITaskbarList, (LPVOID *)&m_Taskbar))) {
        return JNI_TRUE;
    } else {
        return JNI_FALSE;
    }
}

/*
 * Class:     sun_awt_windows_WTaskbarPeer
 * Method:    setProgressValue
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL Java_sun_awt_windows_WTaskbarPeer_setProgressValue
  (JNIEnv *, jobject, jlong window, jint value)
{
    if (value < 0 || value > 100) {
        m_Taskbar->SetProgressState((HWND)window, TBPF_NOPROGRESS);
    } else {
        m_Taskbar->SetProgressValue((HWND)window, value, 100);
    }
}



/*
 * Class:     sun_awt_windows_WTaskbarPeer
 * Method:    setProgressState
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL Java_sun_awt_windows_WTaskbarPeer_setProgressState
  (JNIEnv *env, jobject, jlong window, jobject state)
{
    TBPFLAG flag = TBPF_NOPROGRESS;

    static jmethodID nameMID = NULL;
    if (!nameMID) {
        jclass stateCls = env->FindClass("java/awt/Taskbar$State");
        CHECK_NULL(stateCls);
        nameMID = env->GetMethodID(stateCls, "name", "()Ljava/lang/String;");
        CHECK_NULL(nameMID);
    }
    jstring value = (jstring) env->CallObjectMethod(state, nameMID);
    CHECK_NULL(value);
    const char* valueNative = env->GetStringUTFChars(value, 0);
    if (valueNative) {
        if (strcmp(valueNative, "OFF") == 0) {
            flag = TBPF_NOPROGRESS;
        } else if (strcmp(valueNative, "NORMAL") == 0) {
            flag = TBPF_NORMAL;

            // Switching from TBPF_INDETERMINATE to TBPF_NORMAL has no effect
            m_Taskbar->SetProgressState((HWND)window, TBPF_PAUSED);
        } else if (strcmp(valueNative, "PAUSED") == 0) {
            flag = TBPF_PAUSED;
        } else if (strcmp(valueNative, "INDETERMINATE") == 0) {
            flag = TBPF_INDETERMINATE;
        } else if (strcmp(valueNative, "ERROR") == 0) {
            flag = TBPF_ERROR;
        }
        env->ReleaseStringUTFChars(value, valueNative);
        m_Taskbar->SetProgressState((HWND)window, flag);
    }
}

/*
 * Class:     sun_awt_windows_WTaskbarPeer
 * Method:    flashWindow
 * Signature: (JZ)V
 */
JNIEXPORT void JNICALL Java_sun_awt_windows_WTaskbarPeer_flashWindow
  (JNIEnv *, jobject, jlong window)
{
    ::FlashWindow((HWND) window, TRUE);
}

/*
 * Class:     sun_awt_windows_WTaskbarPeer
 * Method:    setOverlayIcon
 * Signature: (J[III)V
 */
JNIEXPORT void JNICALL Java_sun_awt_windows_WTaskbarPeer_setOverlayIcon
  (JNIEnv *env, jobject, jlong window, jintArray buf, jint w, jint h)
{
    HICON icon = CreateIconFromRaster(env, buf, w, h);
    m_Taskbar->SetOverlayIcon((HWND)window, icon, NULL);
    ::DestroyIcon(icon);
}
#ifdef __cplusplus
}
#endif
