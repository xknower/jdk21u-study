#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>

#include "colordata.h"
#include "jni.h"

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT extern sgn_ordered_dither_array std_img_oda_red;
JNIEXPORT extern sgn_ordered_dither_array std_img_oda_green;
JNIEXPORT extern sgn_ordered_dither_array std_img_oda_blue;
JNIEXPORT extern int std_odas_computed;

JNIEXPORT void JNICALL
make_dither_arrays(int cmapsize, ColorData *cData);

JNIEXPORT void JNICALL
initInverseGrayLut(int* prgb, int rgbsize, ColorData* cData);

/*
 * state info needed for breadth-first recursion of color cube from
 * initial palette entries within the cube
 */

typedef struct {
    unsigned int depth;
    unsigned int maxDepth;

    unsigned char *usedFlags;
    unsigned int  activeEntries;
    unsigned short *rgb;
    unsigned char *indices;
    unsigned char *iLUT;
} CubeStateInfo;

#define INSERTNEW(state, rgb, index) do {                           \
        if (!state.usedFlags[rgb]) {                                \
            state.usedFlags[rgb] = 1;                               \
            state.iLUT[rgb] = index;                                \
            state.rgb[state.activeEntries] = rgb;                   \
            state.indices[state.activeEntries] = index;             \
            state.activeEntries++;                                  \
        }                                                           \
} while (0);


#define ACTIVATE(code, mask, delta, state, index) do {              \
    if (((rgb & mask) + delta) <= mask) {                           \
        rgb += delta;                                               \
        INSERTNEW(state, rgb, index);                               \
        rgb -= delta;                                               \
    }                                                               \
    if ((rgb & mask) >= delta) {                                    \
        rgb -= delta;                                               \
        INSERTNEW(state, rgb, index);                               \
        rgb += delta;                                               \
    }                                                               \
} while (0);

#ifdef __cplusplus
} /* extern "C" */
#endif
