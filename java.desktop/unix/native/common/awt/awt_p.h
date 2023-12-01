/*
 * Motif-specific data structures for AWT Java objects.
 *
 */
#ifndef _AWT_P_H_
#define _AWT_P_H_

#include <stdarg.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#ifndef HEADLESS
#include <X11/extensions/Xrender.h>
#endif /* !HEADLESS */
#include "awt.h"
#include "awt_util.h"
#include "color.h"
#include "colordata.h"
#include "gdefs.h"

#ifndef HEADLESS
#ifndef min
#define min(a,b) ((a) <= (b)? (a):(b))
#endif
#ifndef max
#define max(a,b) ((a) >= (b)? (a):(b))
#endif
#endif /* !HEADLESS */

#define LOOKUPSIZE 32

#ifndef HEADLESS

typedef XRenderPictFormat *
XRenderFindVisualFormatFunc (Display *dpy, _Xconst Visual *visual);

typedef struct _AwtGraphicsConfigData  {
    int         awt_depth;
    Colormap    awt_cmap;
    XVisualInfo awt_visInfo;
    int         awt_num_colors;
    awtImageData *awtImage;
    int         (*AwtColorMatch)(int, int, int,
                                 struct _AwtGraphicsConfigData *);
    XImage      *monoImage;
    Pixmap      monoPixmap;      /* Used in X11TextRenderer_md.c */
    int         monoPixmapWidth; /* Used in X11TextRenderer_md.c */
    int         monoPixmapHeight;/* Used in X11TextRenderer_md.c */
    GC          monoPixmapGC;    /* Used in X11TextRenderer_md.c */
    int         pixelStride;     /* Used in X11SurfaceData.c */
    ColorData      *color_data;
    struct _GLXGraphicsConfigInfo *glxInfo;
    int         isTranslucencySupported; /* Uses Xrender to find this out. */
    XRenderPictFormat renderPictFormat; /*Used only if translucency supported*/
} AwtGraphicsConfigData;

typedef AwtGraphicsConfigData* AwtGraphicsConfigDataPtr;

typedef struct _AwtScreenData {
    int numConfigs;
    Window root;
    unsigned long whitepixel;
    unsigned long blackpixel;
    AwtGraphicsConfigDataPtr defaultConfig;
    AwtGraphicsConfigDataPtr *configs;
} AwtScreenData;

typedef AwtScreenData* AwtScreenDataPtr;

extern AwtGraphicsConfigDataPtr getDefaultConfig(int screen);
#endif /* !HEADLESS */

/* allocated and initialize a structure */
#define ZALLOC(T)       ((struct T *)calloc(1, sizeof(struct T)))

#ifndef HEADLESS

extern int awt_allocate_colors(AwtGraphicsConfigDataPtr);
extern void awt_allocate_systemrgbcolors(jint *, int, AwtGraphicsConfigDataPtr);

extern jobject awtJNI_GetColorModel(JNIEnv *, AwtGraphicsConfigDataPtr);
extern void awtJNI_CreateColorData (JNIEnv *, AwtGraphicsConfigDataPtr, int lock);

#endif /* !HEADLESS */
#endif           /* _AWT_P_H_ */
