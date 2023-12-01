#ifndef HB_AAT_LAYOUT_HH
#define HB_AAT_LAYOUT_HH

#include "hb.hh"

#include "hb-ot-shape.hh"
#include "hb-aat-ltag-table.hh"

struct hb_aat_feature_mapping_t
{
  hb_tag_t otFeatureTag;
  hb_aat_layout_feature_type_t aatFeatureType;
  hb_aat_layout_feature_selector_t selectorToEnable;
  hb_aat_layout_feature_selector_t selectorToDisable;

  int cmp (hb_tag_t key) const
  { return key < otFeatureTag ? -1 : key > otFeatureTag ? 1 : 0; }
};

HB_INTERNAL const hb_aat_feature_mapping_t *
hb_aat_layout_find_feature_mapping (hb_tag_t tag);

HB_INTERNAL void
hb_aat_layout_compile_map (const hb_aat_map_builder_t *mapper,
                           hb_aat_map_t *map);

HB_INTERNAL void
hb_aat_layout_substitute (const hb_ot_shape_plan_t *plan,
                          hb_font_t *font,
                          hb_buffer_t *buffer,
                          const hb_feature_t *features,
                          unsigned num_features);

HB_INTERNAL void
hb_aat_layout_zero_width_deleted_glyphs (hb_buffer_t *buffer);

HB_INTERNAL void
hb_aat_layout_remove_deleted_glyphs (hb_buffer_t *buffer);

HB_INTERNAL void
hb_aat_layout_position (const hb_ot_shape_plan_t *plan,
                        hb_font_t *font,
                        hb_buffer_t *buffer);

HB_INTERNAL void
hb_aat_layout_track (const hb_ot_shape_plan_t *plan,
                     hb_font_t *font,
                     hb_buffer_t *buffer);


#endif /* HB_AAT_LAYOUT_HH */
