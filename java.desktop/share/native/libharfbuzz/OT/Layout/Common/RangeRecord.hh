#ifndef OT_LAYOUT_COMMON_RANGERECORD_HH
#define OT_LAYOUT_COMMON_RANGERECORD_HH

namespace OT {
namespace Layout {
namespace Common {

template <typename Types>
struct RangeRecord
{
  typename Types::HBGlyphID     first;          /* First GlyphID in the range */
  typename Types::HBGlyphID     last;           /* Last GlyphID in the range */
  HBUINT16                      value;          /* Value */

  DEFINE_SIZE_STATIC (2 + 2 * Types::size);

  bool sanitize (hb_sanitize_context_t *c) const
  {
    TRACE_SANITIZE (this);
    return_trace (c->check_struct (this));
  }

  int cmp (hb_codepoint_t g) const
  { return g < first ? -1 : g <= last ? 0 : +1; }

  HB_INTERNAL static int cmp_range (const void *pa, const void *pb) {
    const RangeRecord *a = (const RangeRecord *) pa;
    const RangeRecord *b = (const RangeRecord *) pb;
    if (a->first < b->first) return -1;
    if (a->first > b->first) return +1;
    if (a->last < b->last) return -1;
    if (a->last > b->last) return +1;
    if (a->value < b->value) return -1;
    if (a->value > b->value) return +1;
    return 0;
  }

  unsigned get_population () const
  {
    if (unlikely (last < first)) return 0;
    return (last - first + 1);
  }

  bool intersects (const hb_set_t &glyphs) const
  { return glyphs.intersects (first, last); }

  template <typename set_t>
  bool collect_coverage (set_t *glyphs) const
  { return glyphs->add_range (first, last); }
};

}
}
}

// TODO(garretrieger): This was previously implemented using
//    DECLARE_NULL_NAMESPACE_BYTES_TEMPLATE1 (OT, RangeRecord, 9);
//    but that only works when there is only a single namespace level.
//    The macro should probably be fixed so it can work in this situation.
extern HB_INTERNAL const unsigned char _hb_Null_OT_RangeRecord[9];
template <typename Spec>
struct Null<OT::Layout::Common::RangeRecord<Spec>> {
  static OT::Layout::Common::RangeRecord<Spec> const & get_null () {
    return *reinterpret_cast<const OT::Layout::Common::RangeRecord<Spec> *> (_hb_Null_OT_RangeRecord);
  }
};


#endif  // #ifndef OT_LAYOUT_COMMON_RANGERECORD_HH
