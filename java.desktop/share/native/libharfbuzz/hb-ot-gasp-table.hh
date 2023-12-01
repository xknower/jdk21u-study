#ifndef HB_OT_GASP_TABLE_HH
#define HB_OT_GASP_TABLE_HH

#include "hb-open-type.hh"
#include "hb-ot-hhea-table.hh"
#include "hb-ot-os2-table.hh"
#include "hb-ot-var-hvar-table.hh"

/*
 * gasp -- Grid-fitting and Scan-conversion Procedure
 * https://docs.microsoft.com/en-us/typography/opentype/spec/gasp
 */
#define HB_OT_TAG_gasp HB_TAG('g','a','s','p')


namespace OT {

struct GaspRange
{
  bool sanitize (hb_sanitize_context_t *c) const
  {
    TRACE_SANITIZE (this);
    return_trace (c->check_struct (this));
  }

  public:
  HBUINT16      rangeMaxPPEM;   /* Upper limit of range, in PPEM */
  HBUINT16      rangeGaspBehavior;
                                /* Flags describing desired rasterizer behavior. */
  public:
  DEFINE_SIZE_STATIC (4);
};

struct gasp
{
  static constexpr hb_tag_t tableTag = HB_OT_TAG_gasp;

  const GaspRange &get_gasp_range (unsigned int i) const
  { return gaspRanges[i]; }

  bool sanitize (hb_sanitize_context_t *c) const
  {
    TRACE_SANITIZE (this);
    return_trace (c->check_struct (this) &&
                  gaspRanges.sanitize (c));
  }

  protected:
  HBUINT16      version;        /* Version number (set to 1) */
  Array16Of<GaspRange>
                gaspRanges;     /* Number of records to follow
                                 * Sorted by ppem */
  public:
  DEFINE_SIZE_ARRAY (4, gaspRanges);
};

} /* namespace OT */


#endif /* HB_OT_GASP_TABLE_HH */
