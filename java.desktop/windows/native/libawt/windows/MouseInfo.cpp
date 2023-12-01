#include <windowsx.h>
#include <jni.h>
#include <jni_util.h>
#include "awt.h"
#include "awt_Object.h"
#include "awt_Component.h"

extern "C" {

/*
 * Class:     sun_awt_windows_WMouseInfoPeer
 * Method:    isWindowUnderMouse
 * Signature: (Ljava/awt/Window)Z
 */
JNIEXPORT jboolean JNICALL
Java_sun_awt_windows_WMouseInfoPeer_isWindowUnderMouse(JNIEnv *env, jclass cls,
                                                        jobject window)
{
    POINT pt;

    if (env->EnsureLocalCapacity(1) < 0) {
        return JNI_FALSE;
    }

    jobject winPeer = AwtObject::GetPeerForTarget(env, window);
    CHECK_NULL_RETURN(winPeer, JNI_FALSE);
    PDATA pData;
    pData = JNI_GET_PDATA(winPeer);
    env->DeleteLocalRef(winPeer);
    if (pData == NULL) {
        return JNI_FALSE;
    }

    AwtComponent * ourWindow = (AwtComponent *)pData;
    HWND hwnd = ourWindow->GetHWnd();
    VERIFY(::GetCursorPos(&pt));

    AwtComponent * componentFromPoint = AwtComponent::GetComponent(::WindowFromPoint(pt));

    while (componentFromPoint != NULL
        && componentFromPoint->GetHWnd() != hwnd
        && !AwtComponent::IsTopLevelHWnd(componentFromPoint->GetHWnd()))
    {
        componentFromPoint = componentFromPoint->GetParent();
    }

    return ((componentFromPoint != NULL) && (componentFromPoint->GetHWnd() == hwnd)) ? JNI_TRUE : JNI_FALSE;

}

/*
 * Class:     sun_awt_windows_WMouseInfoPeer
 * Method:    fillPointWithCoords
 * Signature: (Ljava/awt/Point)I
 */
JNIEXPORT jint JNICALL
Java_sun_awt_windows_WMouseInfoPeer_fillPointWithCoords(JNIEnv *env, jclass cls, jobject point)
{
    static jclass pointClass = NULL;
    static jfieldID xID, yID;
    POINT pt;

    VERIFY(::GetCursorPos(&pt));
    if (pointClass == NULL) {
        jclass pointClassLocal = env->FindClass("java/awt/Point");
        DASSERT(pointClassLocal != NULL);
        if (pointClassLocal == NULL) {
            return (jint)0;
        }
        pointClass = (jclass)env->NewGlobalRef(pointClassLocal);
        env->DeleteLocalRef(pointClassLocal);
    }

    HMONITOR monitor = MonitorFromPoint(pt, MONITOR_DEFAULTTOPRIMARY);
    int screen = AwtWin32GraphicsDevice::GetScreenFromHMONITOR(monitor);
    Devices::InstanceAccess devices;
    AwtWin32GraphicsDevice *device = devices->GetDevice(screen);

    xID = env->GetFieldID(pointClass, "x", "I");
    CHECK_NULL_RETURN(xID, (jint)0);
    yID = env->GetFieldID(pointClass, "y", "I");
    CHECK_NULL_RETURN(yID, (jint)0);

    int x = (device == NULL) ? pt.x : device->ScaleDownAbsX(pt.x);
    int y = (device == NULL) ? pt.y : device->ScaleDownAbsY(pt.y);

    env->SetIntField(point, xID, x);
    env->SetIntField(point, yID, y);

    // Always return 0 on Windows: we assume there's always a
    // virtual screen device used.
    return (jint)0;
}

} // extern "C"
