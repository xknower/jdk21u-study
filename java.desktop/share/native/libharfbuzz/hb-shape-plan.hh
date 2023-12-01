#ifndef HB_SHAPE_PLAN_HH
#define HB_SHAPE_PLAN_HH

#include "hb.hh"
#include "hb-shaper.hh"
#include "hb-ot-shape.hh"


struct hb_shape_plan_key_t
{
  hb_segment_properties_t  props;

  const hb_feature_t      *user_features;
  unsigned int             num_user_features;

#ifndef HB_NO_OT_SHAPE
  hb_ot_shape_plan_key_t   ot;
#endif

  hb_shape_func_t         *shaper_func;
  const char              *shaper_name;

  HB_INTERNAL bool init (bool                           copy,
                         hb_face_t                     *face,
                         const hb_segment_properties_t *props,
                         const hb_feature_t            *user_features,
                         unsigned int                   num_user_features,
                         const int                     *coords,
                         unsigned int                   num_coords,
                         const char * const            *shaper_list);

  HB_INTERNAL void fini () { hb_free ((void *) user_features); user_features = nullptr; }

  HB_INTERNAL bool user_features_match (const hb_shape_plan_key_t *other);

  HB_INTERNAL bool equal (const hb_shape_plan_key_t *other);
};

struct hb_shape_plan_t
{
  ~hb_shape_plan_t () { key.fini (); }
  hb_object_header_t header;
  hb_face_t *face_unsafe; /* We don't carry a reference to face. */
  hb_shape_plan_key_t key;
#ifndef HB_NO_OT_SHAPE
  hb_ot_shape_plan_t ot;
#endif
};


#endif /* HB_SHAPE_PLAN_HH */
