#ifndef __RENDEROPTIONS_H
#define __RENDEROPTIONS_H

#include <jni.h>
#include "MTLSurfaceDataBase.h"

// Utility struct to transfer rendering parameters
typedef struct {
    jboolean isTexture;
    jboolean isAA;
    int interpolation;
    SurfaceRasterFlags srcFlags;
    jboolean isText;
    jboolean isLCD;
    jboolean isAAShader;
} RenderOptions;


#endif //__RENDEROPTIONS_H
