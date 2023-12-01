#ifdef HEADLESS
    #error This file should not be included in headless library
#endif

#include "jni_util.h"

struct MenuComponentIDs {
  jfieldID font;
  jfieldID appContext;
  jmethodID getParent;
};
