/*
 * FUNCTION
 *      mlib_ImageConvVersion - Get Conv* functions version
 *      0  - "C" version
 *      1  - "VIS" version
 *      2  - "i386" version
 *      3  - "MMX" version
 *
 * SYNOPSIS
 *      mlib_s32 mlib_ImageConvVersion(mlib_s32 m,
 *                                     mlib_s32 n,
 *                                     mlib_s32 scale,
 *                                     mlib_type type)
 *
 */

#include "mlib_image.h"

#define MAX_U8   8
#define MAX_S16 32

/***************************************************************/
mlib_s32 mlib_ImageConvVersion(mlib_s32 m,
                               mlib_s32 n,
                               mlib_s32 scale,
                               mlib_type type)
{
  mlib_d64 dscale = 1.0 / (1 << scale); /* 16 < scale <= 31 */

  if (type == MLIB_BYTE) {
    if ((m * n * dscale * 32768.0) > MAX_U8)
      return 0;
    return 2;
  }
  else if ((type == MLIB_USHORT) || (type == MLIB_SHORT)) {

    if ((m * n * dscale * 32768.0 * 32768.0) > MAX_S16)
      return 0;
    return 2;
  }
  else
    return 0;
}

/***************************************************************/
