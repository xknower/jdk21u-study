/*
 * Header file to contain porting-relevant code which does not have a
 * home anywhere else.
 * This is initially based on hotspot/src/os/aix/vm/{loadlib,porting}_aix.{hpp,cpp}
 */

/*
 * Aix' own version of dladdr().
 * This function tries to mimic dladdr(3) on Linux
 * (see http://linux.die.net/man/3/dladdr)
 * dladdr(3) is not POSIX but a GNU extension, and is not available on AIX.
 *
 * Differences between AIX dladdr and Linux dladdr:
 *
 * 1) Dl_info.dli_fbase: can never work, is disabled.
 *   A loaded image on AIX is divided in multiple segments, at least two
 *   (text and data) but potentially also far more. This is because the loader may
 *   load each member into an own segment, as for instance happens with the libC.a
 * 2) Dl_info.dli_sname: This only works for code symbols (functions); for data, a
 *   zero-length string is returned ("").
 * 3) Dl_info.dli_saddr: For code, this will return the entry point of the function,
 *   not the function descriptor.
 */

typedef struct {
  const char *dli_fname; /* file path of loaded library */
  void *dli_fbase;       /* doesn't make sense on AIX */
  const char *dli_sname; /* symbol name; "" if not known */
  void *dli_saddr;       /* address of *entry* of function; not function descriptor; */
} Dl_info;

#ifdef __cplusplus
extern "C"
#endif
int dladdr(void *addr, Dl_info *info);
