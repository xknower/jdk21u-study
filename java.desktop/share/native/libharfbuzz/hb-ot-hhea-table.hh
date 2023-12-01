#ifndef HB_OT_HHEA_TABLE_HH
#define HB_OT_HHEA_TABLE_HH

#include "hb-open-type.hh"

/*
 * hhea -- Horizontal Header
 * https://docs.microsoft.com/en-us/typography/opentype/spec/hhea
 * vhea -- Vertical Header
 * https://docs.microsoft.com/en-us/typography/opentype/spec/vhea
 */
#define HB_OT_TAG_hhea HB_TAG('h','h','e','a')
#define HB_OT_TAG_vhea HB_TAG('v','h','e','a')


namespace OT {


template <typename T>
struct _hea
{
  bool has_data () const { return version.major; }

  bool sanitize (hb_sanitize_context_t *c) const
  {
    TRACE_SANITIZE (this);
    return_trace (c->check_struct (this) && likely (version.major == 1));
  }

  public:
  FixedVersion<>version;        /* 0x00010000u for version 1.0. */
  FWORD         ascender;       /* Typographic ascent. */
  FWORD         descender;      /* Typographic descent. */
  FWORD         lineGap;        /* Typographic line gap. */
  UFWORD        advanceMax;     /* Maximum advance width/height value in
                                 * metrics table. */
  FWORD         minLeadingBearing;
                                /* Minimum left/top sidebearing value in
                                 * metrics table. */
  FWORD         minTrailingBearing;
                                /* Minimum right/bottom sidebearing value;
                                 * calculated as Min(aw - lsb -
                                 * (xMax - xMin)) for horizontal. */
  FWORD         maxExtent;      /* horizontal: Max(lsb + (xMax - xMin)),
                                 * vertical: minLeadingBearing+(yMax-yMin). */
  HBINT16       caretSlopeRise; /* Used to calculate the slope of the
                                 * cursor (rise/run); 1 for vertical caret,
                                 * 0 for horizontal.*/
  HBINT16       caretSlopeRun;  /* 0 for vertical caret, 1 for horizontal. */
  HBINT16       caretOffset;    /* The amount by which a slanted
                                 * highlight on a glyph needs
                                 * to be shifted to produce the
                                 * best appearance. Set to 0 for
                                 * non-slanted fonts. */
  HBINT16       reserved1;      /* Set to 0. */
  HBINT16       reserved2;      /* Set to 0. */
  HBINT16       reserved3;      /* Set to 0. */
  HBINT16       reserved4;      /* Set to 0. */
  HBINT16       metricDataFormat;/* 0 for current format. */
  HBUINT16      numberOfLongMetrics;
                                /* Number of LongMetric entries in metric
                                 * table. */
  public:
  DEFINE_SIZE_STATIC (36);
};

struct hhea : _hea<hhea> {
  static constexpr hb_tag_t tableTag = HB_OT_TAG_hhea;
};
struct vhea : _hea<vhea> {
  static constexpr hb_tag_t tableTag = HB_OT_TAG_vhea;
};


} /* namespace OT */


#endif /* HB_OT_HHEA_TABLE_HH */
