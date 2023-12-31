#ifndef HB_SUBSET_INSTANCER_SOLVER_HH
#define HB_SUBSET_INSTANCER_SOLVER_HH

#include "hb.hh"

/* pre-normalized distances */
struct TripleDistances
{
  TripleDistances (): negative (1.f), positive (1.f) {}
  TripleDistances (float neg_, float pos_): negative (neg_), positive (pos_) {}
  TripleDistances (float min, float default_, float max)
  {
    negative = default_ - min;
    positive = max - default_;
  }

  float negative;
  float positive;
};

struct Triple {

  Triple () :
    minimum (0.f), middle (0.f), maximum (0.f) {}

  Triple (float minimum_, float middle_, float maximum_) :
    minimum (minimum_), middle (middle_), maximum (maximum_) {}

  bool operator == (const Triple &o) const
  {
    return minimum == o.minimum &&
           middle  == o.middle  &&
           maximum == o.maximum;
  }

  bool operator != (const Triple o) const
  { return !(*this == o); }

  bool is_point () const
  { return minimum == middle && middle == maximum; }

  bool contains (float point) const
  { return minimum <= point && point <= maximum; }

  /* from hb_array_t hash ()*/
  uint32_t hash () const
  {
    uint32_t current = /*cbf29ce4*/0x84222325;
    current = current ^ hb_hash (minimum);
    current = current * 16777619;

    current = current ^ hb_hash (middle);
    current = current * 16777619;

    current = current ^ hb_hash (maximum);
    current = current * 16777619;
    return current;
  }


  float minimum;
  float middle;
  float maximum;
};

using result_item_t = hb_pair_t<float, Triple>;
using result_t = hb_vector_t<result_item_t>;

/* renormalize a normalized value v to the range of an axis,
 * considering the prenormalized distances as well as the new axis limits.
 * Ported from fonttools */
HB_INTERNAL float renormalizeValue (float v, const Triple &triple,
                                    const TripleDistances &triple_distances,
                                    bool extrapolate = true);
/* Given a tuple (lower,peak,upper) "tent" and new axis limits
 * (axisMin,axisDefault,axisMax), solves how to represent the tent
 * under the new axis configuration.  All values are in normalized
 * -1,0,+1 coordinate system. Tent values can be outside this range.
 *
 * Return value: a list of tuples. Each tuple is of the form
 * (scalar,tent), where scalar is a multipler to multiply any
 * delta-sets by, and tent is a new tent for that output delta-set.
 * If tent value is Triple{}, that is a special deltaset that should
 * be always-enabled (called "gain").
 */
HB_INTERNAL result_t rebase_tent (Triple tent, Triple axisLimit, TripleDistances axis_triple_distances);

#endif /* HB_SUBSET_INSTANCER_SOLVER_HH */
