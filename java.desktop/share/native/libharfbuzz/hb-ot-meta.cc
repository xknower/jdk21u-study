#include "hb.hh"

#ifndef HB_NO_META

#include "hb-ot-meta-table.hh"

/**
 * SECTION:hb-ot-meta
 * @title: hb-ot-meta
 * @short_description: OpenType Metadata
 * @include: hb-ot.h
 *
 * Functions for fetching metadata from fonts.
 **/

/**
 * hb_ot_meta_get_entry_tags:
 * @face: a face object
 * @start_offset: iteration's start offset
 * @entries_count:(inout) (optional): buffer size as input, filled size as output
 * @entries: (out caller-allocates) (array length=entries_count): entries tags buffer
 *
 * Fetches all available feature types.
 *
 * Return value: Number of all available feature types.
 *
 * Since: 2.6.0
 **/
unsigned int
hb_ot_meta_get_entry_tags (hb_face_t        *face,
                           unsigned int      start_offset,
                           unsigned int     *entries_count, /* IN/OUT.  May be NULL. */
                           hb_ot_meta_tag_t *entries        /* OUT.     May be NULL. */)
{
  return face->table.meta->get_entries (start_offset, entries_count, entries);
}

/**
 * hb_ot_meta_reference_entry:
 * @face: a #hb_face_t object.
 * @meta_tag: tag of metadata you like to have.
 *
 * It fetches metadata entry of a given tag from a font.
 *
 * Returns: (transfer full): A blob containing the blob.
 *
 * Since: 2.6.0
 **/
hb_blob_t *
hb_ot_meta_reference_entry (hb_face_t *face, hb_ot_meta_tag_t meta_tag)
{
  return face->table.meta->reference_entry (meta_tag);
}

#endif
