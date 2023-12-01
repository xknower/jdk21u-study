#ifndef HB_OT_SHAPER_SYLLABIC_HH
#define HB_OT_SHAPER_SYLLABIC_HH

#include "hb.hh"

#include "hb-ot-shaper.hh"


HB_INTERNAL bool
hb_syllabic_insert_dotted_circles (hb_font_t *font,
                                   hb_buffer_t *buffer,
                                   unsigned int broken_syllable_type,
                                   unsigned int dottedcircle_category,
                                   int repha_category = -1,
                                   int dottedcircle_position = -1);

HB_INTERNAL bool
hb_syllabic_clear_var (const hb_ot_shape_plan_t *plan,
                       hb_font_t *font,
                       hb_buffer_t *buffer);


#endif /* HB_OT_SHAPER_SYLLABIC_HH */
