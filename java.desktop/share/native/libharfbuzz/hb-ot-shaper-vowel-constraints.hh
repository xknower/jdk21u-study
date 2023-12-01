#ifndef HB_OT_SHAPER_VOWEL_CONSTRAINTS_HH
#define HB_OT_SHAPER_VOWEL_CONSTRAINTS_HH

#include "hb.hh"

#include "hb-ot-shaper.hh"

HB_INTERNAL void
_hb_preprocess_text_vowel_constraints (const hb_ot_shape_plan_t *plan,
                                       hb_buffer_t              *buffer,
                                       hb_font_t                *font);

#endif /* HB_OT_SHAPER_VOWEL_CONSTRAINTS_HH */
