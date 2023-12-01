#ifndef _AWT_SYSTEM_SCALE_H
#define _AWT_SYSTEM_SCALE_H
#include <windows.h>
#ifdef __cplusplus
extern "C" {
#endif
    void GetScreenDpi(HMONITOR mon, float *dpiX, float *dpiY);
    HMONITOR WINAPI getPrimaryMonitor();
#ifdef __cplusplus
}
#endif
#endif
