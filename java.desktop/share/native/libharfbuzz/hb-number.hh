#ifndef HB_NUMBER_HH
#define HB_NUMBER_HH

HB_INTERNAL bool
hb_parse_int (const char **pp, const char *end, int *pv,
              bool whole_buffer = false);

HB_INTERNAL bool
hb_parse_uint (const char **pp, const char *end, unsigned int *pv,
               bool whole_buffer = false, int base = 10);

HB_INTERNAL bool
hb_parse_double (const char **pp, const char *end, double *pv,
                 bool whole_buffer = false);

#endif /* HB_NUMBER_HH */
