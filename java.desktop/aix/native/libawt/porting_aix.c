#include <stdio.h>
#include <string.h>
#include <sys/ldr.h>
#include <errno.h>

#include "porting_aix.h"

static unsigned char dladdr_buffer[0x8000];

static void fill_dll_info(void) {
  int rc = loadquery(L_GETINFO,dladdr_buffer, sizeof(dladdr_buffer));
  if (rc == -1) {
    fprintf(stderr, "loadquery failed (%d %s)", errno, strerror(errno));
    fflush(stderr);
  }
}

static int dladdr_dont_reload(void* addr, Dl_info* info) {
  const struct ld_info* p = (struct ld_info*) dladdr_buffer;
  memset((void *)info, 0, sizeof(Dl_info));
  for (;;) {
    if (addr >= p->ldinfo_textorg &&
        (char*)addr < (char*)(p->ldinfo_textorg) + p->ldinfo_textsize) {
      info->dli_fname = p->ldinfo_filename;
      info->dli_fbase = p->ldinfo_textorg;
      return 1; /* [sic] */
    }
    if (!p->ldinfo_next) {
      break;
    }
    p = (struct ld_info*)(((char*)p) + p->ldinfo_next);
  }
  return 0; /* [sic] */
}

#ifdef __cplusplus
extern "C"
#endif
int dladdr(void *addr, Dl_info *info) {
  static int loaded = 0;
  if (!loaded) {
    fill_dll_info();
    loaded = 1;
  }
  if (!addr) {
    return 0;  /* [sic] */
  }
  /* Address could be AIX function descriptor? */
  void* const addr0 = *( (void**) addr );
  int rc = dladdr_dont_reload(addr, info);
  if (rc == 0) {
    rc = dladdr_dont_reload(addr0, info);
    if (rc == 0) { /* [sic] */
      fill_dll_info(); /* refill, maybe loadquery info is outdated */
      rc = dladdr_dont_reload(addr, info);
      if (rc == 0) {
        rc = dladdr_dont_reload(addr0, info);
      }
    }
  }
  return rc;
}
