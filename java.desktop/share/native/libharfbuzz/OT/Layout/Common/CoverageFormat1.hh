#ifndef OT_LAYOUT_COMMON_COVERAGEFORMAT1_HH
#define OT_LAYOUT_COMMON_COVERAGEFORMAT1_HH

namespace OT {
namespace Layout {
namespace Common {

#define NOT_COVERED             ((unsigned int) -1)

template <typename Types>
struct CoverageFormat1_3
{
  friend struct Coverage;

  protected:
  HBUINT16      coverageFormat; /* Format identifier--format = 1 */
  SortedArray16Of<typename Types::HBGlyphID>
                glyphArray;     /* Array of GlyphIDs--in numerical order */
  public:
  DEFINE_SIZE_ARRAY (4, glyphArray);

  private:
  bool sanitize (hb_sanitize_context_t *c) const
  {
    TRACE_SANITIZE (this);
    return_trace (glyphArray.sanitize (c));
  }

  unsigned int get_coverage (hb_codepoint_t glyph_id) const
  {
    unsigned int i;
    glyphArray.bfind (glyph_id, &i, HB_NOT_FOUND_STORE, NOT_COVERED);
    return i;
  }

  unsigned get_population () const
  {
    return glyphArray.len;
  }

  template <typename Iterator,
      hb_requires (hb_is_sorted_source_of (Iterator, hb_codepoint_t))>
  bool serialize (hb_serialize_context_t *c, Iterator glyphs)
  {
    TRACE_SERIALIZE (this);
    return_trace (glyphArray.serialize (c, glyphs));
  }

  bool intersects (const hb_set_t *glyphs) const
  {
    if (glyphArray.len > glyphs->get_population () * hb_bit_storage ((unsigned) glyphArray.len) / 2)
    {
      for (auto g : *glyphs)
        if (get_coverage (g) != NOT_COVERED)
          return true;
      return false;
    }

    for (const auto& g : glyphArray.as_array ())
      if (glyphs->has (g))
        return true;
    return false;
  }
  bool intersects_coverage (const hb_set_t *glyphs, unsigned int index) const
  { return glyphs->has (glyphArray[index]); }

  template <typename IterableOut,
            hb_requires (hb_is_sink_of (IterableOut, hb_codepoint_t))>
  void intersect_set (const hb_set_t &glyphs, IterableOut&& intersect_glyphs) const
  {
    unsigned count = glyphArray.len;
    for (unsigned i = 0; i < count; i++)
      if (glyphs.has (glyphArray[i]))
        intersect_glyphs << glyphArray[i];
  }

  template <typename set_t>
  bool collect_coverage (set_t *glyphs) const
  { return glyphs->add_sorted_array (glyphArray.as_array ()); }

  public:
  /* Older compilers need this to be public. */
  struct iter_t
  {
    void init (const struct CoverageFormat1_3 &c_) { c = &c_; i = 0; }
    bool __more__ () const { return i < c->glyphArray.len; }
    void __next__ () { i++; }
    hb_codepoint_t get_glyph () const { return c->glyphArray[i]; }
    bool operator != (const iter_t& o) const
    { return i != o.i; }
    iter_t __end__ () const { iter_t it; it.init (*c); it.i = c->glyphArray.len; return it; }

    private:
    const struct CoverageFormat1_3 *c;
    unsigned int i;
  };
  private:
};

}
}
}

#endif  // #ifndef OT_LAYOUT_COMMON_COVERAGEFORMAT1_HH
