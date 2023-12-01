#ifndef __MLIB_C_IMAGECONV_H
#define __MLIB_C_IMAGECONV_H

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

// Shared macro defined for cleanup of allocated memory.
#ifndef FREE_AND_RETURN_STATUS
#define FREE_AND_RETURN_STATUS \
if (pbuff != buff) mlib_free(pbuff); \
if (k != akernel) mlib_free(k); \
return status
#endif /* FREE_AND_RETURN_STATUS */

mlib_status mlib_c_convMxNnw_u8(mlib_image       *dst,
                                const mlib_image *src,
                                const mlib_s32   *kernel,
                                mlib_s32         m,
                                mlib_s32         n,
                                mlib_s32         dm,
                                mlib_s32         dn,
                                mlib_s32         scale,
                                mlib_s32         cmask);

mlib_status mlib_c_convMxNext_u8(mlib_image       *dst,
                                 const mlib_image *src,
                                 const mlib_s32   *kern,
                                 mlib_s32         m,
                                 mlib_s32         n,
                                 mlib_s32         dx_l,
                                 mlib_s32         dx_r,
                                 mlib_s32         dy_t,
                                 mlib_s32         dy_b,
                                 mlib_s32         scale,
                                 mlib_s32         cmask);

/* for x86, using integer multiplies is faster */

mlib_status mlib_i_convMxNnw_s16(mlib_image       *dst,
                                 const mlib_image *src,
                                 const mlib_s32   *kernel,
                                 mlib_s32         m,
                                 mlib_s32         n,
                                 mlib_s32         dm,
                                 mlib_s32         dn,
                                 mlib_s32         scale,
                                 mlib_s32         cmask);

mlib_status mlib_i_convMxNnw_u16(mlib_image       *dst,
                                 const mlib_image *src,
                                 const mlib_s32   *kernel,
                                 mlib_s32         m,
                                 mlib_s32         n,
                                 mlib_s32         dm,
                                 mlib_s32         dn,
                                 mlib_s32         scale,
                                 mlib_s32         cmask);

mlib_status mlib_i_convMxNnw_u8(mlib_image       *dst,
                                const mlib_image *src,
                                const mlib_s32   *kernel,
                                mlib_s32         m,
                                mlib_s32         n,
                                mlib_s32         dm,
                                mlib_s32         dn,
                                mlib_s32         scale,
                                mlib_s32         cmask);

mlib_status mlib_i_convMxNext_u8(mlib_image       *dst,
                                 const mlib_image *src,
                                 const mlib_s32   *kern,
                                 mlib_s32         m,
                                 mlib_s32         n,
                                 mlib_s32         dx_l,
                                 mlib_s32         dx_r,
                                 mlib_s32         dy_t,
                                 mlib_s32         dy_b,
                                 mlib_s32         scale,
                                 mlib_s32         cmask);

mlib_status mlib_i_convMxNext_s16(mlib_image       *dst,
                                  const mlib_image *src,
                                  const mlib_s32   *kernel,
                                  mlib_s32         m,
                                  mlib_s32         n,
                                  mlib_s32         dx_l,
                                  mlib_s32         dx_r,
                                  mlib_s32         dy_t,
                                  mlib_s32         dy_b,
                                  mlib_s32         scale,
                                  mlib_s32         cmask);

mlib_status mlib_i_convMxNext_u16(mlib_image       *dst,
                                  const mlib_image *src,
                                  const mlib_s32   *kernel,
                                  mlib_s32         m,
                                  mlib_s32         n,
                                  mlib_s32         dx_l,
                                  mlib_s32         dx_r,
                                  mlib_s32         dy_t,
                                  mlib_s32         dy_b,
                                  mlib_s32         scale,
                                  mlib_s32         cmask);

#ifdef __cplusplus
}
#endif /* __cplusplus */
#endif /* __MLIB_C_IMAGECONV_H */
