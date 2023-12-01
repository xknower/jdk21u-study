#include "hb-ot-face.hh"

#include "hb-ot-cmap-table.hh"
#include "hb-ot-glyf-table.hh"
#include "hb-ot-cff1-table.hh"
#include "hb-ot-cff2-table.hh"
#include "hb-ot-hmtx-table.hh"
#include "hb-ot-kern-table.hh"
#include "hb-ot-meta-table.hh"
#include "hb-ot-name-table.hh"
#include "hb-ot-post-table.hh"
#include "OT/Color/CBDT/CBDT.hh"
#include "OT/Color/sbix/sbix.hh"
#include "OT/Color/svg/svg.hh"
#include "hb-ot-layout-gdef-table.hh"
#include "hb-ot-layout-gsub-table.hh"
#include "hb-ot-layout-gpos-table.hh"


void hb_ot_face_t::init0 (hb_face_t *face)
{
  this->face = face;
#define HB_OT_TABLE(Namespace, Type) Type.init0 ();
#include "hb-ot-face-table-list.hh"
#undef HB_OT_TABLE
}
void hb_ot_face_t::fini ()
{
#define HB_OT_TABLE(Namespace, Type) Type.fini ();
#include "hb-ot-face-table-list.hh"
#undef HB_OT_TABLE
}
