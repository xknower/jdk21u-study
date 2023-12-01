#if !defined(HB_OT_H_IN) && !defined(HB_NO_SINGLE_HEADER_ERROR)
#error "Include <hb-ot.h> instead."
#endif

#ifndef HB_OT_META_H
#define HB_OT_META_H

#include "hb.h"

HB_BEGIN_DECLS

/**
 * hb_ot_meta_tag_t:
 * @HB_OT_META_TAG_DESIGN_LANGUAGES: Design languages. Text, using only
 * Basic Latin (ASCII) characters. Indicates languages and/or scripts
 * for the user audiences that the font was primarily designed for.
 * @HB_OT_META_TAG_SUPPORTED_LANGUAGES: Supported languages. Text, using
 * only Basic Latin (ASCII) characters. Indicates languages and/or scripts
 * that the font is declared to be capable of supporting.
 *
 * Known metadata tags from https://docs.microsoft.com/en-us/typography/opentype/spec/meta
 *
 * Since: 2.6.0
 **/
typedef enum {
/*
   HB_OT_META_TAG_APPL          = HB_TAG ('a','p','p','l'),
   HB_OT_META_TAG_BILD          = HB_TAG ('b','i','l','d'),
*/
  HB_OT_META_TAG_DESIGN_LANGUAGES       = HB_TAG ('d','l','n','g'),
  HB_OT_META_TAG_SUPPORTED_LANGUAGES    = HB_TAG ('s','l','n','g'),

  /*< private >*/
  _HB_OT_META_TAG_MAX_VALUE = HB_TAG_MAX_SIGNED /*< skip >*/
} hb_ot_meta_tag_t;

HB_EXTERN unsigned int
hb_ot_meta_get_entry_tags (hb_face_t        *face,
                           unsigned int      start_offset,
                           unsigned int     *entries_count, /* IN/OUT.  May be NULL. */
                           hb_ot_meta_tag_t *entries        /* OUT.     May be NULL. */);

HB_EXTERN hb_blob_t *
hb_ot_meta_reference_entry (hb_face_t *face, hb_ot_meta_tag_t meta_tag);

HB_END_DECLS

#endif /* HB_OT_META_H */
