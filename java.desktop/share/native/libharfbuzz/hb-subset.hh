#ifndef HB_SUBSET_HH
#define HB_SUBSET_HH


#include "hb.hh"

#include "hb-subset.h"

#include "hb-machinery.hh"
#include "hb-serialize.hh"
#include "hb-subset-input.hh"
#include "hb-subset-plan.hh"

struct hb_subset_context_t :
       hb_dispatch_context_t<hb_subset_context_t, bool, HB_DEBUG_SUBSET>
{
  const char *get_name () { return "SUBSET"; }
  static return_t default_return_value () { return true; }

  private:
  template <typename T, typename ...Ts> auto
  _dispatch (const T &obj, hb_priority<1>, Ts&&... ds) HB_AUTO_RETURN
  ( obj.subset (this, std::forward<Ts> (ds)...) )
  template <typename T, typename ...Ts> auto
  _dispatch (const T &obj, hb_priority<0>, Ts&&... ds) HB_AUTO_RETURN
  ( obj.dispatch (this, std::forward<Ts> (ds)...) )
  public:
  template <typename T, typename ...Ts> auto
  dispatch (const T &obj, Ts&&... ds) HB_AUTO_RETURN
  ( _dispatch (obj, hb_prioritize, std::forward<Ts> (ds)...) )

  hb_blob_t *source_blob;
  hb_subset_plan_t *plan;
  hb_serialize_context_t *serializer;
  hb_tag_t table_tag;

  hb_subset_context_t (hb_blob_t *source_blob_,
                       hb_subset_plan_t *plan_,
                       hb_serialize_context_t *serializer_,
                       hb_tag_t table_tag_) :
                        source_blob (source_blob_),
                        plan (plan_),
                        serializer (serializer_),
                        table_tag (table_tag_) {}
};


#endif /* HB_SUBSET_HH */
