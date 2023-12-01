#ifndef HB_DISPATCH_HH
#define HB_DISPATCH_HH

#include "hb.hh"

/*
 * Dispatch
 */

template <typename Context, typename Return=hb_empty_t, unsigned int MaxDebugDepth=0>
struct hb_dispatch_context_t
{
  private:
  /* https://en.wikipedia.org/wiki/Curiously_recurring_template_pattern */
  const Context* thiz () const { return static_cast<const Context *> (this); }
        Context* thiz ()       { return static_cast<      Context *> (this); }
  public:
  const char *get_name () { return "UNKNOWN"; }
  static constexpr unsigned max_debug_depth = MaxDebugDepth;
  typedef Return return_t;
  template <typename T, typename F>
  bool may_dispatch (const T *obj HB_UNUSED, const F *format HB_UNUSED) { return true; }
  template <typename T, typename ...Ts>
  return_t dispatch (const T &obj, Ts&&... ds)
  { return obj.dispatch (thiz (), std::forward<Ts> (ds)...); }
  static return_t no_dispatch_return_value () { return Context::default_return_value (); }
  static bool stop_sublookup_iteration (const return_t r HB_UNUSED) { return false; }
  unsigned debug_depth = 0;
};


#endif /* HB_DISPATCH_HH */
