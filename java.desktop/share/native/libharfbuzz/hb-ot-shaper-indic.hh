#ifndef HB_OT_SHAPER_INDIC_HH
#define HB_OT_SHAPER_INDIC_HH

#include "hb.hh"

#include "hb-ot-shaper-syllabic.hh"


/* Visual positions in a syllable from left to right. */
enum ot_position_t {
  POS_START = 0,

  POS_RA_TO_BECOME_REPH = 1,
  POS_PRE_M = 2,
  POS_PRE_C = 3,

  POS_BASE_C = 4,
  POS_AFTER_MAIN = 5,

  POS_ABOVE_C = 6,

  POS_BEFORE_SUB = 7,
  POS_BELOW_C = 8,
  POS_AFTER_SUB = 9,

  POS_BEFORE_POST = 10,
  POS_POST_C = 11,
  POS_AFTER_POST = 12,

  POS_SMVD = 13,

  POS_END = 14
};


HB_INTERNAL uint16_t
hb_indic_get_categories (hb_codepoint_t u);


#endif /* HB_OT_SHAPER_INDIC_HH */
