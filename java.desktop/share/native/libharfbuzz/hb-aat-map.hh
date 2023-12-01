#ifndef HB_AAT_MAP_HH
#define HB_AAT_MAP_HH

#include "hb.hh"


struct hb_aat_map_t
{
  friend struct hb_aat_map_builder_t;

  public:
  struct range_flags_t
  {
    hb_mask_t flags;
    unsigned cluster_first;
    unsigned cluster_last; // end - 1
  };

  public:
  hb_vector_t<hb_sorted_vector_t<range_flags_t>> chain_flags;
};

struct hb_aat_map_builder_t
{
  public:

  HB_INTERNAL hb_aat_map_builder_t (hb_face_t *face_,
                                    const hb_segment_properties_t props_) :
                                      face (face_),
                                      props (props_) {}

  HB_INTERNAL void add_feature (const hb_feature_t &feature);

  HB_INTERNAL void compile (hb_aat_map_t  &m);

  public:
  struct feature_info_t
  {
    hb_aat_layout_feature_type_t  type;
    hb_aat_layout_feature_selector_t  setting;
    bool is_exclusive;
    unsigned  seq; /* For stable sorting only. */

    HB_INTERNAL static int cmp (const void *pa, const void *pb)
    {
      const feature_info_t *a = (const feature_info_t *) pa;
      const feature_info_t *b = (const feature_info_t *) pb;
      if (a->type != b->type) return (a->type < b->type ? -1 : 1);
      if (!a->is_exclusive &&
          (a->setting & ~1) != (b->setting & ~1)) return (a->setting < b->setting ? -1 : 1);
            return (a->seq < b->seq ? -1 : a->seq > b->seq ? 1 : 0);
    }

    /* compares type & setting only */
    int cmp (const feature_info_t& f) const
    {
      return (f.type != type) ? (f.type < type ? -1 : 1) :
             (f.setting != setting) ? (f.setting < setting ? -1 : 1) : 0;
    }
  };

  struct feature_range_t
  {
    feature_info_t info;
    unsigned start;
    unsigned end;
  };

  private:
  struct feature_event_t
  {
    unsigned int index;
    bool start;
    feature_info_t feature;

    HB_INTERNAL static int cmp (const void *pa, const void *pb) {
      const feature_event_t *a = (const feature_event_t *) pa;
      const feature_event_t *b = (const feature_event_t *) pb;
      return a->index < b->index ? -1 : a->index > b->index ? 1 :
             a->start < b->start ? -1 : a->start > b->start ? 1 :
             feature_info_t::cmp (&a->feature, &b->feature);
    }
  };

  public:
  hb_face_t *face;
  hb_segment_properties_t props;

  public:
  hb_sorted_vector_t<feature_range_t> features;
  hb_sorted_vector_t<feature_info_t> current_features;
  unsigned range_first = HB_FEATURE_GLOBAL_START;
  unsigned range_last = HB_FEATURE_GLOBAL_END;
};


#endif /* HB_AAT_MAP_HH */
