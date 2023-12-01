#ifndef HB_OT_FACE_HH
#define HB_OT_FACE_HH

#include "hb.hh"

#include "hb-machinery.hh"


/*
 * hb_ot_face_t
 */

/* Declare tables. */
#define HB_OT_TABLE(Namespace, Type) namespace Namespace { struct Type; }
#define HB_OT_ACCELERATOR(Namespace, Type) HB_OT_TABLE (Namespace, Type##_accelerator_t)
#include "hb-ot-face-table-list.hh"
#undef HB_OT_ACCELERATOR
#undef HB_OT_TABLE

struct hb_ot_face_t
{
  HB_INTERNAL void init0 (hb_face_t *face);
  HB_INTERNAL void fini ();

#define HB_OT_TABLE_ORDER(Namespace, Type) \
    HB_PASTE (ORDER_, HB_PASTE (Namespace, HB_PASTE (_, Type)))
  enum order_t
  {
    ORDER_ZERO,
#define HB_OT_TABLE(Namespace, Type) HB_OT_TABLE_ORDER (Namespace, Type),
#include "hb-ot-face-table-list.hh"
#undef HB_OT_TABLE
  };

  hb_face_t *face; /* MUST be JUST before the lazy loaders. */
#define HB_OT_TABLE(Namespace, Type) \
  hb_table_lazy_loader_t<Namespace::Type, HB_OT_TABLE_ORDER (Namespace, Type)> Type;
#define HB_OT_CORE_TABLE(Namespace, Type) \
  hb_table_lazy_loader_t<Namespace::Type, HB_OT_TABLE_ORDER (Namespace, Type), true> Type;
#define HB_OT_ACCELERATOR(Namespace, Type) \
  hb_face_lazy_loader_t<Namespace::Type##_accelerator_t, HB_OT_TABLE_ORDER (Namespace, Type)> Type;
#include "hb-ot-face-table-list.hh"
#undef HB_OT_ACCELERATOR
#undef HB_OT_CORE_TABLE
#undef HB_OT_TABLE
};


#endif /* HB_OT_FACE_HH */
