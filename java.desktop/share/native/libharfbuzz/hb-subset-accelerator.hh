#ifndef HB_SUBSET_ACCELERATOR_HH
#define HB_SUBSET_ACCELERATOR_HH


#include "hb.hh"

#include "hb-map.hh"
#include "hb-multimap.hh"
#include "hb-set.hh"

extern HB_INTERNAL hb_user_data_key_t _hb_subset_accelerator_user_data_key;

namespace CFF {
struct cff_subset_accelerator_t;
}

namespace OT {
struct SubtableUnicodesCache;
struct cff1_subset_accelerator_t;
struct cff2_subset_accelerator_t;
}

struct hb_subset_accelerator_t
{
  static hb_user_data_key_t* user_data_key()
  {
    return &_hb_subset_accelerator_user_data_key;
  }

  static hb_subset_accelerator_t* create(hb_face_t *source,
                                         const hb_map_t& unicode_to_gid_,
                                         const hb_set_t& unicodes_,
                                         bool has_seac_) {
    hb_subset_accelerator_t* accel =
        (hb_subset_accelerator_t*) hb_calloc (1, sizeof(hb_subset_accelerator_t));

    if (unlikely (!accel)) return accel;

    new (accel) hb_subset_accelerator_t (source,
                                         unicode_to_gid_,
                                         unicodes_,
                                         has_seac_);

    return accel;
  }

  static void destroy (void* p)
  {
    if (!p) return;

    hb_subset_accelerator_t *accel = (hb_subset_accelerator_t *) p;

    accel->~hb_subset_accelerator_t ();

    hb_free (accel);
  }

  hb_subset_accelerator_t (hb_face_t *source,
                           const hb_map_t& unicode_to_gid_,
                           const hb_set_t& unicodes_,
                           bool has_seac_) :
    unicode_to_gid(unicode_to_gid_),
    unicodes(unicodes_),
    cmap_cache(nullptr),
    destroy_cmap_cache(nullptr),
    has_seac(has_seac_),
    source(hb_face_reference (source))
  {
    gid_to_unicodes.alloc (unicode_to_gid.get_population ());
    for (const auto &_ : unicode_to_gid)
    {
      auto unicode = _.first;
      auto gid = _.second;
      gid_to_unicodes.add (gid, unicode);
    }
  }

  HB_INTERNAL ~hb_subset_accelerator_t ();

  // Generic

  mutable hb_mutex_t sanitized_table_cache_lock;
  mutable hb_hashmap_t<hb_tag_t, hb::unique_ptr<hb_blob_t>> sanitized_table_cache;

  hb_map_t unicode_to_gid;
  hb_multimap_t gid_to_unicodes;
  hb_set_t unicodes;

  // cmap
  const OT::SubtableUnicodesCache* cmap_cache;
  hb_destroy_func_t destroy_cmap_cache;

  // CFF
  bool has_seac;

  // TODO(garretrieger): cumulative glyf checksum map

  bool in_error () const
  {
    return unicode_to_gid.in_error () ||
           gid_to_unicodes.in_error () ||
           unicodes.in_error () ||
           sanitized_table_cache.in_error ();
  }

  hb_face_t *source;
#ifndef HB_NO_SUBSET_CFF
  // These have to be immediately after source:
  mutable hb_face_lazy_loader_t<OT::cff1_subset_accelerator_t, 1> cff1_accel;
  mutable hb_face_lazy_loader_t<OT::cff2_subset_accelerator_t, 2> cff2_accel;
#endif
};


#endif /* HB_SUBSET_ACCELERATOR_HH */
