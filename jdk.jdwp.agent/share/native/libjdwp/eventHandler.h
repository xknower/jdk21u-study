#ifndef JDWP_EVENTHANDLER_H
#define JDWP_EVENTHANDLER_H

#include "bag.h"

typedef jint HandlerID;

/* structure is read-only for users */
typedef struct HandlerNode_ {
    HandlerID handlerID;
    EventIndex ei;
    jbyte suspendPolicy;
    jboolean permanent;
    int needReturnValue;
} HandlerNode;

typedef void (*HandlerFunction)(JNIEnv *env,
                                EventInfo *evinfo,
                                HandlerNode *node,
                                struct bag *eventBag);

/***** HandlerNode create = alloc + install *****/

HandlerNode *eventHandler_alloc(jint filterCount, EventIndex ei,
                                jbyte suspendPolicy);
HandlerID eventHandler_allocHandlerID(void);
jvmtiError eventHandler_installExternal(HandlerNode *node);
HandlerNode *eventHandler_createPermanentInternal(EventIndex ei,
                                                  HandlerFunction func);
HandlerNode *eventHandler_createInternalThreadOnly(EventIndex ei,
                                                   HandlerFunction func,
                                                   jthread thread);
HandlerNode *eventHandler_createInternalBreakpoint(HandlerFunction func,
                                                   jthread thread,
                                                   jclass clazz,
                                                   jmethodID method,
                                                   jlocation location);

/***** HandlerNode free *****/

jvmtiError eventHandler_freeAll(EventIndex ei);
jvmtiError eventHandler_freeByID(EventIndex ei, HandlerID handlerID);
jvmtiError eventHandler_free(HandlerNode *node);
void eventHandler_freeClassBreakpoints(jclass clazz);

/***** HandlerNode manipulation *****/

void eventHandler_initialize(jbyte sessionID);
void eventHandler_onConnect();
void eventHandler_reset(jbyte sessionID);
void eventHandler_waitForActiveCallbacks();

void eventHandler_lock(void);
void eventHandler_unlock(void);

jboolean eventHandler_synthesizeUnloadEvent(char *signature, JNIEnv *env);

jclass getMethodClass(jvmtiEnv *jvmti_env, jmethodID method);

/***** debugging *****/

#ifdef DEBUG
void eventHandler_dumpAllHandlers(jboolean dumpPermanent);
void eventHandler_dumpHandlers(EventIndex ei, jboolean dumpPermanent);
void eventHandler_dumpHandler(HandlerNode *node);
#endif

#endif /* _EVENTHANDLER_H */
