#ifndef HB_OUTLINE_HH
#define HB_OUTLINE_HH

#include "hb.hh"

#include "hb-draw.hh"


struct hb_outline_point_t
{
  enum class type_t
  {
    MOVE_TO,
    LINE_TO,
    QUADRATIC_TO,
    CUBIC_TO,
  };

  hb_outline_point_t (float x, float y, type_t type) :
    x (x), y (y), type (type) {}

  float x, y;
  type_t type;
};

struct hb_outline_vector_t
{
  float normalize_len ()
  {
    float len = hypotf (x, y);
    if (len)
    {
      x /= len;
      y /= len;
    }
    return len;
  }

  float x, y;
};

struct hb_outline_t
{
  void reset () { points.shrink (0, false); contours.resize (0); }

  HB_INTERNAL void replay (hb_draw_funcs_t *pen, void *pen_data) const;
  HB_INTERNAL float control_area () const;
  HB_INTERNAL void embolden (float x_strength, float y_strength,
                             float x_shift, float y_shift);

  hb_vector_t<hb_outline_point_t> points;
  hb_vector_t<unsigned> contours;
};

HB_INTERNAL hb_draw_funcs_t *
hb_outline_recording_pen_get_funcs ();


#endif /* HB_OUTLINE_HH */
