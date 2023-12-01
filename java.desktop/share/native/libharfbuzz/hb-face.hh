#ifndef HB_FACE_HH
#define HB_FACE_HH

#include "hb.hh"

#include "hb-shaper.hh"
#include "hb-shape-plan.hh"
#include "hb-ot-face.hh"


/*
 * hb_face_t
 */

#define HB_SHAPER_IMPLEMENT(shaper) HB_SHAPER_DATA_INSTANTIATE_SHAPERS(shaper, face);
#include "hb-shaper-list.hh"
#undef HB_SHAPER_IMPLEMENT

struct hb_face_t
{
  hb_object_header_t header;

  hb_reference_table_func_t  reference_table_func;
  void                      *user_data;
  hb_destroy_func_t          destroy;

  unsigned int index;                   /* Face index in a collection, zero-based. */
  mutable hb_atomic_int_t upem;         /* Units-per-EM. */
  mutable hb_atomic_int_t num_glyphs;   /* Number of glyphs. */

  hb_shaper_object_dataset_t<hb_face_t> data;/* Various shaper data. */
  hb_ot_face_t table;                   /* All the face's tables. */

  /* Cache */
  struct plan_node_t
  {
    hb_shape_plan_t *shape_plan;
    plan_node_t *next;
  };
#ifndef HB_NO_SHAPER
  hb_atomic_ptr_t<plan_node_t> shape_plans;
#endif

  hb_blob_t *reference_table (hb_tag_t tag) const
  {
    hb_blob_t *blob;

    if (unlikely (!reference_table_func))
      return hb_blob_get_empty ();

    blob = reference_table_func (/*Oh, well.*/const_cast<hb_face_t *> (this), tag, user_data);
    if (unlikely (!blob))
      return hb_blob_get_empty ();

    return blob;
  }

  unsigned int get_upem () const
  {
    unsigned int ret = upem;
    if (unlikely (!ret))
    {
      return load_upem ();
    }
    return ret;
  }

  unsigned int get_num_glyphs () const
  {
    unsigned int ret = num_glyphs;
    if (unlikely (ret == UINT_MAX))
      return load_num_glyphs ();
    return ret;
  }

  private:
  HB_INTERNAL unsigned int load_upem () const;
  HB_INTERNAL unsigned int load_num_glyphs () const;
};
DECLARE_NULL_INSTANCE (hb_face_t);


#endif /* HB_FACE_HH */
