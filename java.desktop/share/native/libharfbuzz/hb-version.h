#if !defined(HB_H_IN) && !defined(HB_NO_SINGLE_HEADER_ERROR)
#error "Include <hb.h> instead."
#endif

#ifndef HB_VERSION_H
#define HB_VERSION_H

#include "hb-common.h"

HB_BEGIN_DECLS


/**
 * HB_VERSION_MAJOR:
 *
 * The major component of the library version available at compile-time.
 */
#define HB_VERSION_MAJOR 8
/**
 * HB_VERSION_MINOR:
 *
 * The minor component of the library version available at compile-time.
 */
#define HB_VERSION_MINOR 2
/**
 * HB_VERSION_MICRO:
 *
 * The micro component of the library version available at compile-time.
 */
#define HB_VERSION_MICRO 2

/**
 * HB_VERSION_STRING:
 *
 * A string literal containing the library version available at compile-time.
 */
#define HB_VERSION_STRING "8.2.2"

/**
 * HB_VERSION_ATLEAST:
 * @major: the major component of the version number
 * @minor: the minor component of the version number
 * @micro: the micro component of the version number
 *
 * Tests the library version at compile-time against a minimum value,
 * as three integer components.
 */
#define HB_VERSION_ATLEAST(major,minor,micro) \
        ((major)*10000+(minor)*100+(micro) <= \
         HB_VERSION_MAJOR*10000+HB_VERSION_MINOR*100+HB_VERSION_MICRO)


HB_EXTERN void
hb_version (unsigned int *major,
            unsigned int *minor,
            unsigned int *micro);

HB_EXTERN const char *
hb_version_string (void);

HB_EXTERN hb_bool_t
hb_version_atleast (unsigned int major,
                    unsigned int minor,
                    unsigned int micro);


HB_END_DECLS

#endif /* HB_VERSION_H */
