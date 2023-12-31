#ifndef _AWT_IMAGINGLIB_H_
#define _AWT_IMAGINGLIB_H_

#include "mlib_types.h"
#include "mlib_status.h"
#include "mlib_image_types.h"
#include "mlib_image_get.h"

/* Struct that holds the mlib function ptrs and names */
typedef struct {
    mlib_status (*fptr)();
    char *fname;
} mlibFnS_t;

typedef mlib_image *(*MlibCreateFP_t)(mlib_type, mlib_s32, mlib_s32,
                                       mlib_s32);
typedef mlib_image *(*MlibCreateStructFP_t)(mlib_type, mlib_s32, mlib_s32,
                                             mlib_s32, mlib_s32, const void *);
typedef void (*MlibDeleteFP_t)(mlib_image *);

typedef struct {
    MlibCreateFP_t createFP;
    MlibCreateStructFP_t createStructFP;
    MlibDeleteFP_t deleteImageFP;
} mlibSysFnS_t;

#endif /* _AWT_IMAGINGLIB_H */
