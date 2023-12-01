#if !defined(HB_OT_H_IN) && !defined(HB_NO_SINGLE_HEADER_ERROR)
#error "Include <hb-ot.h> instead."
#endif

#ifndef HB_OT_COLOR_H
#define HB_OT_COLOR_H

#include "hb.h"
#include "hb-ot-name.h"

HB_BEGIN_DECLS


/*
 * Color palettes.
 */

HB_EXTERN hb_bool_t
hb_ot_color_has_palettes (hb_face_t *face);

HB_EXTERN unsigned int
hb_ot_color_palette_get_count (hb_face_t *face);

HB_EXTERN hb_ot_name_id_t
hb_ot_color_palette_get_name_id (hb_face_t *face,
                                 unsigned int palette_index);

HB_EXTERN hb_ot_name_id_t
hb_ot_color_palette_color_get_name_id (hb_face_t *face,
                                       unsigned int color_index);

/**
 * hb_ot_color_palette_flags_t:
 * @HB_OT_COLOR_PALETTE_FLAG_DEFAULT: Default indicating that there is nothing special
 *   to note about a color palette.
 * @HB_OT_COLOR_PALETTE_FLAG_USABLE_WITH_LIGHT_BACKGROUND: Flag indicating that the color
 *   palette is appropriate to use when displaying the font on a light background such as white.
 * @HB_OT_COLOR_PALETTE_FLAG_USABLE_WITH_DARK_BACKGROUND: Flag indicating that the color
 *   palette is appropriate to use when displaying the font on a dark background such as black.
 *
 * Flags that describe the properties of color palette.
 *
 * Since: 2.1.0
 */
typedef enum { /*< flags >*/
  HB_OT_COLOR_PALETTE_FLAG_DEFAULT                      = 0x00000000u,
  HB_OT_COLOR_PALETTE_FLAG_USABLE_WITH_LIGHT_BACKGROUND = 0x00000001u,
  HB_OT_COLOR_PALETTE_FLAG_USABLE_WITH_DARK_BACKGROUND  = 0x00000002u
} hb_ot_color_palette_flags_t;

HB_EXTERN hb_ot_color_palette_flags_t
hb_ot_color_palette_get_flags (hb_face_t *face,
                               unsigned int palette_index);

HB_EXTERN unsigned int
hb_ot_color_palette_get_colors (hb_face_t    *face,
                                unsigned int  palette_index,
                                unsigned int  start_offset,
                                unsigned int *color_count,  /* IN/OUT.  May be NULL. */
                                hb_color_t   *colors        /* OUT.     May be NULL. */);


/*
 * Color layers.
 */

HB_EXTERN hb_bool_t
hb_ot_color_has_layers (hb_face_t *face);

/**
 * hb_ot_color_layer_t:
 * @glyph: the glyph ID of the layer
 * @color_index: the palette color index of the layer
 *
 * Pairs of glyph and color index.
 *
 * A color index of 0xFFFF does not refer to a palette
 * color, but indicates that the foreground color should
 * be used.
 *
 * Since: 2.1.0
 **/
typedef struct hb_ot_color_layer_t {
  hb_codepoint_t glyph;
  unsigned int   color_index;
} hb_ot_color_layer_t;

HB_EXTERN unsigned int
hb_ot_color_glyph_get_layers (hb_face_t           *face,
                              hb_codepoint_t       glyph,
                              unsigned int         start_offset,
                              unsigned int        *layer_count, /* IN/OUT.  May be NULL. */
                              hb_ot_color_layer_t *layers /* OUT.     May be NULL. */);

/* COLRv1 */

HB_EXTERN hb_bool_t
hb_ot_color_has_paint (hb_face_t *face);

HB_EXTERN hb_bool_t
hb_ot_color_glyph_has_paint (hb_face_t      *face,
                             hb_codepoint_t  glyph);

/*
 * SVG
 */

HB_EXTERN hb_bool_t
hb_ot_color_has_svg (hb_face_t *face);

HB_EXTERN hb_blob_t *
hb_ot_color_glyph_reference_svg (hb_face_t *face, hb_codepoint_t glyph);

/*
 * PNG: CBDT or sbix
 */

HB_EXTERN hb_bool_t
hb_ot_color_has_png (hb_face_t *face);

HB_EXTERN hb_blob_t *
hb_ot_color_glyph_reference_png (hb_font_t *font, hb_codepoint_t glyph);


HB_END_DECLS

#endif /* HB_OT_COLOR_H */
