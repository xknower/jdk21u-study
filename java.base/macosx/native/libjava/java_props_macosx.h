#include "java_props.h"

char *setupMacOSXLocale(int cat);
const char *convertToPOSIXLocale(const char* src);
void setOSNameAndVersion(java_props_t *sprops);
void setUserHome(java_props_t *sprops);
void setProxyProperties(java_props_t *sProps);
