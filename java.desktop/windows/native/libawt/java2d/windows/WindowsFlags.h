#ifndef WINDOWSFLAGS_H
#define WINDOWSFLAGS_H

extern BOOL      useD3D;             // d3d enabled flag
extern BOOL      forceD3DUsage;      // force d3d on or off
extern jboolean  g_offscreenSharing; // JAWT accelerated surface sharing
extern BOOL      setHighDPIAware;    // whether to set High DPI Aware flag on Vista

void SetD3DEnabledFlag(JNIEnv *env, BOOL d3dEnabled, BOOL d3dSet);

BOOL IsD3DEnabled();
BOOL IsD3DForced();

#endif // WINDOWSFLAGS_H
