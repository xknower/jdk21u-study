#ifndef __MLIB_IMAGEFILTERS_H
#define __MLIB_IMAGEFILTERS_H

#include "mlib_image.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 *    These tables are used by C and VIS versions
 *    of the following functions:
 *      mlib_ImageRotate(Index)
 *      mlib_ImageAffine(Index)
 *      mlib_ImageZoom(Index)
 *      mlib_ImageGridWarp
 *      mlib_ImagePolynomialWarp
 */

extern const mlib_f32 mlib_filters_u8f_bc[];
extern const mlib_f32 mlib_filters_u8f_bc2[];
extern const mlib_f32 mlib_filters_s16f_bc[];
extern const mlib_f32 mlib_filters_s16f_bc2[];

extern const mlib_s16 mlib_filters_u8_bc[];
extern const mlib_s16 mlib_filters_u8_bc2[];
extern const mlib_s16 mlib_filters_s16_bc[];
extern const mlib_s16 mlib_filters_s16_bc2[];

#ifdef __cplusplus
}
#endif /* __cplusplus */
#endif /* __MLIB_IMAGEFILTERS_H */
