#include "systemScale.h"
#include <jdk_util.h>
#ifndef MDT_EFFECTIVE_DPI
#define MDT_EFFECTIVE_DPI 0
#endif

void GetScreenDpi(HMONITOR hmon, float *dpiX, float *dpiY)
{
    unsigned x = 0;
    unsigned y = 0;

    // for debug purposes
    static float scale = -2.0f;
    if (scale == -2) {
        scale = -1;
        char *uiScale = getenv("J2D_UISCALE");
        if (uiScale != NULL) {
            scale = (float)strtod(uiScale, NULL);
            if (errno == ERANGE || scale <= 0) {
                scale = -1;
            }
        }
    }

    if (scale > 0) {
        *dpiX = *dpiY = scale;
        return;
    }

    typedef HRESULT(WINAPI GetDpiForMonitorFunc)(HMONITOR, int, UINT*, UINT*);
    static HMODULE hLibSHCoreDll = NULL;
    static GetDpiForMonitorFunc *lpGetDpiForMonitor = NULL;

    if (hLibSHCoreDll == NULL) {
        hLibSHCoreDll = JDK_LoadSystemLibrary("shcore.dll");
        if (hLibSHCoreDll != NULL) {
            lpGetDpiForMonitor = (GetDpiForMonitorFunc*)GetProcAddress(
                hLibSHCoreDll, "GetDpiForMonitor");
        }
    }

    if (lpGetDpiForMonitor != NULL) {
        HRESULT hResult = lpGetDpiForMonitor(hmon,
                                             MDT_EFFECTIVE_DPI, &x, &y);
        if (hResult == S_OK) {
            *dpiX = static_cast<float>(x);
            *dpiY = static_cast<float>(y);
        }
    } else {
        HDC hdc = GetDC(NULL);
        if (hdc) {
            *dpiX = static_cast<float>(GetDeviceCaps(hdc, LOGPIXELSX));
            *dpiY = static_cast<float>(GetDeviceCaps(hdc, LOGPIXELSY));
            ReleaseDC(NULL, hdc);
        }
    }
    return;
}

HMONITOR WINAPI getPrimaryMonitor()
{
    const POINT point = { 0, 0 };
    return MonitorFromPoint(point, MONITOR_DEFAULTTOPRIMARY);
}
