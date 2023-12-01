#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "java_io_Console.h"

#include <stdlib.h>
#include <unistd.h>

JNIEXPORT jboolean JNICALL
Java_java_io_Console_istty(JNIEnv *env, jclass cls)
{
    return isatty(fileno(stdin)) && isatty(fileno(stdout));
}

JNIEXPORT jstring JNICALL
Java_java_io_Console_encoding(JNIEnv *env, jclass cls)
{
    return NULL;
}
