#ifndef HB_AAT_LAYOUT_ANKR_TABLE_HH
#define HB_AAT_LAYOUT_ANKR_TABLE_HH

#include "hb-aat-layout-common.hh"

/*
 * ankr -- Anchor Point
 * https://developer.apple.com/fonts/TrueType-Reference-Manual/RM06/Chap6ankr.html
 */
#define HB_AAT_TAG_ankr HB_TAG('a','n','k','r')


namespace AAT {

using namespace OT;


struct Anchor
{
  bool sanitize (hb_sanitize_context_t *c) const
  {
    TRACE_SANITIZE (this);
    return_trace (c->check_struct (this));
  }

  public:
  FWORD         xCoordinate;
  FWORD         yCoordinate;
  public:
  DEFINE_SIZE_STATIC (4);
};

typedef Array32Of<Anchor> GlyphAnchors;

struct ankr
{
  static constexpr hb_tag_t tableTag = HB_AAT_TAG_ankr;

  const Anchor &get_anchor (hb_codepoint_t glyph_id,
                            unsigned int i,
                            unsigned int num_glyphs) const
  {
    const NNOffset16To<GlyphAnchors> *offset = (this+lookupTable).get_value (glyph_id, num_glyphs);
    if (!offset)
      return Null (Anchor);
    const GlyphAnchors &anchors = &(this+anchorData) + *offset;
    return anchors[i];
  }

  bool sanitize (hb_sanitize_context_t *c) const
  {
    TRACE_SANITIZE (this);
    return_trace (likely (c->check_struct (this) &&
                          version == 0 &&
                          c->check_range (this, anchorData) &&
                          lookupTable.sanitize (c, this, &(this+anchorData))));
  }

  protected:
  HBUINT16      version;        /* Version number (set to zero) */
  HBUINT16      flags;          /* Flags (currently unused; set to zero) */
  Offset32To<Lookup<NNOffset16To<GlyphAnchors>>>
                lookupTable;    /* Offset to the table's lookup table */
  NNOffset32To<HBUINT8>
                anchorData;     /* Offset to the glyph data table */

  public:
  DEFINE_SIZE_STATIC (12);
};

} /* namespace AAT */


#endif /* HB_AAT_LAYOUT_ANKR_TABLE_HH */
