#ifndef MLIB_SYSMATH_H
#define MLIB_SYSMATH_H

#include <math.h>
#ifdef _MSC_VER
#define M_PI            3.14159265358979323846
#define M_1_PI          0.31830988618379067154
#endif /* _MSC_VER */

#define mlib_acos       acos
#define mlib_sin        sin
#define mlib_cos        cos
#define mlib_fabs       fabs
#define mlib_ceil       ceil

#ifdef MLIB_LIBCAFEMATH

#include <stdlib.h>

#define mlib_sqrt       mlib_sqrt_cafe
#define mlib_sinf       sinf
#define mlib_cosf       cosf
void mlib_sincosf (float x, float *s, float *c);
#define mlib_sqrtf      mlib_sqrtf_cafe
#define mlib_fabsf      fabsf

double mlib_sqrt_cafe  (double x);
float  mlib_sqrtf_cafe (float  x);

#else

#define mlib_sqrt       sqrt

#define mlib_sinf       (float) sin
#define mlib_cosf       (float) cos
void mlib_sincosf (float x, float *s, float *c);
#define mlib_sqrtf      (float) sqrt
#define mlib_fabsf      (float) fabs

#endif  /* MLIB_LIBCAFEMATH */


  /* internal mathematical functions */

double mlib_sincospi(double x, double *co);
double mlib_atan2i (int y, int x);
int    mlib_ilogb (double x);

#endif /* MLIB_SYSMATH_H */
