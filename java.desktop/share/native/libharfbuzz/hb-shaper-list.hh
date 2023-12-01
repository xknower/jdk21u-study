#ifndef HB_SHAPER_LIST_HH
#define HB_SHAPER_LIST_HH
#endif /* HB_SHAPER_LIST_HH */ /* Dummy header guards */

#ifndef HB_NO_SHAPER


/* v--- Add new shapers in the right place here. */

#ifdef HAVE_WASM
/* Only picks up fonts that have a "Wasm" table. */
HB_SHAPER_IMPLEMENT (wasm)
#endif

#ifdef HAVE_GRAPHITE2
/* Only picks up fonts that have a "Silf" table. */
HB_SHAPER_IMPLEMENT (graphite2)
#endif

#ifndef HB_NO_OT_SHAPE
HB_SHAPER_IMPLEMENT (ot) /* <--- This is our main shaper. */
#endif

#ifdef HAVE_UNISCRIBE
HB_SHAPER_IMPLEMENT (uniscribe)
#endif
#ifdef HAVE_DIRECTWRITE
HB_SHAPER_IMPLEMENT (directwrite)
#endif
#ifdef HAVE_CORETEXT
HB_SHAPER_IMPLEMENT (coretext)
#endif

#ifndef HB_NO_FALLBACK_SHAPE
HB_SHAPER_IMPLEMENT (fallback) /* <--- This should be last. */
#endif


#endif
