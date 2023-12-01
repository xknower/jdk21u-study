#ifndef __SAFE_ALLOC_H__
#define __SAFE_ALLOC_H__

#include "j2d_md.h"

/*
 * Macros defined below are wrappers for alloc functions
 * that perform buffer size calculation with integer overflow
 * check.
 */
#define SAFE_TO_ALLOC_2(c, sz)                                             \
    (((c) > 0) && ((sz) > 0) &&                                            \
     ((0x7fffffffu / (c)) > (sz)))

#define SAFE_TO_ALLOC_3(w, h, sz)                                          \
    (((w) > 0) && ((h) > 0) && ((sz) > 0) &&                               \
     (((0x7fffffffu / (w)) / (h)) > (sz)))

#endif // __SAFE_ALLOC_H__
