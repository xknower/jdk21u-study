#include "mlib_image.h"

#include <jni.h>
#include "jni_util.h"

/*
 * Declare library specific JNI_Onload entry if static build
 */
DEF_STATIC_JNI_OnLoad

/***************************************************************/
typedef union {
  mlib_d64 db;
  struct {
#ifdef _LITTLE_ENDIAN
    mlib_s32 int1, int0;
#else
    mlib_s32 int0, int1;
#endif
  } two_int;
} type_union_mlib_d64;

#define DVAIN52 4.503599627370496e15

/***************************************************************/
mlib_s32 mlib_ilogb(mlib_d64 X)
{
  type_union_mlib_d64 arg;
  mlib_s32 n;

  if (X == 0.0)
    return -MLIB_S32_MAX;
  arg.db = X;
  n = arg.two_int.int0 & 0x7ff00000;
  if (n)
    n = (n < 0x7ff00000) ? (n >> 20) - 1023 : MLIB_S32_MAX;
  else {
    arg.db = X * DVAIN52;
    n = ((arg.two_int.int0 & 0x7ff00000) >> 20) - 1075;
  }
  return n;
}

/***************************************************************/
