#ifndef HB_MULTIMAP_HH
#define HB_MULTIMAP_HH

#include "hb.hh"
#include "hb-map.hh"
#include "hb-vector.hh"


/*
 * hb_multimap_t
 */

struct hb_multimap_t
{
  void add (hb_codepoint_t k, hb_codepoint_t v)
  {
    hb_vector_t<hb_codepoint_t> *m;
    if (multiples.has (k, &m))
    {
      m->push (v);
      return;
    }

    hb_codepoint_t *old_v;
    if (singulars.has (k, &old_v))
    {
      hb_codepoint_t old = *old_v;
      singulars.del (k);

      multiples.set (k, hb_vector_t<hb_codepoint_t> {old, v});
      return;
    }

    singulars.set (k, v);
  }

  hb_array_t<const hb_codepoint_t> get (hb_codepoint_t k) const
  {
    const hb_codepoint_t *v;
    if (singulars.has (k, &v))
      return hb_array (v, 1);

    hb_vector_t<hb_codepoint_t> *m;
    if (multiples.has (k, &m))
      return m->as_array ();

    return hb_array_t<const hb_codepoint_t> ();
  }

  bool in_error () const
  {
    if (singulars.in_error () || multiples.in_error ())
      return true;
    for (const auto &m : multiples.values_ref ())
      if (m.in_error ())
        return true;
    return false;
  }

  void alloc (unsigned size)
  {
    singulars.alloc (size);
  }

  protected:
  hb_map_t singulars;
  hb_hashmap_t<hb_codepoint_t, hb_vector_t<hb_codepoint_t>> multiples;
};



#endif /* HB_MULTIMAP_HH */
