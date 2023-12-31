#include "hb.hh"

#ifndef HB_NO_OT_SHAPE

#include "hb-ot-shaper.hh"


const hb_ot_shaper_t _hb_ot_shaper_default =
{
  nullptr, /* collect_features */
  nullptr, /* override_features */
  nullptr, /* data_create */
  nullptr, /* data_destroy */
  nullptr, /* preprocess_text */
  nullptr, /* postprocess_glyphs */
  nullptr, /* decompose */
  nullptr, /* compose */
  nullptr, /* setup_masks */
  nullptr, /* reorder_marks */
  HB_TAG_NONE, /* gpos_tag */
  HB_OT_SHAPE_NORMALIZATION_MODE_DEFAULT,
  HB_OT_SHAPE_ZERO_WIDTH_MARKS_BY_GDEF_LATE,
  true, /* fallback_position */
};

#ifndef HB_NO_AAT_SHAPE
/* Same as default but no mark advance zeroing / fallback positioning.
 * Dumbest shaper ever, basically. */
const hb_ot_shaper_t _hb_ot_shaper_dumber =
{
  nullptr, /* collect_features */
  nullptr, /* override_features */
  nullptr, /* data_create */
  nullptr, /* data_destroy */
  nullptr, /* preprocess_text */
  nullptr, /* postprocess_glyphs */
  nullptr, /* decompose */
  nullptr, /* compose */
  nullptr, /* setup_masks */
  nullptr, /* reorder_marks */
  HB_TAG_NONE, /* gpos_tag */
  HB_OT_SHAPE_NORMALIZATION_MODE_DEFAULT,
  HB_OT_SHAPE_ZERO_WIDTH_MARKS_NONE,
  false, /* fallback_position */
};
#endif


#endif
