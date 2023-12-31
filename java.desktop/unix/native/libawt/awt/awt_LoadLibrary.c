#include <stdio.h>
#include <dlfcn.h>
#include <string.h>
#include <stdlib.h>
#include <jni.h>
#include <jni_util.h>
#include <jvm.h>
#include "gdefs.h"

#include <sys/param.h>
#include <sys/utsname.h>

#ifdef AIX
#include "porting_aix.h" /* For the 'dladdr' function. */
#endif

#ifdef DEBUG
#define VERBOSE_AWT_DEBUG
#endif

static void *awtHandle = NULL;

typedef jint JNICALL JNI_OnLoad_type(JavaVM *vm, void *reserved);

/* Initialize the Java VM instance variable when the library is
   first loaded */
JNIEXPORT JavaVM *jvm;

JNIEXPORT jboolean JNICALL AWTIsHeadless() {
    static JNIEnv *env = NULL;
    static jboolean isHeadless;
    jmethodID headlessFn;
    jclass graphicsEnvClass;

    if (env == NULL) {
        env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
        graphicsEnvClass = (*env)->FindClass(env,
                                             "java/awt/GraphicsEnvironment");
        if (graphicsEnvClass == NULL) {
            return JNI_TRUE;
        }
        headlessFn = (*env)->GetStaticMethodID(env,
                                               graphicsEnvClass, "isHeadless", "()Z");
        if (headlessFn == NULL) {
            return JNI_TRUE;
        }
        isHeadless = (*env)->CallStaticBooleanMethod(env, graphicsEnvClass,
                                                     headlessFn);
        if ((*env)->ExceptionCheck(env)) {
            (*env)->ExceptionClear(env);
            return JNI_TRUE;
        }
    }
    return isHeadless;
}

#define CHECK_EXCEPTION_FATAL(env, message) \
    if ((*env)->ExceptionCheck(env)) { \
        (*env)->ExceptionClear(env); \
        (*env)->FatalError(env, message); \
    }

/*
 * Pathnames to the various awt toolkits
 */

#ifdef MACOSX
  #define LWAWT_PATH "/libawt_lwawt.dylib"
  #define DEFAULT_PATH LWAWT_PATH
#else
  #define XAWT_PATH "/libawt_xawt.so"
  #define DEFAULT_PATH XAWT_PATH
  #define HEADLESS_PATH "/libawt_headless.so"
#endif

jint
AWT_OnLoad(JavaVM *vm, void *reserved)
{
    Dl_info dlinfo;
    char buf[MAXPATHLEN];
    int32_t len;
    char *p, *tk;
    JNI_OnLoad_type *JNI_OnLoad_ptr;
    struct utsname name;
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(vm, JNI_VERSION_1_2);
    void *v;

    if (awtHandle != NULL) {
        /* Avoid several loading attempts */
        return JNI_VERSION_1_2;
    }

    jvm = vm;
#ifndef STATIC_BUILD
    /* Get address of this library and the directory containing it. */
    dladdr((void *)AWT_OnLoad, &dlinfo);
    realpath((char *)dlinfo.dli_fname, buf);
    len = strlen(buf);
    p = strrchr(buf, '/');
#endif
    /*
     * The code below is responsible for
     * loading appropriate awt library, i.e. libawt_xawt or libawt_headless
     */

#ifdef MACOSX
        tk = LWAWT_PATH;
#else
        tk = XAWT_PATH;
#endif

#ifndef MACOSX
    if (AWTIsHeadless()) {
        tk = HEADLESS_PATH;
    }
#endif

#ifndef STATIC_BUILD
    /* Calculate library name to load */
    strncpy(p, tk, MAXPATHLEN-len-1);
#endif

#ifndef STATIC_BUILD
    jstring jbuf = JNU_NewStringPlatform(env, buf);
    CHECK_EXCEPTION_FATAL(env, "Could not allocate library name");
    JNU_CallStaticMethodByName(env, NULL, "java/lang/System", "load",
                               "(Ljava/lang/String;)V",
                               jbuf);

    awtHandle = dlopen(buf, RTLD_LAZY | RTLD_GLOBAL);
#endif
    return JNI_VERSION_1_2;
}

JNIEXPORT jint JNICALL
DEF_JNI_OnLoad(JavaVM *vm, void *reserved)
{
    return AWT_OnLoad(vm, reserved);
}
