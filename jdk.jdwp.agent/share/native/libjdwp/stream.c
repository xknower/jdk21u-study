#include "util.h" /* Needed for linux, do not remove */
#include "stream.h"

jfloat
stream_encodeFloat(jfloat theFloat)
{
    union {
        jfloat f;
        jint i;
    } sF;

    sF.f = theFloat;

    sF.i = HOST_TO_JAVA_INT(sF.i);

    return sF.f;
}

jdouble
stream_encodeDouble(jdouble d)
{
    union {
        jdouble d;
        jlong l;
    } sD;

    sD.d = d;

    sD.l = HOST_TO_JAVA_LONG(sD.l);

    return sD.d;
}
