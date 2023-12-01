#ifndef HB_OT_SHAPER_ARABIC_HH
#define HB_OT_SHAPER_ARABIC_HH

#include "hb.hh"

#include "hb-ot-shaper.hh"


struct arabic_shape_plan_t;

HB_INTERNAL void *
data_create_arabic (const hb_ot_shape_plan_t *plan);

HB_INTERNAL void
data_destroy_arabic (void *data);

HB_INTERNAL void
setup_masks_arabic_plan (const arabic_shape_plan_t *arabic_plan,
                         hb_buffer_t               *buffer,
                         hb_script_t                script);

#endif /* HB_OT_SHAPER_ARABIC_HH */
