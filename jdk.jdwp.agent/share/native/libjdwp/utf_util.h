#ifndef _utf_util_h_
#define _utf_util_h_

#include "jni.h"


int JNICALL utf8sToUtf8mLength(jbyte *string, int length);
void JNICALL utf8sToUtf8m(jbyte *string, int length, jbyte *newString, int newLength);
int JNICALL utf8mToUtf8sLength(jbyte *string, int length);
void JNICALL utf8mToUtf8s(jbyte *string, int length, jbyte *newString, int newLength);

int JNICALL utf8ToPlatform(jbyte *utf8, int len, char* output, int outputBufSize);
int JNICALL utf8FromPlatform(char *str, int len, jbyte *output, int outputBufSize);

#endif
