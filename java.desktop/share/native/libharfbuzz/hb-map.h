#if !defined(HB_H_IN) && !defined(HB_NO_SINGLE_HEADER_ERROR)
#error "Include <hb.h> instead."
#endif

#ifndef HB_MAP_H
#define HB_MAP_H

#include "hb-common.h"
#include "hb-set.h"

HB_BEGIN_DECLS


/**
 * HB_MAP_VALUE_INVALID:
 *
 * Unset #hb_map_t value.
 *
 * Since: 1.7.7
 */
#define HB_MAP_VALUE_INVALID HB_CODEPOINT_INVALID

/**
 * hb_map_t:
 *
 * Data type for holding integer-to-integer hash maps.
 *
 **/
typedef struct hb_map_t hb_map_t;


HB_EXTERN hb_map_t *
hb_map_create (void);

HB_EXTERN hb_map_t *
hb_map_get_empty (void);

HB_EXTERN hb_map_t *
hb_map_reference (hb_map_t *map);

HB_EXTERN void
hb_map_destroy (hb_map_t *map);

HB_EXTERN hb_bool_t
hb_map_set_user_data (hb_map_t           *map,
                      hb_user_data_key_t *key,
                      void *              data,
                      hb_destroy_func_t   destroy,
                      hb_bool_t           replace);

HB_EXTERN void *
hb_map_get_user_data (const hb_map_t     *map,
                      hb_user_data_key_t *key);


/* Returns false if allocation has failed before */
HB_EXTERN hb_bool_t
hb_map_allocation_successful (const hb_map_t *map);

HB_EXTERN hb_map_t *
hb_map_copy (const hb_map_t *map);

HB_EXTERN void
hb_map_clear (hb_map_t *map);

HB_EXTERN hb_bool_t
hb_map_is_empty (const hb_map_t *map);

HB_EXTERN unsigned int
hb_map_get_population (const hb_map_t *map);

HB_EXTERN hb_bool_t
hb_map_is_equal (const hb_map_t *map,
                 const hb_map_t *other);

HB_EXTERN unsigned int
hb_map_hash (const hb_map_t *map);

HB_EXTERN void
hb_map_set (hb_map_t       *map,
            hb_codepoint_t  key,
            hb_codepoint_t  value);

HB_EXTERN hb_codepoint_t
hb_map_get (const hb_map_t *map,
            hb_codepoint_t  key);

HB_EXTERN void
hb_map_del (hb_map_t       *map,
            hb_codepoint_t  key);

HB_EXTERN hb_bool_t
hb_map_has (const hb_map_t *map,
            hb_codepoint_t  key);

HB_EXTERN void
hb_map_update (hb_map_t *map,
               const hb_map_t *other);

/* Pass -1 in for idx to get started. */
HB_EXTERN hb_bool_t
hb_map_next (const hb_map_t *map,
             int *idx,
             hb_codepoint_t *key,
             hb_codepoint_t *value);

HB_EXTERN void
hb_map_keys (const hb_map_t *map,
             hb_set_t *keys);

HB_EXTERN void
hb_map_values (const hb_map_t *map,
               hb_set_t *values);

HB_END_DECLS

#endif /* HB_MAP_H */
