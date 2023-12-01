#if !defined(HB_H_IN) && !defined(HB_NO_SINGLE_HEADER_ERROR)
#error "Include <hb.h> instead."
#endif

#ifndef HB_SHAPE_H
#define HB_SHAPE_H

#include "hb-common.h"
#include "hb-buffer.h"
#include "hb-font.h"

HB_BEGIN_DECLS


HB_EXTERN void
hb_shape (hb_font_t           *font,
          hb_buffer_t         *buffer,
          const hb_feature_t  *features,
          unsigned int         num_features);

HB_EXTERN hb_bool_t
hb_shape_full (hb_font_t          *font,
               hb_buffer_t        *buffer,
               const hb_feature_t *features,
               unsigned int        num_features,
               const char * const *shaper_list);

HB_EXTERN hb_bool_t
hb_shape_justify (hb_font_t          *font,
                  hb_buffer_t        *buffer,
                  const hb_feature_t *features,
                  unsigned int        num_features,
                  const char * const *shaper_list,
                  float               min_target_advance,
                  float               max_target_advance,
                  float              *advance, /* IN/OUT */
                  hb_tag_t           *var_tag, /* OUT */
                  float              *var_value /* OUT */);

HB_EXTERN const char **
hb_shape_list_shapers (void);


HB_END_DECLS

#endif /* HB_SHAPE_H */
