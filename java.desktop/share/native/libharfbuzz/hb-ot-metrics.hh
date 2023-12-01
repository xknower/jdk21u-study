#ifndef HB_OT_METRICS_HH
#define HB_OT_METRICS_HH

#include "hb.hh"

HB_INTERNAL bool
_hb_ot_metrics_get_position_common (hb_font_t           *font,
                                    hb_ot_metrics_tag_t  metrics_tag,
                                    hb_position_t       *position     /* OUT.  May be NULL. */);

#endif /* HB_OT_METRICS_HH */
