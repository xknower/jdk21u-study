#include "hb.hh"
#include "hb-number.hh"
#include "hb-number-parser.hh"

template<typename T, typename Func>
static bool
_parse_number (const char **pp, const char *end, T *pv,
               bool whole_buffer, Func f)
{
  char buf[32];
  unsigned len = hb_min (ARRAY_LENGTH (buf) - 1, (unsigned) (end - *pp));
  strncpy (buf, *pp, len);
  buf[len] = '\0';

  char *p = buf;
  char *pend = p;

  errno = 0;
  *pv = f (p, &pend);
  if (unlikely (errno || p == pend ||
                /* Check if consumed whole buffer if is requested */
                (whole_buffer && pend - p != end - *pp)))
    return false;

  *pp += pend - p;
  return true;
}

bool
hb_parse_int (const char **pp, const char *end, int *pv, bool whole_buffer)
{
  return _parse_number<int> (pp, end, pv, whole_buffer,
                             [] (const char *p, char **end)
                             { return strtol (p, end, 10); });
}

bool
hb_parse_uint (const char **pp, const char *end, unsigned *pv,
               bool whole_buffer, int base)
{
  return _parse_number<unsigned> (pp, end, pv, whole_buffer,
                                  [base] (const char *p, char **end)
                                  { return strtoul (p, end, base); });
}

bool
hb_parse_double (const char **pp, const char *end, double *pv, bool whole_buffer)
{
  const char *pend = end;
  *pv = strtod_rl (*pp, &pend);
  if (unlikely (*pp == pend)) return false;
  *pp = pend;
  return !whole_buffer || end == pend;
}
