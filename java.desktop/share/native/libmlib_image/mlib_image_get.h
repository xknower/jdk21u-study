#ifndef __MLIB_IMAGE_GET_H
#define __MLIB_IMAGE_GET_H

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */


static inline mlib_type mlib_ImageGetType(const mlib_image *img)
{
  return img->type;
}

static inline mlib_s32 mlib_ImageGetChannels(const mlib_image *img)
{
  return img->channels;
}

static inline mlib_s32 mlib_ImageGetWidth(const mlib_image *img)
{
  return img->width;
}

static inline mlib_s32 mlib_ImageGetHeight(const mlib_image *img)
{
  return img->height;
}

static inline mlib_s32 mlib_ImageGetStride(const mlib_image *img)
{
  return img->stride;
}

static inline void *mlib_ImageGetData(const mlib_image *img)
{
  return img->data;
}

static inline mlib_s32 mlib_ImageGetFlags(const mlib_image *img)
{
  return img->flags;
}

static inline mlib_u8 *mlib_ImageGetPaddings(const mlib_image *img)
{
  return (mlib_u8 *)img->paddings;
}

static inline mlib_s32 mlib_ImageGetBitOffset(const mlib_image *img)
{
  return img->bitoffset;
}

static inline mlib_format mlib_ImageGetFormat(const mlib_image *img)
{
  return img->format;
}

/* returns 0 if all conditions are satisfied, non-zero otherwise */
static inline int mlib_ImageTestFlags(const mlib_image *img, mlib_s32 flags)
{
  return (img->flags & flags);
}

/* returns 0 if 64 byte aligned and non-zero if not aligned */
static inline int mlib_ImageIsNotAligned64(const mlib_image *img)
{
  return (img->flags & MLIB_IMAGE_ALIGNED64);
}

/* returns 0 if 8 byte aligned and non-zero if not aligned */
static inline int mlib_ImageIsNotAligned8(const mlib_image *img)
{
  return (img->flags & MLIB_IMAGE_ALIGNED8);
}

/* returns 0 if 4 byte aligned and non-zero if not aligned */
static inline int mlib_ImageIsNotAligned4(const mlib_image *img)
{
  return (img->flags & MLIB_IMAGE_ALIGNED4);
}

/* returns 0 if 2 byte aligned and non-zero if not aligned */
static inline int mlib_ImageIsNotAligned2(const mlib_image *img)
{
  return (img->flags & MLIB_IMAGE_ALIGNED2);
}

/* returns 0 if width is a multiple of 8, non-zero otherwise */
static inline int mlib_ImageIsNotWidth8X(const mlib_image *img)
{
  return (img->flags & MLIB_IMAGE_WIDTH8X);
}

/* returns 0 if width is a multiple of 4, non-zero otherwise */
static inline int mlib_ImageIsNotWidth4X(const mlib_image *img)
{
  return (img->flags & MLIB_IMAGE_WIDTH4X);
}

/* returns 0 if width is a multiple of 2, non-zero otherwise */
static inline int mlib_ImageIsNotWidth2X(const mlib_image *img)
{
  return (img->flags & MLIB_IMAGE_WIDTH2X);
}

/* returns 0 if height is a multiple of 8, non-zero otherwise */
static inline int mlib_ImageIsNotHeight8X(const mlib_image *img)
{
  return (img->flags & MLIB_IMAGE_HEIGHT8X);
}

/* returns 0 if height is a multiple of 4, non-zero otherwise */
static inline int mlib_ImageIsNotHeight4X(const mlib_image *img)
{
  return (img->flags & MLIB_IMAGE_HEIGHT4X);
}

/* returns 0 if height is a multiple of 2, non-zero otherwise */
static inline int mlib_ImageIsNotHeight2X(const mlib_image *img)
{
  return (img->flags & MLIB_IMAGE_HEIGHT2X);
}

/* returns 0 if stride is a multiple of 8, non-zero otherwise */
static inline int mlib_ImageIsNotStride8X(const mlib_image *img)
{
  return (img->flags & MLIB_IMAGE_STRIDE8X);
}

/* returns 0 if it can be treated as a 1-D vector, non-zero otherwise */
static inline int mlib_ImageIsNotOneDvector(const mlib_image *img)
{
  return (img->flags & MLIB_IMAGE_ONEDVECTOR);
}

/* returns non-zero if data buffer is user allocated, 0 otherwise */
static inline int mlib_ImageIsUserAllocated(const mlib_image *img)
{
  return (img->flags & MLIB_IMAGE_USERALLOCATED);
}

#ifdef __cplusplus
}
#endif /* __cplusplus */
#endif /* __MLIB_IMAGE_GET_H */
