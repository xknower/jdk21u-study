#include "jni.h"

/*
 * Class name checking methods
 */

jboolean verifyClassname(char *name, jboolean allowArrayClass);
jboolean verifyFixClassname(char *name);
void fixClassname(char *name);
