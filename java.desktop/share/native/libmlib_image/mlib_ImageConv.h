#ifndef __MLIB_IMAGECONV_H
#define __MLIB_IMAGECONV_H

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

mlib_status mlib_convMxNext_s32(mlib_image       *dst,
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

mlib_status mlib_convMxNnw_d64(mlib_image       *dst,
                               const mlib_image *src,
                               const mlib_d64   *ker,
                               mlib_s32         m,
                               mlib_s32         n,
                               mlib_s32         dm,
                               mlib_s32         dn,
                               mlib_s32         cmask);

mlib_status mlib_convMxNnw_f32(mlib_image       *dst,
                               const mlib_image *src,
                               const mlib_d64   *ker,
                               mlib_s32         m,
                               mlib_s32         n,
                               mlib_s32         dm,
                               mlib_s32         dn,
                               mlib_s32         cmask);

mlib_status mlib_convMxNnw_s16(mlib_image       *dst,
                               const mlib_image *src,
                               const mlib_s32   *kernel,
                               mlib_s32         m,
                               mlib_s32         n,
                               mlib_s32         dm,
                               mlib_s32         dn,
                               mlib_s32         scale,
                               mlib_s32         cmask);

mlib_status mlib_convMxNnw_s32(mlib_image       *dst,
                               const mlib_image *src,
                               const mlib_s32   *kernel,
                               mlib_s32         m,
                               mlib_s32         n,
                               mlib_s32         dm,
                               mlib_s32         dn,
                               mlib_s32         scale,
                               mlib_s32         cmask);

mlib_status mlib_convMxNnw_u16(mlib_image       *dst,
                               const mlib_image *src,
                               const mlib_s32   *kernel,
                               mlib_s32         m,
                               mlib_s32         n,
                               mlib_s32         dm,
                               mlib_s32         dn,
                               mlib_s32         scale,
                               mlib_s32         cmask);

mlib_s32 mlib_ImageConvVersion(mlib_s32  m,
                               mlib_s32  n,
                               mlib_s32  scale,
                               mlib_type type);

mlib_status mlib_ImageConvMxN_f(mlib_image       *dst,
                                const mlib_image *src,
                                const void       *kernel,
                                mlib_s32         m,
                                mlib_s32         n,
                                mlib_s32         dm,
                                mlib_s32         dn,
                                mlib_s32         scale,
                                mlib_s32         cmask,
                                mlib_edge        edge);

mlib_status mlib_convMxNnw_u8(mlib_image       *dst,
                              const mlib_image *src,
                              const mlib_s32   *kern,
                              mlib_s32         m,
                              mlib_s32         n,
                              mlib_s32         dm,
                              mlib_s32         dn,
                              mlib_s32         scale,
                              mlib_s32         cmask);

mlib_status mlib_convMxNext_u8(mlib_image       *dst,
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

mlib_status mlib_convMxNext_s16(mlib_image *dst,
                                const mlib_image *src,
                                const mlib_s32 *kernel,
                                mlib_s32 m,
                                mlib_s32 n,
                                mlib_s32 dx_l,
                                mlib_s32 dx_r,
                                mlib_s32 dy_t,
                                mlib_s32 dy_b,
                                mlib_s32 scale,
                                mlib_s32 cmask);

mlib_status mlib_convMxNext_u16(mlib_image       *dst,
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

mlib_status mlib_convMxNext_f32(mlib_image       *dst,
                                const mlib_image *src,
                                const mlib_d64   *kernel,
                                mlib_s32         m,
                                mlib_s32         n,
                                mlib_s32         dx_l,
                                mlib_s32         dx_r,
                                mlib_s32         dy_t,
                                mlib_s32         dy_b,
                                mlib_s32         cmask);

mlib_status mlib_convMxNext_d64(mlib_image       *dst,
                                const mlib_image *src,
                                const mlib_d64   *kernel,
                                mlib_s32         m,
                                mlib_s32         n,
                                mlib_s32         dx_l,
                                mlib_s32         dx_r,
                                mlib_s32         dy_t,
                                mlib_s32         dy_b,
                                mlib_s32         cmask);

#ifdef __cplusplus
}
#endif /* __cplusplus */
#endif /* __MLIB_IMAGECONV_H */
