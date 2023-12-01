#include <jni.h>

#include "jni_util.h"
#include "jmm.h"

#ifndef _MANAGEMENT_EXT_H_
#define _MANAGEMENT_EXT_H_

// These symbols are global in this library but need to be uniquely named to
// avoid conflicts with same-named symbols in other native libraries, when
// statically linking.
extern const JmmInterface* jmm_interface_management_ext;
extern jint jmm_version_management_ext;

void throw_internal_error(JNIEnv* env, const char* msg);

#endif
