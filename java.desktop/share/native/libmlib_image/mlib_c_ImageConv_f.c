#include "mlib_image.h"
#include "mlib_ImageConv.h"
#include "mlib_c_ImageConv.h"

/***************************************************************/
#define MLIB_PARAMS_CONV_MN_NW                                  \
  mlib_image *dst,                                              \
  const mlib_image *src,                                        \
  const mlib_s32   *kern,                                       \
  mlib_s32         m,                                           \
  mlib_s32         n,                                           \
  mlib_s32         dm,                                          \
  mlib_s32         dn,                                          \
  mlib_s32         scale,                                       \
  mlib_s32         cmask

/***************************************************************/
#define MLIB_CALL_PARAMS_CONV_MN_NW                             \
  dst, src, kern, m, n, dm, dn, scale, cmask

/***************************************************************/
#define MLIB_PARAMS_CONV_MN_EXT                                 \
  mlib_image       *dst,                                        \
  const mlib_image *src,                                        \
  const mlib_s32   *kern,                                       \
  mlib_s32         m,                                           \
  mlib_s32         n,                                           \
  mlib_s32         dx_l,                                        \
  mlib_s32         dx_r,                                        \
  mlib_s32         dy_t,                                        \
  mlib_s32         dy_b,                                        \
  mlib_s32         scale,                                       \
  mlib_s32         cmask

/***************************************************************/
#define MLIB_CALL_PARAMS_CONV_MN_EXT                            \
  dst, src, kern, m, n, dx_l, dx_r, dy_t, dy_b, scale, cmask


/***************************************************************/
mlib_status mlib_convMxNnw_u8(MLIB_PARAMS_CONV_MN_NW)
{
  if (mlib_ImageConvVersion(m, n, scale, MLIB_BYTE) == 0)
    return mlib_c_convMxNnw_u8(MLIB_CALL_PARAMS_CONV_MN_NW);
  else
    return mlib_i_convMxNnw_u8(MLIB_CALL_PARAMS_CONV_MN_NW);
}

/***************************************************************/
mlib_status mlib_convMxNext_u8(MLIB_PARAMS_CONV_MN_EXT)
{
  if (mlib_ImageConvVersion(m, n, scale, MLIB_BYTE) == 0)
    return mlib_c_convMxNext_u8(MLIB_CALL_PARAMS_CONV_MN_EXT);
  else
    return mlib_i_convMxNext_u8(MLIB_CALL_PARAMS_CONV_MN_EXT);
}

/***************************************************************/
