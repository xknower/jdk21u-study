#include <stdlib.h>
#include <string.h>
#ifdef MACOSX
#include <unistd.h>
#include <sys/param.h>
#else
#include <malloc.h>
#endif
#include <mlib_types.h>
#include <mlib_sys_proto.h>
#include "mlib_SysMath.h"

/***************************************************************/

#if ! defined ( __MEDIALIB_OLD_NAMES )
#if defined ( __GNUC__ )

  __typeof__ ( __mlib_memmove) mlib_memmove
    __attribute__ ((weak,alias("__mlib_memmove")));
  __typeof__ ( __mlib_malloc) mlib_malloc
    __attribute__ ((weak,alias("__mlib_malloc")));
  __typeof__ ( __mlib_realloc) mlib_realloc
    __attribute__ ((weak,alias("__mlib_realloc")));
  __typeof__ ( __mlib_free) mlib_free
    __attribute__ ((weak,alias("__mlib_free")));
  __typeof__ ( __mlib_memset) mlib_memset
    __attribute__ ((weak,alias("__mlib_memset")));
  __typeof__ ( __mlib_memcpy) mlib_memcpy
    __attribute__ ((weak,alias("__mlib_memcpy")));

void __mlib_sincosf (float x, float *s, float *c);

__typeof__ ( __mlib_sincosf) mlib_sincosf
    __attribute__ ((weak,alias("__mlib_sincosf")));

#else /* defined ( __GNUC__ ) */

#error  "unknown platform"

#endif /* defined ( __GNUC__ ) */
#endif /* ! defined ( __MEDIALIB_OLD_NAMES ) */

/***************************************************************/

void *__mlib_malloc(mlib_u32 size)
{
#if defined(_MSC_VER) || defined(AIX)
  /*
   * Currently, all MS C compilers for Win32 platforms default to 8 byte
   * alignment. -- from stdlib.h of MS VC++5.0.
   *
   * On AIX, the malloc subroutine returns a pointer to space suitably
   * aligned for the storage of any type of object (see 'man malloc').
   */
  return (void *) malloc(size);
#elif defined(MACOSX)
  return valloc(size);
#else
  return (void *) memalign(8, size);
#endif /* _MSC_VER */
}

void *__mlib_realloc(void *ptr, mlib_u32 size)
{
  return realloc(ptr, size);
}

void __mlib_free(void *ptr)
{
  free(ptr);
}

void *__mlib_memset(void *s, mlib_s32 c, mlib_u32 n)
{
  return memset(s, c, n);
}

void *__mlib_memcpy(void *s1, void *s2, mlib_u32 n)
{
  return memcpy(s1, s2, n);
}

void *__mlib_memmove(void *s1, void *s2, mlib_u32 n)
{
  return memmove(s1, s2, n);
}

void __mlib_sincosf (mlib_f32 x, mlib_f32 *s, mlib_f32 *c)
{
  *s = (mlib_f32)sin(x);
  *c = (mlib_f32)cos(x);
}
