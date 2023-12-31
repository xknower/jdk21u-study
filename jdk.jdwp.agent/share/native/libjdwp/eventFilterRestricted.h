#ifndef JDWP_EVENTFILTERRESTRICTED_H
#define JDWP_EVENTFILTERRESTRICTED_H

/**
 * eventFilter functionality restricted to use only by it's
 * enclosing module - eventHandler.
 */

HandlerNode *eventFilterRestricted_alloc(jint filterCount);

jvmtiError eventFilterRestricted_install(HandlerNode *node);

jvmtiError eventFilterRestricted_deinstall(HandlerNode *node);

jboolean eventFilterRestricted_passesFilter(JNIEnv *env,
                                            char *classname,
                                            EventInfo *evinfo,
                                            HandlerNode *node,
                                            jboolean *shouldDelete);
jboolean eventFilterRestricted_passesUnloadFilter(JNIEnv *env,
                                                  char *classname,
                                                  HandlerNode *node,
                                                  jboolean *shouldDelete);
jboolean eventFilterRestricted_isBreakpointInClass(JNIEnv *env,
                                                   jclass clazz,
                                                   HandlerNode *node);

#endif
